package edu.paulo.app.example.io;

import java.io.File;

public class RenameFileExample {
    public static void main(String[] args) {
        File srcFile = new File(System.getProperty("user.dir")+"/materials/data/FileToRename.txt");
        File destFile = new File(System.getProperty("user.dir")+"/materials/data/FileAlreadyRename.txt");

        boolean renamed = srcFile.renameTo(destFile);
        System.out.println("Renamed: " + renamed);
    }
}
