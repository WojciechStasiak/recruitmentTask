import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Detection extends Component {


    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame("Draw red line");
        // read input.png
        Image image = new Image(ImageIO.read(new File("src\\main\\resources\\Input.png")));
        // create and write new file which is converted image to black and white image
        // writing is not necessary but I think it's good to see what the program analyses
        Image blackAndWhite = new Image(image.toBlackAndWhite(10));
        ImageIO.write(blackAndWhite.getImage(),"png",new File("src\\main\\resources\\BlackAndWhite.png"));
        // create object Line which stores every point of the longest white line on the picture
        Line line = new Line();
        line.findLongestLinePoints(blackAndWhite);
        // puts Input.png image and draw on it a curved line and then saves it as Output.png
        FinalImage finalImage = new FinalImage(ImageIO.read(new File("src\\main\\resources\\Input.png")));
        finalImage.drawCurve(line);

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new JLabel(new ImageIcon(finalImage.getFinalImage())));
        f.pack();
        f.setVisible(true);

    }
}
