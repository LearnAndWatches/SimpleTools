package edu.paulo.app.research.javaimage;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import java.awt.image.BufferedImage;

public class ImagesComparasion {

	public static void main(String[] args) {
		BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(System.getProperty("user.dir")+ "/src/main/resources/opencvsample/Ori-1.jpg");
		BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(System.getProperty("user.dir")+ "/src/main/resources/opencvsample/Ori-1-Compress.jpg");
		ImageComparison comparison = new ImageComparison(expectedImage, actualImage);
		ImageComparisonResult result = comparison.compareImages();
		expectedImage.flush();
		actualImage.flush();
		System.out.println(ImageComparisonState.MATCH == result.getImageComparisonState());
	}
}