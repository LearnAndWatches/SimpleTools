package edu.paulo.app.example.io;

import edu.paulo.app.core.io.poi.MSWordWriter;

public class WriteToDocxExample {
	public static void main(String[] args) throws Exception {
        //Instantiate WordGenerator Class
		String pathOutput= System.getProperty("user.dir")+"/materials\\outputio\\GenerateDocx.docx";
		String txtWord = new StringBuilder().
				append("Java is a general purpose, high-level programming language developed by Sun Microsystems.").
				append(" The Java programming language was developed by a small team of engineers, ").
				append("known as the Green Team, who initiated the language in 1991.\n").
				append("Originally called OAK, the Java language was designed for handheld devices and set-top boxes. \n\n").
				append("Oak was unsuccessful and in 1995 Sun changed the name to Java and modified the language to take ").
				append("advantage of the burgeoning World Wide Web. \n\n").
				append("Today the Java platform is a commonly used foundation for developing and delivering content ").
				append("on the web. According to Oracle, there are more than 9 million Java developers worldwide and more ").
				append("than 3 billion mobile phones run Java.").toString();
		MSWordWriter wg = new MSWordWriter(pathOutput,txtWord);
    }
}