/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.*;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import javax.swing.ImageIcon;
import java.io.File;
import java.nio.file.Files;
import javax.swing.JOptionPane;


/**
 *
 * @author stark28
 */
public class principal extends javax.swing.JFrame {

    /**
     * Creates new form principal
     */
    public principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Btm_reconstruir = new javax.swing.JButton();
        btm_texturas = new javax.swing.JButton();
        txt_analizar = new javax.swing.JTextField();
        btm_objeto = new javax.swing.JButton();
        txt_resultados = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btm_profundo = new javax.swing.JButton();
        btm_reconocimiento = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_aprender = new javax.swing.JTextField();
        conte_menu = new javax.swing.JMenuBar();
        menubtm_configurar = new javax.swing.JMenu();
        item_bd = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Btm_reconstruir.setText("Reconstruir Imagenes");
        Btm_reconstruir.setName("btmanalizarcaptura"); // NOI18N
        Btm_reconstruir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btm_reconstruirActionPerformed(evt);
            }
        });

        btm_texturas.setToolTipText("");
        btm_texturas.setLabel("analisis por textura");
        btm_texturas.setName("btm_animg"); // NOI18N
        btm_texturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btm_texturasActionPerformed(evt);
            }
        });

        btm_objeto.setText("analisis por objeto");
        btm_objeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btm_objetoActionPerformed(evt);
            }
        });

        jLabel1.setText("Introduzca el direcotrio o archivo a analizar");

        jLabel2.setText("¿Donde deben guardarse los resultados?");

        btm_profundo.setText("analisis profundo");
        btm_profundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btm_profundoActionPerformed(evt);
            }
        });

        btm_reconocimiento.setText("analisis por reconocimiento facial");
        btm_reconocimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btm_reconocimientoActionPerformed(evt);
            }
        });

        jLabel3.setText("¿De donde se puede aprender para identificar un rostro?");

        menubtm_configurar.setText("Configuracion");

        item_bd.setText("base de datos");
        item_bd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_bdActionPerformed(evt);
            }
        });
        menubtm_configurar.add(item_bd);

        conte_menu.add(menubtm_configurar);

        setJMenuBar(conte_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_analizar)
                            .addComponent(txt_resultados)
                            .addComponent(txt_aprender, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btm_profundo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btm_reconstruir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btm_texturas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btm_objeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btm_reconocimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_resultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_aprender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(Btm_reconstruir)
                .addGap(18, 18, 18)
                .addComponent(btm_texturas)
                .addGap(18, 18, 18)
                .addComponent(btm_objeto)
                .addGap(18, 18, 18)
                .addComponent(btm_profundo)
                .addGap(18, 18, 18)
                .addComponent(btm_reconocimiento)
                .addContainerGap())
        );

        btm_objeto.getAccessibleContext().setAccessibleName("btmanlisisXob");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btm_reconstruirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btm_reconstruirActionPerformed
        //Configuramos conexion a bd
        String host="";
        String port="";
        String bd="";
        try
        {
            String name = System.getProperty("user.dir")+"/config_file.txt";
            BufferedReader buffr = null;
            FileReader filer = null;
            filer = new FileReader(name);
            buffr = new BufferedReader(filer);
            host=buffr.readLine();
            port=buffr.readLine();
            bd=buffr.readLine();
            buffr=null;
            filer = null;
        }
        catch(Exception ex)
        {
            
        }
        //analizamos archivos
        String origen=txt_analizar.getText();
        String destino=txt_resultados.getText();
        if (!(origen.equals("")) && !(destino.equals("")))
        {
            File dir = new File(origen);
            File dir2 = new File(destino);
            if (dir.exists() && dir2.exists())
            {        
                    try{
                        //abrir archivo y leer imagen
                        String FILENAME = origen;
                        //String FILENAME = "/home/stark28/planos/prueba_03_03.txt";
                        BufferedReader br = null;
                        FileReader fr = null;
                        fr = new FileReader(FILENAME);
                        br = new BufferedReader(fr);
                        String sCurrentLine="";
                        paquete mipaquete = new paquete();
                        while (sCurrentLine != null){
                        //while ((sCurrentLine = br.readLine()) != null){                            
                            try{
                                boolean result = recuperar(br, sCurrentLine, mipaquete);
                                if (result == true){
                                    result = reconstruir(br, sCurrentLine, mipaquete,destino);
                                    if (result == true){
                                        System.out.println("ok: " + mipaquete.id);
                                        mipaquete.host=host;
                                        mipaquete.port=port;
                                        mipaquete.bd=bd;
                                        mipaquete.insertarenbd();
                                        paquete mipack = new paquete();
                                        System.out.println(sCurrentLine);
                                        }
                                    else{
                                        System.out.println("emergencia: " + mipaquete.id);
                                        paquete mipack = new paquete();
                                        }
                                    }
                                
                                else{
                                    sCurrentLine = br.readLine();
                                    }
                                }
                            catch(Exception ex)
                                {
                                    System.out.println(ex);
                                    sCurrentLine = br.readLine();
                                }
                }
                    Image im = mipaquete.restore_image();
//                    Image myImg = im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
//                    ImageIcon newImage = new ImageIcon(myImg);
//                    label.setIcon(newImage);
                    br.close();
                    fr.close();
            }
                    catch(Exception ex){
                ex.printStackTrace();
            }
            }
            dir=null;
            dir2=null;
        }
        System.gc();
    }//GEN-LAST:event_Btm_reconstruirActionPerformed

    private void btm_texturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btm_texturasActionPerformed

        //nu_scanner scanner = new nu_scanner();
        //funciona 
        String origen=txt_analizar.getText();
        String intermedio=txt_analizar.getText();
        String destino=txt_resultados.getText();
        if (!(origen.equals("")) && !(destino.equals("")))
        {
            File dir = new File(origen);
            File dir2 = new File(destino);
            if (dir.exists() && dir2.exists())
            {
                String[] ficheros = dir.list();
                for(int x=1; x<ficheros.length;x++)
                {
                    nu_scanner.getnu_scanner().convert_to_hsv(origen+ficheros[x], intermedio+"temp"+ficheros[x]);
                    //scanner.convert_to_hsv(origen+ficheros[x], intermedio+"temp"+ficheros[x]);
                    //probar con 0.19
                    double y = nu_scanner.getnu_scanner().skin_scan(intermedio+"temp"+ficheros[x]);
                    //double y = scanner.skin_scan(intermedio+"temp"+ficheros[x]);
                    if (y>0.15)  //bien 17/19 mal 5/25  bien:11/26  mal 10/26
                    {
                        System.out.println("ALERTA!! " + origen+ficheros[x]);
                        //copiar la evidencia a otra carpeta o destino
                        java.nio.file.Path origenPath = java.nio.file.FileSystems.getDefault().getPath(origen+ficheros[x]);
                        java.nio.file.Path destinoPath = java.nio.file.FileSystems.getDefault().getPath(destino+ficheros[x]);
                        try
                        {
                            Files.copy(origenPath, destinoPath,java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                        }
                        catch (Exception e)
                        {
                            System.out.println("ha ocurrido un error con "+origen+ficheros[x]);
                        }
                    }
                    File temp = new File(intermedio+"temp"+ficheros[x]);
                    temp.delete();
                    
                }
            }
            dir=null;
            dir2=null;
        }        
//        scanner=null;
        System.gc();
    }//GEN-LAST:event_btm_texturasActionPerformed

    private void btm_objetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btm_objetoActionPerformed
        // TODO add your handling code here:
        //nu_scanner scanner = new nu_scanner();
        //funciona
        String origen=txt_analizar.getText();
        String destino=txt_resultados.getText();
        String intermedio=txt_analizar.getText();
        if (!(origen.equals("")) && !(destino.equals("")))
        {
            File dir = new File(origen);
            File dir2 = new File(destino);
            if (dir.exists() && dir2.exists())
            {
                String[] ficheros = dir.list();
                //int x=0;
                //while(ficheros != null)
                for(int x=1; x<ficheros.length;x++)
                {
                    System.out.println(x);
            //scanner.convert_to_greyscale(origen+ficheros[x],destino+"temp"+ficheros[x]);
            /*
            scanner.convert_to_hsv(origen+ficheros[x],destino+"temp"+ficheros[x]);
            scanner.full_body_detection(destino+"temp"+ficheros[x]);
            scanner.lower_body_detection(destino+"temp"+ficheros[x]);
            scanner.upper_body_detection(destino+"temp"+ficheros[x]);
            */
            //scanner.convert_to_hsv(origen+ficheros[x], intermedio+"temp"+ficheros[x]);
                    nu_scanner.getnu_scanner().convert_to_hsv(origen+ficheros[x],destino+"temp"+ficheros[x]);
                    nu_scanner.getnu_scanner().full_body_detection(destino+"temp"+ficheros[x]);
                    nu_scanner.getnu_scanner().lower_body_detection(destino+"temp"+ficheros[x]);
                    nu_scanner.getnu_scanner().upper_body_detection(destino+"temp"+ficheros[x]);
                    java.nio.file.Path destinoPath2 = java.nio.file.FileSystems.getDefault().getPath(destino+"temp"+ficheros[x]);
            try
                {
                    Files.deleteIfExists(destinoPath2);
                }
            catch (Exception e)
                {
                    System.out.println("ha ocurrido un error con "+origen+ficheros[x]);
                }
                    if (nu_scanner.getnu_scanner().full != 0 || nu_scanner.getnu_scanner().lower != 0 || nu_scanner.getnu_scanner().upper != 0 ) //bien 18/26 (70%), 15/19(78%)   y  mal 7/26(27) y 14/25(56)
                    {
                        //System.out.println("ALERTA!! " + origen+ficheros[x]);
                        //copiar la evidencia a otra carpeta o destino
                        java.nio.file.Path origenPath = java.nio.file.FileSystems.getDefault().getPath(origen+ficheros[x]);
                        java.nio.file.Path destinoPath = java.nio.file.FileSystems.getDefault().getPath(destino+ficheros[x]);
                        try
                        {
                            System.out.println(origen+ficheros[x]);
                            Files.copy(origenPath, destinoPath,java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                            Files.deleteIfExists(destinoPath2);
                        }
                        catch (Exception e)
                        {
                            System.out.println("ha ocurrido un error con "+origen+ficheros[x]);
                        }
                    }
            //File temp = new File(destino+"temp"+ficheros[x]);
            //temp.delete();
                    //x=x+1;
                    nu_scanner.getnu_scanner().reset_flags();
                }
                System.out.println("Completo!!!");
            }
            dir=null;
            dir2=null;
        }
        //scanner=null;
        System.gc();
    }//GEN-LAST:event_btm_objetoActionPerformed
