package Clases;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.nio.IntBuffer;
import java.nio.file.Files;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_core.CV_8UC1;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.EigenFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;

/**
 *
 * @author stark28
 */
public class nu_scanner {
    
public int full;
public int lower;
public int upper;
public int breasts;


    public void reset_flags()
    {
        this.full=0;
        this.lower=0;
        this.upper=0;
        this.breasts=0;
    }
    
    public void convert_to_hsv(String origin_paht, String destiny_paht)
    {
        try
        {
            Mat bgr = org.bytedeco.javacpp.opencv_imgcodecs.imread(origin_paht);
            Mat hsv = new Mat();
            org.bytedeco.javacpp.opencv_imgproc.cvtColor(bgr, hsv, org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2HSV_FULL);
            Mat skin = new Mat();
            Mat a = new Mat(1, 1, CV_32SC4, new org.bytedeco.javacpp.IntPointer(0,58,40,0));
            Mat b = new Mat(1, 1, CV_32SC4, new org.bytedeco.javacpp.IntPointer(35,174,255,0));
            org.bytedeco.javacpp.opencv_core.inRange(hsv,a,b,skin);
            org.bytedeco.javacpp.opencv_imgcodecs.imwrite(destiny_paht, skin);
        }
        catch(Exception ex)
        {
            System.out.println("error con: " + origin_paht);
        }
        
    }
    
    public void convert_to_greyscale(String origin_paht, String destiny_paht)
    {
        try
        {
            Mat bgr = org.bytedeco.javacpp.opencv_imgcodecs.imread(origin_paht);
            Mat greyscale = new Mat();
            org.bytedeco.javacpp.opencv_imgproc.cvtColor(bgr, greyscale, org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY);
            org.bytedeco.javacpp.opencv_imgcodecs.imwrite(destiny_paht, greyscale);
        }
        catch(Exception ex)
        {
            System.out.println("error con: " + origin_paht);
        }
        
    }
    
    public void convert_to_NormRGB(String origin_paht, String destiny_paht)
    {        
        Mat bgr = org.bytedeco.javacpp.opencv_imgcodecs.imread(origin_paht);
        Mat NormRGB = new Mat();
        org.bytedeco.javacpp.opencv_imgproc.cvtColor(bgr, NormRGB, org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY);
       //org.bytedeco.javacpp.opencv_imgcodecs.imwrite(destiny_paht, hsv);
       //
        Mat skin = new Mat();
        Mat a = new Mat(1, 1, CV_32SC4, new org.bytedeco.javacpp.IntPointer(0,58,40,0));
        Mat b = new Mat(1, 1, CV_32SC4, new org.bytedeco.javacpp.IntPointer(35,174,255,0));
        org.bytedeco.javacpp.opencv_core.inRange(NormRGB,a,b,skin);
        org.bytedeco.javacpp.opencv_imgcodecs.imwrite(destiny_paht+"(1)", skin);   
    }
    public void convert_to_YCbCr(String origin_paht, String destiny_paht)
    {
        Mat bgr = org.bytedeco.javacpp.opencv_imgcodecs.imread(origin_paht);
        Mat YCbCr = new Mat();
        org.bytedeco.javacpp.opencv_imgproc.cvtColor(bgr, YCbCr, org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2YCrCb);
       //org.bytedeco.javacpp.opencv_imgcodecs.imwrite(destiny_paht, hsv);
       //
        Mat skin = new Mat();
        Mat a = new Mat(1, 1, CV_32SC4, new org.bytedeco.javacpp.IntPointer(0,58,40,0));
        Mat b = new Mat(1, 1, CV_32SC4, new org.bytedeco.javacpp.IntPointer(35,174,255,0));
        org.bytedeco.javacpp.opencv_core.inRange(YCbCr,a,b,skin);
        org.bytedeco.javacpp.opencv_imgcodecs.imwrite(destiny_paht+"(2)", skin);   
    }
    
    public double skin_scan(String paht)
    {
        try
        {
            File file= new File(paht);
            BufferedImage image = ImageIO.read(file);
            int Height = image.getHeight();
            int Width= image.getWidth();
            double wp=0;
            for (int y=0; y<Height; y++)
            {
                for (int x=0;x<Width; x++)
                {
                    int clr=  image.getRGB(x,y); 
                    int  red   = (clr & 0x00ff0000) >> 16;
                    int  green = (clr & 0x0000ff00) >> 8;
                    int  blue  =  clr & 0x000000ff;
                    if (red == 255 && green == 255 && blue==255)
                    {
                        wp=wp+1;
                    }
                }
            }
            return (wp/(Height*Width));
        }
        catch (IOException ex){return -1;}
    }
    
