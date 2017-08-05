package imagebinarization.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {
    }

    public BufferedImage loadImgFile(File file) {
        try {
            image = ImageIO.read(file);
            Image img = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            image.getGraphics().drawImage(img, 0, 0, null);
            return image;
        } catch (IOException ex) {
            System.out.println("Image load failed! Check if choosen file is in image format.");
        }

        return null;
    }

    public void setImage(BufferedImage bImage) {
        image = bImage;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