//adsadasd
    
    private void btm_profundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btm_profundoActionPerformed
        // TODO add your handling code here:

        //nu_scanner scanner = new nu_scanner();
        //funciona 
        String origen=txt_analizar.getText();
        String intermedio=txt_analizar.getText();
        String destino=txt_resultados.getText();
        if (!(origen.equals("")) && !(destino.equals("")))
        {
            File dir = new File(origen);
            File dir2 = new File(destino);
            if (dir.exists() && dir2.exists())
            {
                String[] ficheros = dir.list();
                for(int x=1; x<ficheros.length;x++)
                {
                    nu_scanner.getnu_scanner().convert_to_hsv(origen+ficheros[x], intermedio+"temp"+ficheros[x]);
                    double y = nu_scanner.getnu_scanner().skin_scan(intermedio+"temp"+ficheros[x]);
                    if (y>0.15)
                    {
                        nu_scanner.getnu_scanner().full_body_detection(intermedio+"temp"+ficheros[x]);
                        nu_scanner.getnu_scanner().lower_body_detection(intermedio+"temp"+ficheros[x]);
                        nu_scanner.getnu_scanner().upper_body_detection(intermedio+"temp"+ficheros[x]);
                        java.nio.file.Path destinoPath2 = java.nio.file.FileSystems.getDefault().getPath(destino+"temp"+ficheros[x]);
                        try
                            {
                                Files.deleteIfExists(destinoPath2);
                            }
                        catch (Exception e)
                            {
                                System.out.println("ha ocurrido un error con "+origen+ficheros[x]);
                            }
                        if (nu_scanner.getnu_scanner().full != 0 || nu_scanner.getnu_scanner().lower != 0 || nu_scanner.getnu_scanner().upper != 0)
                            {
                                System.out.println("ALERTA!! " + origen+ficheros[x]);
                                //copiar la evidencia a otra carpeta o destino
                                java.nio.file.Path origenPath = java.nio.file.FileSystems.getDefault().getPath(origen+ficheros[x]);
                                java.nio.file.Path destinoPath = java.nio.file.FileSystems.getDefault().getPath(destino+ficheros[x]);
                                try
                                {
                                    Files.copy(origenPath, destinoPath,java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                                }
                                catch (Exception e)
                                {
                                    System.out.println("ha ocurrido un error con "+origen+ficheros[x]);
                                }
                            }
                    }
                    File temp = new File(intermedio+"temp"+ficheros[x]);
                    temp.delete();
                }                        
            }
            dir=null;
            dir2=null;
        }
        //scanner=null;
        System.gc();
    }//GEN-LAST:event_btm_profundoActionPerformed

    private void btm_reconocimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btm_reconocimientoActionPerformed
        //nu_scanner scanner = new nu_scanner();
        String origen=txt_analizar.getText();
        String destino=txt_resultados.getText();
        String entrenamiento=txt_aprender.getText();
        if (!(origen.equals("")) && !(destino.equals("")) && !(entrenamiento.equals("")))
        {
            File ef1 = new File(origen);
            File ef2 = new File(destino);
            File ef3 = new File(entrenamiento);
            if (ef1.exists() && ef2.exists() && ef3.exists())
            {
                nu_scanner.getnu_scanner().face_recognition(entrenamiento,origen,destino);
                ef1=null;
                ef2=null;
                ef3=null;
                //scanner = null;
                System.gc();
            }
        }
    }//GEN-LAST:event_btm_reconocimientoActionPerformed

    private void item_bdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_bdActionPerformed
        // TODO add your handling code here:
        Interfaz.base_datos bd = new Interfaz.base_datos();
        bd.setVisible(true);
    }//GEN-LAST:event_item_bdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btm_reconstruir;
    private javax.swing.JButton btm_objeto;
    private javax.swing.JButton btm_profundo;
    private javax.swing.JButton btm_reconocimiento;
    private javax.swing.JButton btm_texturas;
    private javax.swing.JMenuBar conte_menu;
    private javax.swing.JMenuItem item_bd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu menubtm_configurar;
    public javax.swing.JTextField txt_analizar;
    private javax.swing.JTextField txt_aprender;
    public javax.swing.JTextField txt_resultados;
    // End of variables declaration//GEN-END:variables

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

                
        private boolean reconstruir(BufferedReader buffrd, String sLine, paquete mipaquete,String path)
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
                FileOutputStream fos = new FileOutputStream(path+mipaquete.id + ".jpg");
            //FileOutputStream fos = new FileOutputStream("/home/stark28/Imágenes/reconstruido/"+mipaquete.id + ".jpg");
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
                FileOutputStream fos = new FileOutputStream(path+mipaquete.id + ".jpg");
            //FileOutputStream fos = new FileOutputStream("/home/stark28/Imágenes/reconstruido/"+mipaquete.id + ".jpg");
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
