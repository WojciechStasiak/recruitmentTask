import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FinalImage {

    BufferedImage finalImage;

    public FinalImage(BufferedImage image) {
        this.finalImage = image;
    }

    public void drawCurve(Line line) throws IOException {
        BufferedImage img = finalImage;
        Graphics2D graphics = img.createGraphics();
        Path2D curveLine = new Path2D.Double();
        curveLine.moveTo(line.getX().get(0),line.getY().get(0));

        double x1 = (line.getX().get(0)-2);
        double x2 = line.getX().get(getHalfLengthOfXList(line));
        double x3 = line.getX().get(getLastIndexOfList(line));
        double y1 = line.getY().get(0);
        double y2 = line.getY().get(getHalfLengthOfXList(line));
        double y3 = line.getY().get(getLastIndexOfList(line));
        y1 = shift(y1);
        y2 = shift(y2);
        y3 = shift(y3);


        double cx1a = x1 + (x2 - x1) / 3;
        double cy1a = y1 + (y2 - y1) / 3;
        double cx1b = x2 - (x3 - x1) / 3;
        double cy1b = y2 - (y3 - y1) / 3;
        double cx2a = x2 + (x3 - x1) / 3;
        double cy2a = y2 + (y3 - y1) / 3;
        double cx2b = x3 - (x3 - x2) / 3;
        double cy2b = y3 - (y3 - y2) / 3;

        curveLine.curveTo(cx1a, cy1a, cx1b, cy1b, x2, y2);
        curveLine.curveTo(cx2a, cy2a, cx2b, cy2b, x3, y3);

        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(6));
        graphics.draw(curveLine);
        ImageIO.write(img, "png", new File("src\\main\\resources\\Output.png"));
    }

    private double shift(double x){
        if(x-2<0){
            return 0;
        }else {
            return x-2;
        }
    }

    private int getHalfLengthOfXList(Line line){
        return line.getX().size()/2;
    }
    private int getLastIndexOfList(Line line){
        return line.getX().size()-1;
    }

    public BufferedImage getFinalImage() {
        return finalImage;
    }


}