    public void full_body_detection(String src) //funciona
    {
        try
        {
            IplImage img = cvLoadImage(src);
            String XML_FILE = "XML/haarcascade_fullbody.xml";
            CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(XML_FILE));
            CvMemStorage storage = CvMemStorage.create();
            CvSeq sign = cvHaarDetectObjects(img,cascade,storage,1.8,5,CV_HAAR_DO_CANNY_PRUNING);
            cvClearMemStorage(storage);
            this.full= sign.total();
//            for(int i = 0; i < this.full; i++)
//                {
//                    System.out.println(".");
//                    CvRect faceRect = new CvRect(cvGetSeqElem(sign, i));
//                    cvRectangle(img, 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                CV_RGB(255,0, 0), 3, 8, 0);
//                }
//            if (this.full !=0)
//            {
//                Mat test = new Mat(img);
//                org.bytedeco.javacpp.opencv_imgcodecs.imwrite(src+"(2)", test);
//            }
        }
        catch(Exception e)
        {
            
        }
    }

    public void lower_body_detection(String src) //funciona
    {
        try
        {
            IplImage img = cvLoadImage(src);
            String XML_FILE = "XML/haarcascade_lowerbody.xml";
            CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(XML_FILE));
            CvMemStorage storage = CvMemStorage.create();
            CvSeq sign = cvHaarDetectObjects(img,cascade,storage,1.8,5,CV_HAAR_DO_CANNY_PRUNING);
            cvClearMemStorage(storage);
            this.lower = sign.total();
//            for(int i = 0; i < this.lower; i++)
//                {
//                    System.out.println(".");
//                    CvRect faceRect = new CvRect(cvGetSeqElem(sign, i));
//                    cvRectangle(img, 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                CV_RGB(255,0, 0), 3, 8, 0);
//                }
//            if (this.lower !=0)
//            {
//                Mat test = new Mat(img);
//                org.bytedeco.javacpp.opencv_imgcodecs.imwrite(src+"(3)", test);
//            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void upper_body_detection(String src) //funciona
    {
        try
        {
            IplImage img = cvLoadImage(src);
            String XML_FILE = "XML/haarcascade_upperbody.xml";
            CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(XML_FILE));
            CvMemStorage storage = CvMemStorage.create();
            CvSeq sign = cvHaarDetectObjects(img,cascade,storage,1.8,5,CV_HAAR_DO_CANNY_PRUNING);
            cvClearMemStorage(storage);
            this.upper = sign.total();
//            for(int i = 0; i < this.upper; i++)
//                {
//                    System.out.println(".");
//                    CvRect faceRect = new CvRect(cvGetSeqElem(sign, i));
//                    cvRectangle(img, 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                CV_RGB(255,0, 0), 3, 8, 0);
//                }
//            if (this.upper !=0)
//            {
//                Mat test = new Mat(img);
//                org.bytedeco.javacpp.opencv_imgcodecs.imwrite(src+"(4)", test);
//            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    
    public void breasts_detection(String src) //funciona
    {
        try
        {
            IplImage img = cvLoadImage(src);
            String XML_FILE = "XML/cascade.xml";
            CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(XML_FILE));
            CvMemStorage storage = CvMemStorage.create();
            CvSeq sign = cvHaarDetectObjects(img,cascade,storage,1.45,3,CV_HAAR_DO_CANNY_PRUNING);
            cvClearMemStorage(storage);
            this.breasts = sign.total();
//            for(int i = 0; i < this.upper; i++)
//                {
//                    System.out.println(".");
//                    CvRect faceRect = new CvRect(cvGetSeqElem(sign, i));
//                    cvRectangle(img, 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                cvPoint(faceRect.x(), faceRect.y()), 
//                                CV_RGB(255,0, 0), 3, 8, 0);
//                }
//            if (this.upper !=0)
//            {
//                Mat test = new Mat(img);
//                org.bytedeco.javacpp.opencv_imgcodecs.imwrite(src+"(4)", test);
//            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    //face_matching
    public void face_recognition(String imgfuente, String imgbuscar, String Copyin) 
    {
        String trainingDir = imgfuente;
        File root = new File(trainingDir);
        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(imgFilter);
        MatVector images = new MatVector(imageFiles.length);
        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();
        int counter = 0;
        int label1 =0;           

//cargo las imagenes al conjunto de entrenamiento
//tengo que darles un nuevo tamaño y como minimo tienen que ser 2
        for (File image : imageFiles) {
            if (image.getName().contains("directory") == false)
            {
                Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
                images.put(counter, img);
                labelsBuf.put(counter, label1);
                counter++;
                label1++;
            }
        }

        //FaceRecognizer faceRecognizer = org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer.create();
        //FaceRecognizer faceRecognizer = org.bytedeco.javacpp.opencv_face.EigenFaceRecognizer.create();
        //termino de entrenar el reconocedor
        FaceRecognizer faceRecognizer = org.bytedeco.javacpp.opencv_face.LBPHFaceRecognizer.create(); //no se rompe pero tira 0
        faceRecognizer.train(images, labels);
        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);
        
        //hacemos un bucle para buscar en todas las imagenes del directorio deseado
        File dir = new File(imgbuscar);
        String[] ficheros = dir.list();
        int x=0;
        while(ficheros != null)
        {
            if(ficheros[x].contains("directory") == false)
            {
                Mat testImage = imread(imgbuscar+ficheros[x], CV_LOAD_IMAGE_GRAYSCALE);
                faceRecognizer.predict(testImage, label, confidence);
                double conf=confidence.get();
                if (conf>80.00)
                {
                    //copiar la evidencia a otra carpeta o destino
                    java.nio.file.Path origenPath = java.nio.file.FileSystems.getDefault().getPath(imgbuscar+ficheros[x]);
                    java.nio.file.Path destinoPath = java.nio.file.FileSystems.getDefault().getPath(Copyin+ficheros[x]);
                    try
                        {
                            Files.copy(origenPath, destinoPath,java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                        }
                    catch (Exception e)
                        {
                            System.out.println("ha ocurrido un error con "+imgbuscar+ficheros[x]);
                        }
                }
            }
            x++;
        }
    }    
}
