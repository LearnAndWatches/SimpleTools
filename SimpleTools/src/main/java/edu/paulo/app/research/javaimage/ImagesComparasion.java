package edu.paulo.app.research.javaimage;

import java.awt.image.BufferedImage;

/*
 * https://github.com/romankh3/image-comparison
 */
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

public class ImagesComparasion {

	public static void main(String[] args) {
		BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources("C:\\Users\\pollc\\OneDrive\\Desktop\\hahaha\\Ori-1.jpg");
		BufferedImage actualImage = ImageComparisonUtil.readImageFromResources("C:\\Users\\pollc\\OneDrive\\Desktop\\hahaha\\Ori-1 - Copy.jpg");
		ImageComparison comparison = new ImageComparison(expectedImage, actualImage);
		ImageComparisonResult result = comparison.compareImages();
		System.out.println(ImageComparisonState.MATCH == result.getImageComparisonState());
	}
}