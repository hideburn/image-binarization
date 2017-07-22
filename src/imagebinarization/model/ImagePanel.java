package imagebinarization.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
       // TODO: provjeri ovo, nesto nije ok sa classpathom
       /*try {      
           InputStream in = this.getClass().getClassLoader().getResourceAsStream("assets/img/default.png");
           image = ImageIO.read(in);
       } catch (IOException ex) {
            System.out.println("Image load failed! Check if choosen file is in image format.");
       }*/
    }

    public void loadImgFile(File file){
        try {
            image = ImageIO.read(file);
            Image img = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            image.getGraphics().drawImage(img, 0, 0 , null);
        } catch (IOException ex) {
            System.out.println("Image load failed! Check if choosen file is in image format.");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);        
    }

}
