package edu.paulo.app.example.io;

import java.io.File;

public class GetComputerDirveExample {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("This is Drive - "+root.getAbsolutePath());
        }
    }
}
