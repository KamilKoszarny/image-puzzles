import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageSource {

    private File imageFile;
    private BufferedImage image;

    ImageSource(JFrame frame){
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\Inz\\Documents\\ImagePuzzlesImages");
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "bmp", "tif"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setAccessory(new ImagePreview(fileChooser));
        int fileChosen = fileChooser.showDialog(frame, "Select picture");
        if (fileChosen == JFileChooser.APPROVE_OPTION){
            imageFile = fileChooser.getSelectedFile();
        }
    }

    BufferedImage loadImage(){
        try {
            image = ImageIO.read(imageFile);
            Board.imageSize = new Dimension(image.getWidth(), image.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}