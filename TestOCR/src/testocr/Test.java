package testocr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Test {
	public static void main(String[] args)  throws IOException{
		//creer une image ans un fichier
		File imageFile =new File("image/bio.png");
		FileOutputStream out=null;
	
		ITesseract instance = new Tesseract();
		instance.setDatapath("D:\\ENSAO\\PFA-master\\Tess4J\\tessdata");
	
		try {
			instance.setLanguage("fr/fra");
			String result = instance.doOCR(imageFile);
            FileWriter file=new FileWriter("D:\\ENSAO\\result.txt");  
            file.write(result);
            file.close();
			System.out.println(result);
		}catch(TesseractException e) {
			System.err.println(e.getMessage());
		}
	
	}

}
