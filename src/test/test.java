package test;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class test {
	public static String path = "E:/exam/";
	public static void createXML(String coursename) {
    	Document document = DocumentHelper.createDocument();
    	document.addElement("exams");
    	File file=new File(path+coursename+".xml");
    	writeXML(document,file);
    }

	private static int writeXML(Document document,File file) {
    	int value=0;
    	OutputFormat format=OutputFormat.createPrettyPrint();
    	format.setEncoding("UTF-8");
    	XMLWriter writer=null;
    	try {
    		writer =new XMLWriter(new FileOutputStream(file),format.createPrettyPrint());
    		writer.write(document);
    		value=1;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return value;
    }
	public static int addExam(Exam exam) {
    	int value=0;
    	File file =new File(path+"exam.xml");
    	if(!file.exists()) {
    		createXML("exam");
    		file = new File(path+"exam.xml");
    	}
    	Document document=null;
    	try {
    		SAXReader reader=new SAXReader();
    		document = reader.read(file);
    		Element rootElement = document.getRootElement();
    		Element cElement = rootElement.addElement("exam");
    		cElement.addAttribute("name", exam.getEname());
    		Element courseElement=cElement.addElement("course");
    		courseElement.setText(exam.getCourse());
    		Element tnameElement=cElement.addElement("tname");
    		tnameElement.setText(exam.getTname());
    		Element tnumberElement=cElement.addElement("tnumber");
    		tnumberElement.setText(exam.getTnumber());
    		Element timeElement=cElement.addElement("time");
    		timeElement.setText(exam.getTime());
    		}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        	value=writeXML(document,file);
        return value;
    }
	public static void main(String[] args) {
		Exam exam=new Exam();
		exam.setCourse("数据库");
		exam.setEname("数据库期中考试");
		exam.setTime("100");
		exam.setTname("小黄");
		exam.setTnumber("1001");
		if(addExam(exam)==1) {
			System.out.println("ok");
		}
		else {
			System.out.println("no");
		}
		
	}
}
