package imagebinarization.math;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Binarization {
    
    // Luminosity
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
 
                // photoshop luminance formula
                gsValue = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                newPixel = rgbChannelsToPixel(alpha, gsValue, gsValue, gsValue);
                
                lumImg.setRGB(i, j, newPixel);
            }
        }
        
        return lumImg;
    }
    
    // histogram of grayscale image
    public static int[] histogram(BufferedImage grayscaled) {
        int[] histogram = new int[256];
        for(int i = 0; i < histogram.length; i++) histogram[i] = 0;
        for(int i = 0; i < grayscaled.getWidth(); i++) {
            for(int j = 0; j < grayscaled.getHeight(); j++) {
                int red = new Color(grayscaled.getRGB (i, j)).getRed();
                histogram[red]++;
            }
        }
        
        return histogram;
    }
    
    // RGB and alpha to 8 bit
    private static int rgbChannelsToPixel(int alpha, int red, int green, int blue) {
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
    
    // Get binary treshold using Otsu's method
    private static int otsuTreshold(BufferedImage original) {
        int[] histogram = histogram(original);
        int total = original.getHeight() * original.getWidth();
 
        float sum = 0;
        for(int i=0; i<256; i++) sum += i * histogram[i];
 
        float sumB = 0;
        int wB = 0;
        int wF = 0;
 
        float varMax = 0;
        int threshold = 0;
 
        for(int i=0 ; i<256 ; i++) {
            wB += histogram[i];
            if(wB == 0) continue;
            wF = total - wB;
 
            if(wF == 0) break;
 
            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;
 
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);
 
            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        
        System.out.println("Calculated threshold: " + threshold);
        return threshold;
    }
    
    public static BufferedImage binarize(BufferedImage original) {
 
        int red;
        int newPixel;
 
        int threshold = otsuTreshold(original);
 
        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {
 
                // Get pixels
                red = new Color(original.getRGB(i, j)).getRed();
                int alpha = new Color(original.getRGB(i, j)).getAlpha();
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = rgbChannelsToPixel(alpha, newPixel, newPixel, newPixel);
                binarized.setRGB(i, j, newPixel); 
 
            }
        }
        return binarized;
    }
}
