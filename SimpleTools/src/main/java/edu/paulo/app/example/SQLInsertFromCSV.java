package edu.paulo.app.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInsertFromCSV {

	private File file;
	private FileInputStream fis;
	private String xx ;
	private StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) {
    	SQLInsertFromCSV jifc = new SQLInsertFromCSV();
    	jifc.executeFile("./data/DataDriven.csv","./data/DataDriven.txt","insert_demo");
    }
    
    public void executeFile(String pathCsv,String pathTxt, String tableName)
    {
    	try {
            xx = getInsertQuery(pathCsv,tableName);
            file = new File(pathTxt);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(xx.toString());/*WRITE String to .txt file as log the result of parsing data from csv file*/
        }           
            System.out.println(xx);
        } catch (IOException ex) {
            Logger.getLogger(SQLInsertFromCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readWholeFile(String filename) throws IOException {
        file = new File(filename);
        fis = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fis.read(data);
        fis.close();
        return new String(data, "UTF-8");
    }

    public String getInsertQuery(String filename, String tableName) throws IOException {
        String content = readWholeFile(filename);
        String[] lines = content.split("");

        if (lines.length < 2) return ""; // or throw new Exception("not enough lines);

        sb.append("INSERT INTO ").append(tableName).append("(").append(lines[0]).append("\b) VALUES ");
        for (int i=1; i<lines.length; i++) {
            String[] parts = lines[i].split(",");
            
            sb.append("(");

            for (int k=0; k<parts.length; k++) {
                sb.append("'"+parts[k]+"'");                
                if (k != parts.length-1)
                    sb.append(", ");                
            }

            sb.append(")");            
            if (i != lines.length-1)
                sb.append(", ");
        }
        
        return sb.toString();
    }
}