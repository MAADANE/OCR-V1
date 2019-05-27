package test;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImageExample {

    public static void main(String... args) throws IOException {

        File input = new File("D:\\ENSAO\\PFA-master\\TestOCR\\Lool5.PNG");
        BufferedImage image = ImageIO.read(input);

        BufferedImage resized = resize(image, (int)(image.getHeight()*1.5),(int)(image.getWidth()*1.5));

        File output = new File("D:\\ENSAO\\PFA-master\\TestOCR\\khikhi1.PNG");
        ImageIO.write(resized, "png", output);

    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }}

