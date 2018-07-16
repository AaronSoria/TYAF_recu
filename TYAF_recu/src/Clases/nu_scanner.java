package Clases;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;






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
    
    
}
