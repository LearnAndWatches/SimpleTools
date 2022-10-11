package edu.paulo.app.example.io;

import edu.paulo.app.core.io.file.DeletingFile;

public class DeleteFileExample {
    public static void main(String[] args) {
        DeletingFile df = new DeletingFile();
        df.deletingFile(System.getProperty("user.dir")+"/data/FileToDeleteExample.txt");
    }
}