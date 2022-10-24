package edu.paulo.app.example.io;

import java.io.File;

public class CreateChildFolderExample {
    public static void main(String[] args) {
        File srcFile = new File(System.getProperty("user.dir")+"/materials/FolderToRename/FileInTheParentFolder.txt");
        File destFile = new File(System.getProperty("user.dir")+"/materials/FolderToRename/FolderAlreadyRenamed/FileInTheChildFolder.txt");
        boolean isExists = true;

        if (!srcFile.exists()) {
            System.out.println("Src File doest not exists");
            isExists=false;
        }else
        {   //get the parent folder from the previous folder
            destFile.getParentFile().mkdirs();
        }

        boolean renamed = srcFile.renameTo(destFile);//remove file in the parent folder and create the new destination child folder from it + file
        System.out.println("Renamed: " + renamed); // true
    }
}