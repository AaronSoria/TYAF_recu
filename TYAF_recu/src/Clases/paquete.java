package Clases;
import com.mongodb.*;
import org.bson.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.DBObject;
import java.math.*;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;



/**
 *
 * @author stark28
 */
public class paquete {
        
    
        public String ip_origin;
        public String ip_destiny;
        public int id;
        public String protocol;
        public int Length;
        public int fragments;
        public String content_type;
        public String content_byte;
        public int actual_id;
        public String host;
        public String port;
        public String bd;

        public boolean can_start_packet(String chain)
        {
            if (chain != null && chain.contains("IP"))
            {
                return true; 
            }
            else
            {
                return false; //no se pudo iniciar el paquete
            }
        }

        public void set_id(String chain)
        {
            int Ipos = chain.indexOf("id")+3;
            int Fpos = chain.indexOf(",", Ipos);
            String cad = chain.substring(Ipos, Fpos);
            this.id = Integer.parseInt(cad);
        }

        public void set_ip(String chain)
        {
            int Ipos = chain.indexOf(">");
            this.ip_origin = chain.substring(0, Ipos-1);
            this.ip_origin = ip_origin.replace(" ", "");
            int Fpos = chain.indexOf(":", Ipos)-1;
            this.ip_destiny = chain.substring(Ipos+2, Fpos);
        }

        public void set_Length_and_fragments(String chain)
        {
            int Ipos = chain.indexOf(":")+2;
            int Fpos = chain.length();
            this.Length = Integer.parseInt(chain.substring(Ipos, Fpos));
            if (this.Length % 1460 == 0)
            {
                this.fragments = this.Length / 1460; //reviar division entera
            }
            else
            {
                this.fragments = (this.Length / 1460) + 1;
            }           
        }

        public void set_content_t(String chain)
        {
            int Ipos = chain.indexOf(":") +2;
            int Fpos = chain.length();
            this.content_type = chain.substring(Ipos, Fpos);
        }

        //public void set_init_bytes(string chain)
        //{
        //    int pos = chain.IndexOf("0a") + 1;
        //    pos = chain.IndexOf("0a", pos);     //hay algunos que tiene en una sola linea el 0aff ver para png y otros tipos de imagen
        //    string subbyte = "";
        //    subbyte = chain.Substring(pos, 4);
        //    pos = pos + 5;
        //    content_byte = content_byte + subbyte;
        //    while (!(chain[pos] == ' ' && chain[pos - 1] == ' '))
        //    {
        //        subbyte = chain.Substring(pos, 4);
        //        pos = pos + 5;
        //        content_byte = content_byte + subbyte;
        //    }
        //}

        //public void set_init_bytes(string chain)
        //{
        //    int pos = chain.IndexOf("0aff");
        //    string subbyte = "";
        //    subbyte = chain.Substring(pos, 4);
        //    pos = pos + 5;
        //    content_byte = content_byte + subbyte;
        //    while (!(chain[pos] == ' ' && chain[pos - 1] == ' '))
        //    {
        //        subbyte = chain.Substring(pos, 4);
        //        pos = pos + 5;
        //        content_byte = content_byte + subbyte;
        //    }
        //}

        public void set_init_bytes(String chain)
        {
            int pos = chain.indexOf("0a") + 1;
            pos = chain.indexOf("0a", pos);     //hay algunos que tiene en una sola linea el 0aff ver para png y otros tipos de imagen
            String subbyte = "";
            int endpos = chain.indexOf("  ", pos);
            subbyte = chain.substring(pos, endpos);
            this.content_byte = subbyte.replace(" ", "");

        }

        public void set_bytes(String chain)
        {
            int pos = chain.indexOf(":")+3;
            String subbyte = "";
            int count =0;
            while (!(subbyte.contains("  ")) && count <9)
            {
                this.content_byte = this.content_byte + subbyte;
                subbyte = chain.substring(pos, pos+4);
                pos = pos + 5;
                count = count + 1;
            }
        }

        public boolean can_get_image_type(String chain)
        {
            if (chain.contains("Content-Type: image"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public boolean can_get_image_Length(String chain)
        {
            if (chain.contains("Content-Length:"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //public bool can_get_image_byte(string chain)
        //{
        //    if (chain.Contains("bytes") && chain.Contains("0aff"))
        //    {
        //        return true;
        //    }
        //    else
        //    {
        //        return false; 
        //    }
        //} modificado 18/02/18

        //public bool can_get_image_byte(string chain)
        //{
        //    if (chain.Contains("0aff"))
        //    {
        //        return true;
        //    }
        //    else
        //    {
        //        return false;
        //    }
        //}

        public boolean can_get_image_byte(String chain)
        {
            int pos = chain.indexOf("0a");
            if (pos != -1) 
            {
                pos = chain.indexOf("0a", pos + 1);
                if (pos != -1)
                {
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

        public Image restore_image()
        {
            if (this.content_byte != "")
            {
                    byte[] img = this.HexStringToBytes(this.content_byte);
                    //Resize The ImageIcon
                    ImageIcon image = new ImageIcon(img);
                    Image im = image.getImage();
                    return im;

            }
            else
            {
                return null;
            }
        }

        public byte[] HexStringToBytes(String hex)
        {          
            byte[] data = new byte[hex.length() / 2];
            int j = 0;
            for (int i = 2; i < hex.length(); i += 2)
            {
                        data[j]=(byte)((Character.digit(hex.charAt(i), 16) << 4)
                             + Character.digit(hex.charAt(i+1), 16));
                ++j;
            }
            return data;
        }
        
        public void insertarenbd()
        {
            //crear cliente de mongo
            MongoClient mongo = new MongoClient( this.host , Integer.parseInt(this.port) );
            //crear credenciales
            MongoCredential credential;
            credential = MongoCredential.createCredential("Aaron", "tyaf",
                    "0123456789".toCharArray());
            //Accedemos a la bd
            MongoDatabase database = mongo.getDatabase(this.bd);
            //definifmos la coleccion en la que queremos guardar el documento
            com.mongodb.client.MongoCollection<Document> coleccion = database.getCollection("paquete");
            Document documento = new Document()
                    .append("ip_origin", this.ip_origin)
                    .append("ip_destiny", this.ip_destiny)
                    .append("id", this.id)
                    .append("protocol", this.protocol)
                    .append("Lenght",this.Length)
                    .append("fragments",this.fragments)
                    .append("content_type", this.content_type)
                    .append("content_byte", this.content_byte);
            coleccion.insertOne(documento);
            mongo.close();
        }
    }
    

