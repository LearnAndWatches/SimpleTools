package edu.paulo.app.research.opencv;

import edu.paulo.app.util.OpenCVLibraries;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.FastFeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class DetectingKeyPoints{
   public static void main(String args[]) throws Exception {
      //Loading the OpenCV core library
	  OpenCVLibraries ocvLib = new OpenCVLibraries();
	  ocvLib.loadLibraries();
      //Reading the contents of the image
      String file = System.getProperty("user.dir")+"/opencvsample/Ori-1.jpg";
      Mat src = Imgcodecs.imread(file);
      //Reading the key points of the image
      Mat dst = new Mat();
      MatOfKeyPoint matOfKeyPoints = new MatOfKeyPoint();
      FastFeatureDetector featureDetector = FastFeatureDetector.create();
      featureDetector.detect(src, matOfKeyPoints);
      //Drawing the detected key points
      Features2d.drawKeypoints(src, matOfKeyPoints, dst, new Scalar(0, 0, 255));
      HighGui.imshow("Feature Detection", dst);
      HighGui.waitKey();
   }
}