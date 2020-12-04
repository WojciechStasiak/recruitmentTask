import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Line {

    private List<Double> X = new ArrayList<>();
    private List<Double> Y = new ArrayList<>();

    public void findLongestLinePoints(Image image) {
        BufferedImage img = image.getImage();
        int x = img.getWidth();
        int y = img.getHeight();
        int lengthOfLine;
        int maxLengthOfLine = 0;

        List<Double> polyXX = new ArrayList<>();
        List<Double> polyYY = new ArrayList<>();
        List<Double> polyXTemp = new ArrayList<>();
        List<Double> polyYTemp = new ArrayList<>();


        for (int i = 0, j; i < x; i++) {
            for (j = 0; j < y; j++) {
                Color color = new Color(img.getRGB(i, j));
                Color color4;
                if((j-1)>=0) {
                    color4 = new Color(img.getRGB(i, j - 1));
                }
                else color4 = new Color(0, 0, 0);
                if (color.equals(Color.WHITE) && !color4.equals(Color.WHITE)) {
                    polyXTemp.add((double) i);
                    polyYTemp.add((double) j);
                    lengthOfLine = 0;
                    for (int currentX = i, currentY = j; currentX < x; currentX++) {

                        if ((currentX + 1) < x && ((currentY + 1) < y || (currentY) < y || (currentY - 1) < y)) {
                            Color color1 = new Color(img.getRGB(currentX + 1, currentY - 1));
                            Color color2 = new Color(img.getRGB(currentX + 1, currentY));
                            Color color3 = new Color(img.getRGB(currentX + 1, currentY + 1));
                            if (color1.equals(Color.WHITE)) {
                                lengthOfLine++;
                                currentY = currentY - 1;
                                polyXTemp.add((double) currentX);
                                polyYTemp.add((double) currentY);
                            } else if (color2.equals(Color.WHITE)) {
                                lengthOfLine++;
                                polyXTemp.add((double) currentX);
                                polyYTemp.add((double) currentY);
                            } else if (color3.equals(Color.WHITE)) {
                                lengthOfLine++;
                                currentY = currentY + 1;
                                polyXTemp.add((double) currentX);
                                polyYTemp.add((double) currentY);
                            } else {
                                if (lengthOfLine > maxLengthOfLine) {
                                    maxLengthOfLine = lengthOfLine;
                                    polyXX = polyXTemp;
                                    polyYY = polyYTemp;
                                }
                                polyXTemp = new ArrayList<>();;
                                polyYTemp = new ArrayList<>();;
                                break;
                            }
                        }
                    }
                }
            }
        }
        X = polyXX;
        Y = polyYY;
    }

    public Line() {
    }

    public List<Double> getX() {
        return X;
    }

    public List<Double> getY() {
        return Y;
    }

}
