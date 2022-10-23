package edu.paulo.app.example.withpreparedstatement;


import edu.paulo.app.core.connectivity.JDBCInsertFromExcelPS;

public class JDBCInsertFromExcelPSExample {
	public static void main(String[] args) {
		/*Parameter order , path excel file --- sheet name ---- table name */
		JDBCInsertFromExcelPS jife = new JDBCInsertFromExcelPS("./materials/data/DataDriven.xlsx","JDBCDemoInsert", "insert_demo" );
	}
}