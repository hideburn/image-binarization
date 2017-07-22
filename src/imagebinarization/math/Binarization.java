package imagebinarization.math;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Binarization {
    
    // Luminance
    public static BufferedImage toGrayscale(BufferedImage initial){
        BufferedImage lumImg = new BufferedImage(initial.getWidth(), initial.getHeight(), initial.getType());
        int alpha, red, green, blue, newPixel, gsValue;
        
        for(int i = 0; i < initial.getWidth(); i++) {
            for(int j = 0; j < initial.getHeight(); j++) {
                // get pixels R-G-B components
                alpha = new Color(initial.getRGB(i, j)).getAlpha();
                red   = new Color(initial.getRGB(i, j)).getRed();
                green = new Color(initial.getRGB(i, j)).getGreen();
                blue  = new Color(initial.getRGB(i, j)).getBlue();
 
                gsValue = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
                newPixel = grayscalePixel(alpha, gsValue, gsValue, gsValue);
                
                lumImg.setRGB(i, j, newPixel);
            }
        }
        
        return lumImg;
    }
    
    // RGB and alpha to 8 bit
    private static int grayscalePixel(int alpha, int red, int green, int blue) {
        int pixel = 0;
        pixel += alpha;
        pixel = pixel << 8;
        
        pixel += red; 
        pixel = pixel << 8;
        
        pixel += green; 
        pixel = pixel << 8;
        
        pixel += blue;
        
        return pixel;
    }
}
