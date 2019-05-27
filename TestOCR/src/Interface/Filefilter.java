package Interface;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Filefilter extends FileFilter {
	String ImgPNG="PNG" ; 
	String ImgJPG="JPG" ; 
	String ImgJPEG="JPEG" ; 
	String DotIndex = "."; 
	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		if(f.isDirectory())
		{
			return true ;
		}
	
	  if(extension(f).equalsIgnoreCase("ImgPNG") || extension(f).equalsIgnoreCase("ImgJPEG")||extension(f).equalsIgnoreCase("ImgJPG"))
		{
		 // ImgFormat=extension(f);
			return true ; 
		}
		else
		return false;
	}
	
	public String extension(File F)
	{
		String Filename =F.getName();
		int IndexFile =Filename.lastIndexOf(DotIndex);
		if(IndexFile>0 && IndexFile<Filename.length()-1)
		{
			return Filename.substring(IndexFile+1);
					
		}
		else 
		{
			return "";
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ImgPNG;
			}

}
