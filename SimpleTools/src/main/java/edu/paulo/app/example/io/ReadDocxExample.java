package edu.paulo.app.example.io;

import edu.paulo.app.core.io.poi.MSWordReader;

public class ReadDocxExample {

	public static void main(String[] args) {
		MSWordReader d = new MSWordReader();
		System.out.println(d.getData(System.getProperty("user.dir")+"/materials\\data\\sample.docx"));
	}
}