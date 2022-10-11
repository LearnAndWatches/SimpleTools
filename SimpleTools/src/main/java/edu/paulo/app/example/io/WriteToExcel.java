package edu.paulo.app.example.io;

import edu.paulo.app.core.io.poi.ExcelWriter;

import java.util.ArrayList;
import java.util.List;

public class WriteToExcel {

    // any exceptions need to be caught 
    public static void main(String[] args) throws Exception 
    { 
    	String pathOutout = System.getProperty("user.dir")+"\\outputio\\SampleManual.xlsx";
    	List<String[]> list = new ArrayList<String[]>();
    	
    	String[] strArr = new String[4];
    	strArr[0] = "id";
    	strArr[1] = "nama_depan";
    	strArr[2] = "nama_tengah";
    	strArr[3] = "nama_belakang";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "1";
    	strArr[1] = "Aditya";
    	strArr[2] = "Krisna";
    	strArr[3] = "Pamungkas";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "2";
    	strArr[1] = "Alamanda";
    	strArr[2] = "Cathartica";
    	strArr[3] = "Florencea ";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "3";
    	strArr[1] = "Andhika";
    	strArr[2] = "Bagaskara Putra";
    	strArr[3] = "Nusantara";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "4";
    	strArr[1] = "Ayu";
    	strArr[2] = "Maylina";
    	strArr[3] = "Wati";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "5";
    	strArr[1] = "Mafasyafa";
    	strArr[2] = "Annisa";
    	strArr[3] = "Zukhruff";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "6";
    	strArr[1] = "Muhammad";
    	strArr[2] = "Ahyar Ibrahim";
    	strArr[3] = " Aldebaran";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "7";
    	strArr[1] = "Nico";
    	strArr[2] = "Ardy";
    	strArr[3] = "Hermawan";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "8";
    	strArr[1] = "Novri";
    	strArr[2] = "Anto";
    	strArr[3] = "Batara";
    	list.add(strArr);
    	
    	strArr = new String[4];
    	strArr[0] = "9";
    	strArr[1] = "Ru";
    	strArr[2] = "Nan";
    	strArr[3] = "To";
    	list.add(strArr);
    	
    	ExcelWriter k = new ExcelWriter(list, pathOutout, "Contoh_2");
    }
}
