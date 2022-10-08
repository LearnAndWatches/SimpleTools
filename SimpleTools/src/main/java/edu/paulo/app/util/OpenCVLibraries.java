package edu.paulo.app.util;

import java.io.File;
import java.io.InputStream;

public class OpenCVLibraries {

	/*
	 * LINK TO DOWNLOAD OPEN CV https://sourceforge.net/projects/opencvlibrary/files/
	 * https://sourceforge.net/projects/opencvlibrary/files/3.4.9/
	 * mvn install:install-file -Dfile=C:\opencv46\opencv\build\java\opencv-460.jar -DgroupId=org -DartifactId=opencv -Dversion=4.6.0 -Dpackaging=jar
	 * 
	 * EXTRACT TO FOLDER
	 * 
	 */
	private ConfigProperties cProp = new ConfigProperties();
	
	public void loadLibraries() {

	    try {
	        String osName = System.getProperty("os.name");
	        String opencvpath = cProp.getOpenCVPath();
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
	            opencvpath = opencvpath + "Your path to .dylib";/* this only for macOS*/
	        }
	        System.out.println(opencvpath);
	        System.out.println("Core.NATIVE_LIBRARY_NAME = " + cProp.getOpenCVDLL());
	        System.load(opencvpath + cProp.getOpenCVDLL());
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to load opencv native library", e);
	    }
	}
}
