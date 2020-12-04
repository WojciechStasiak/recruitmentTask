import java.awt.*;
import java.awt.image.BufferedImage;

public class Image {
    private BufferedImage image;

    public Image(BufferedImage image) {
        this.image = image;
    }
    public Image(){ }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage toBlackAndWhite(int precision) {
        int w = image.getWidth();
        int h = image.getHeight();

        if(precision<0 || precision>100)
            precision = 50;

        int limit = 255 * precision / 100;

        for(int i = 0, j; i < w; i++) {
            for(j = 0; j < h; j++) {
                Color color = new Color(image.getRGB(i, j));
                if(limit <= color.getRed() || limit <= color.getGreen() || limit <= color.getBlue()) {
                    image.setRGB(i, j, Color.WHITE.getRGB());
                } else {
                    image.setRGB(i, j, Color.BLACK.getRGB());
                }
            }
        }
        return image;
    }
}
