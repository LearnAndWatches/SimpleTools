package edu.paulo.app.example.report.sampleone;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("deprecation")
public class JasperReportFill {

	@SuppressWarnings("unchecked")
	   public static void main(String[] args) {
	      String sourceFileName = System.getProperty("user.dir")+"\\data\\jasper_report_template.jasper";
	      String printFileName = null;
	      DataBeanList DataBeanList = new DataBeanList();
	      ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
	      JRBeanCollectionDataSource beanColDataSource =
	         new JRBeanCollectionDataSource(dataList);

	      Map parameters = new HashMap();
	      try {
	         printFileName = JasperFillManager.fillReportToFile(sourceFileName,
	            parameters, beanColDataSource);
	         if (printFileName != null) {
	            /**
	             * 1- export to PDF
	             */
	            JasperExportManager.exportReportToPdfFile(printFileName,
						System.getProperty("user.dir")+ "/src/main/resources/outputio/sample_report.pdf");

	            /**
	             * 2- export to HTML
	             */
	            JasperExportManager.exportReportToHtmlFile(printFileName,
	            		System.getProperty("user.dir")+ "/src/main/resources/outputio/sample_report.html");

	            /**
	             * 3- export to Excel sheet
	             */
	            JRXlsExporter exporter = new JRXlsExporter();

	            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
	               printFileName);
	            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						System.getProperty("user.dir")+ "/src/main/resources/outputio/sample_report.xls");

	            exporter.exportReport();
	         }
	      } catch (JRException e) {
	         e.printStackTrace();
	      }
	   }
}