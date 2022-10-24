package edu.paulo.app.example.io;

import edu.paulo.app.core.io.file.FilteringFiles;

import java.io.File;

public class FileFilterExample {

    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir")+"/materials/data");
        File[] txtFiles = dir.listFiles(new FilteringFiles());
        //only get files with .txt extension from the specific directory
        for (File txtFile : txtFiles) {
            System.out.println(txtFile.getAbsolutePath());
        }
    }
}