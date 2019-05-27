package Interface;
import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import traitement.ImageResizer;


public class InterOCR extends JFrame implements ActionListener{
    
	
	JTextField tfu;
	JLabel input,output;
	JButton btu,btd,btt;
	JTextField tfd;
	JComboBox list;
	String[] s = {"French","English","Arabic"};
	JFileChooser jFC = new JFileChooser();
	
	public InterOCR(String ch) {
		super(ch);
		setLayout(new BorderLayout());
		tfu = new JTextField();
		input = new JLabel("Choose File:"); 
		btu = new JButton("Upload File");
		tfd = new JTextField();
		output = new JLabel("The Result:");
		btd = new JButton("Download File");
		btt = new JButton("Traitement");
		list = new JComboBox(s);
		
		JPanel p=new JPanel(new GridLayout(3,1));
		JPanel p1=new JPanel(new GridLayout(1,3));
		JPanel p2=new JPanel(new GridLayout(1,3));
		JPanel p3 = new JPanel();
		
		p1.add(input);
		p1.add(tfu);
		p1.add(btu);
		p2.add(output);
		p2.add(tfd);
		p2.add(btd);
		p3.add(list);
		p3.add(btt);
		p.add(p1,BorderLayout.NORTH);
		p.add(p3,BorderLayout.CENTER);
		p.add(p2,BorderLayout.SOUTH);
		
		
		Container c=getContentPane();
		c.add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		btd.addActionListener(this);
		btu.addActionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterOCR ocr = new InterOCR("Myocr");
		ocr.setSize(new Dimension(500,150));
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Upload File")) {
			  /*  jFC.setAcceptAllFileFilterUsed(false);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", new String[] { "JPG", "JPEG","PNG" });
		        jFC.addChoosableFileFilter(filter);*/
		        jFC.showOpenDialog(null);
				tfu.setText(jFC.getSelectedFile().getAbsolutePath());
		}
		if(e.getActionCommand().equals("Download File")) {
			String result =list.getSelectedItem().toString();
			String path=tfu.getText();
			File imageFile =new File(path);
			ITesseract instance = new Tesseract();
			instance.setDatapath("D:\\ENSAO\\PFA-master\\Tess4J\\tessdata");
			/*ImageResizer im=new ImageResizer();
			File inputFile= new File(path);
			File outputFile=new File("image2.png");
			try {
				outputFile=im.resize(inputFile, outputFile);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}*/
			if(result.equals("French") && !tfd.getText().isEmpty()) {
				System.out.println(tfu.getText());
				try {
					instance.setLanguage("fr/fra");
					//File ff=new File("hello1.png");
					/*BufferedImage inputImage = ImageIO.read(imageFile);
					Image icon = new ImageIcon(path).getImage();
					int scaledWidth = (int) (inputImage.getWidth() * 1.5);
			        int scaledHeight = (int) (inputImage.getHeight() * 1.5);
					icon.getScaledInstance(scaledWidth,scaledHeight, Image.SCALE_SMOOTH);
					BufferedImage bufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g = bufferedImage.createGraphics();
				    g.drawImage(icon, 0, 0,scaledWidth,scaledHeight, null);
				    g.dispose();
					ImageIO.write(bufferedImage, "png", ff);*/
					
					//System.out.print(imageFile);
					//String resultt = instance.doOCR(outputFile);
					String resultt=instance.doOCR(imageFile);
					FileWriter file=new FileWriter("D:\\ENSAO\\"+tfd.getText());  
		            file.write(resultt);
		            file.close();
					System.out.println(resultt);
				}catch(TesseractException e1) {
					System.err.println(e1.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(result.equals("English") && !tfd.getText().isEmpty()) {
				System.out.println(tfu.getText());
				try {
					instance.setLanguage("en/eng");
					//String resultt = instance.doOCR(outputFile);
					String resultt=instance.doOCR(imageFile);
					FileWriter file=new FileWriter("D:\\ENSAO\\"+tfd.getText());  
		            file.write(resultt);
		            file.close();
					System.out.println(resultt);
				}catch(TesseractException e1) {
					System.err.println(e1.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}
