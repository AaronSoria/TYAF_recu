package Interfaz;
import Clases.paquete;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;

/**
 *
 * @author stark28
 */
public class Principal extends  JFrame {
    
    JButton button ;
    JLabel label;
    JTextField jtf;
    
public Principal(){

super("retrieve image from database in java");
    //paquete pack = new paquete();

    button = new JButton("Retrieve");
    button.setBounds(250,300,100,40);
    
    jtf = new JTextField();
    jtf.setBounds(360,310,100,20);
    
    label = new JLabel();
    label.setBounds(10,10,670,250);
    
    add(button);
    add(label);
    add(jtf);

 button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
        
            try{
                //abrir archivo y leer imagen
                String FILENAME = "/home/stark28/planos/prueba_03_03.txt";
                BufferedReader br = null;
		FileReader fr = null;
                fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);
                String sCurrentLine="";
                paquete mipaquete = new paquete();
                while ((sCurrentLine = br.readLine()) != null)
                {
                    try 
                    {
                        boolean result = recuperar(br, sCurrentLine, mipaquete);
                        if (result == true)
                        {
                            result = reconstruir(br, sCurrentLine, mipaquete);
                            if (result == true)
                            {
                                System.out.println("ok: " + mipaquete.id);
                                mipaquete.insertarenbd();
                                paquete mipack = new paquete();
                                System.out.println(sCurrentLine);
                            }
                            else
                            {
                                System.out.println("emergencia: " + mipaquete.id);
                                paquete mipack = new paquete();
                            }
                        }
                        else
                        {
                            sCurrentLine = br.readLine();
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                        sCurrentLine = br.readLine();
                    }
                }
                
                
//		while ((sCurrentLine = br.readLine()) != null) {
//                    Line=Line+sCurrentLine;
//		}
                
                
                
                
                //aqui leo las imagenes
                    //byte[] img = rs.getBytes("Image");
                    //byte[] img = pack.HexStringToBytes(Line);
                    //Resize The ImageIcon
//                    ImageIcon image = new ImageIcon(img);
                    Image im = mipaquete.restore_image();
                    Image myImg = im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    label.setIcon(newImage);
br.close();
fr.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        
        }
    });
    
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.decode("#bdb76b"));
    setLocationRelativeTo(null);
    setSize(700,400);
    setVisible(true);
             
}

public static void main(String[] args){
        new Principal();
    }

        private boolean recuperar(BufferedReader buffrd, String sLine, paquete mipaquete)
        {
            boolean isimage = false;
            try
            {
            if (mipaquete.can_start_packet(sLine))
            {
                mipaquete.set_id(sLine);
                sLine = buffrd.readLine();
                mipaquete.set_ip(sLine);
                while (!(sLine.contains("Content-Type:")))
                {
                    sLine = buffrd.readLine();    //salto lineas para buscar el tipo y la longitud del paquete
                    if (mipaquete.can_start_packet(sLine))
                    {
                        return false;
                    }
                }
                if (mipaquete.can_get_image_type(sLine))
                {
                    mipaquete.set_content_t(sLine);
                    sLine = buffrd.readLine();
                    mipaquete.set_Length_and_fragments(sLine);
                    while (!(sLine.contains("0x0000:")))
                    {
                        sLine = buffrd.readLine();    //salto lineas para buscar los bytes
                        if (mipaquete.can_start_packet(sLine))
                        {
                            return false;
                        }
                    }
                    while (mipaquete.can_start_packet(sLine) == false) //recorremos el paquete
                    {
                        if (mipaquete.can_get_image_byte(sLine) == true)
                        {
                            isimage = true;
                            mipaquete.set_init_bytes(sLine);
                            sLine = buffrd.readLine();
                        }
                        else
                        {
                            if (isimage == true)
                            {
                                mipaquete.set_bytes(sLine);
                                sLine = buffrd.readLine();
                            }
                            else
                            {
                                sLine = buffrd.readLine();
                            }
                        }
                    }
                   return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
            }
            catch (Exception ex) 
            {
             return false;   
            }
        }

        
       
       
        
        private boolean reconstruir(BufferedReader buffrd, String sLine, paquete mipaquete)
        {
            try
            {
            mipaquete.actual_id = mipaquete.id;
            int fragments = 1;
            while (sLine.contains(Integer.toString(mipaquete.actual_id)))
            {
                if (sLine != null && mipaquete.can_start_packet(sLine))
                {
                    if (sLine.contains(Integer.toString(mipaquete.actual_id)))
                    {
                        mipaquete.actual_id = mipaquete.actual_id + 1;
                        fragments = fragments + 1;
                        while (!(sLine.contains("0x0000:")))
                        {
                            sLine = buffrd.readLine();    //salto lineas para buscar los bytes
                        }
                        while (mipaquete.can_start_packet(sLine) == false && sLine != null) //recorremos el paquete
                        {
                            mipaquete.set_bytes(sLine);
                            sLine = buffrd.readLine();
                        }
                    }
                }
            }
            System.out.println("Los que hay: "+fragments + ". Los que son: "+ mipaquete.fragments);
            if (mipaquete.fragments == fragments)
            {
            FileOutputStream fos = new FileOutputStream("/home/stark28/Imágenes/reconstruido/"+mipaquete.id + ".jpg");
            try 
            {
                fos.write(mipaquete.HexStringToBytes(mipaquete.content_byte));
                return true;
                }
            catch(Exception e)
                    {
                        return false;
                    }
            
                finally {
                fos.close();
                }

            }
            else
            {
            FileOutputStream fos = new FileOutputStream("/home/stark28/Imágenes/reconstruido/"+mipaquete.id + ".jpg");
            try 
            {
                fos.write(mipaquete.HexStringToBytes(mipaquete.content_byte));
                return true;
                }
            catch(Exception e)
                    {
                        return false;
                    }
            
                finally {
                fos.close();
                }
            }
            }
            catch(Exception ex)
            {
                return false;
            }   
        }
}



