package traitement;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageResizer {

    public static File resize(File inputFile,File outputFile)
            throws IOException {
    	String path=inputFile.getAbsolutePath();
    	
        // reads input image
        BufferedImage inputImage = ImageIO.read(inputFile);
        Image icon = new ImageIcon(inputFile.getAbsolutePath()).getImage();
        
        //extract inputImage Width and Height
        int scaledWidth = (int) (inputImage.getWidth() * 1.5);
        int scaledHeight = (int) (inputImage.getHeight() * 1.5);
        
        icon.getScaledInstance(scaledWidth,scaledHeight, Image.SCALE_SMOOTH);
        
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
        
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = path.substring(path.lastIndexOf(".") + 1);
        
        // writes to output file
        ImageIO.write(outputImage, formatName,outputFile );
        
        //return outputFile
        return outputFile;
       
    }
}
