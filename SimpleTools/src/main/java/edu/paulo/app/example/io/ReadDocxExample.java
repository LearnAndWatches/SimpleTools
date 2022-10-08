package edu.paulo.app.example.io;

import edu.paulo.app.util.MSWordReader;

public class ReadDocxExample {

	public static void main(String[] args) {
		MSWordReader d = new MSWordReader();
		System.out.println(d.getData(System.getProperty("user.dir")+"\\data\\sample.docx"));
	}
}