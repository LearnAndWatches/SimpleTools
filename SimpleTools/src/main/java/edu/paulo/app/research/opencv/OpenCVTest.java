package edu.paulo.app.research.opencv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVTest {



	    public static void main(String[] arges) throws MalformedURLException, 
	IOException, Exception {
	    loadLibraries();

	    // create and print on screen a 3x3 identity matrix
	    System.out.println("Create a 3x3 identity matrix...");
	    Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
	    System.out.println("mat = " + mat.dump());

	    // prepare to convert a RGB image in gray scale
	    String location = System.getProperty("user.dir")+"\\data\\Poli.jpg";
	    System.out.print("Convert the image at " + location + " in gray scale... ");
	    // get the jpeg image from the internal resource folder
	    Mat image = Imgcodecs.imread(location);
	    // convert the image in gray scale
	    Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2GRAY);
	    // write the new image on disk
	    Imgcodecs.imwrite(System.getProperty("user.dir")+"\\data\\Poli-gray.jpg", image);
	    System.out.println("Done!");

	}

	    private static void loadLibraries() {

	    try {
	        InputStream in = null;
	        File fileOut = null;
	        String osName = System.getProperty("os.name");
//	            String opencvpath = System.getProperty("user.dir");
	        String opencvpath = "C:\\opencv46\\opencv\\build\\java\\";
	        if (osName.startsWith("Windows")) {
	            int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
	            if (bitness == 32) {
	                opencvpath = opencvpath + "\\x86\\";
	            } else if (bitness == 64) {
	                opencvpath = opencvpath + "\\x64\\";
	            } else {
	                opencvpath = opencvpath + "\\x86\\";
	            }
	        } else if (osName.equals("Mac OS X")) {
	            opencvpath = opencvpath + "Your path to .dylib";
	        }
	        System.out.println(opencvpath);
//	        System.out.println("Core.NATIVE_LIBRARY_NAME = " + Core.NATIVE_LIBRARY_NAME);
	        System.out.println("Core.NATIVE_LIBRARY_NAME = " + "opencv_java460.dll");
//	        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
	        System.load(opencvpath + "opencv_java460.dll");
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to load opencv native library", e);
	    }
	}
}