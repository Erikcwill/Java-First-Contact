import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create(InputStream inputStream, String fileName) throws Exception {
       
        BufferedImage originalImage = ImageIO.read(inputStream);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        File directory = new File("output");
        if (!directory.exists()) {
            directory.mkdir();
        }

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(font);

        graphics.drawString("TOPZERA", 0, newHeight - 100);

        ImageIO.write(newImage, "png", new File(fileName));

    }

}
