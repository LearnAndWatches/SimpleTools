package edu.paulo.app.example.io;

import java.io.File;
import java.util.Date;

public class FileInfoExample {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir")+"/materials/data/DataDriven.txt");
//        File file = new File(System.getProperty("user.dir")+"/materials/data");

        //is the path of this path exist ?
        System.out.println("Path exists : " + file.exists());

        if (file.exists()) {
            //to check is this path is file or directory ?
            System.out.println("isDirectory : " + file.isDirectory());

            //the attribute of this directory/file is hidden or not>
            System.out.println("isHidden : " + file.isHidden());

            //get the name of the file / directory (last)
            System.out.println("Simple Name: " + file.getName());

            //get the absolute path information
            System.out.println("Absolute Path: " + file.getAbsolutePath());

            //the size of file by bytes
            System.out.println("Length : " + file.length() + " (bytes)");

            // the last modified information of the file/directory
            long lastMofifyInMillis = file.lastModified(); // milliseconds
            Date lastModifyDate = new Date(lastMofifyInMillis);
            System.out.println("Last modify date: " + lastModifyDate);
        }
    }
}
