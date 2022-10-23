package edu.paulo.app.example.io;

import edu.paulo.app.core.io.file.ReadTextFile;

public class ReadTextFileExample {

    public static void main(String[] args) {
        ReadTextFile rtf = new ReadTextFile(System.getProperty("user.dir")+ "/materials/data/MailHTMLExample.txt");
        System.out.println(rtf.getContentFile());
    }
}