import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Image{

    private BufferedImage[] subimages = new BufferedImage[Board.PUZZLES_NUMBER];

    Image(JFrame frame) {
        ImageSource imageSource = new ImageSource(frame);
        BufferedImage image = imageSource.loadImage();
        int scaledWidth = 800;
        int scaledHeight = 800 * image.getHeight() / image.getWidth();
        Board.imageSize = new Dimension(scaledWidth, scaledHeight);
        System.out.println(scaledHeight);
        image = createResizedCopy(image, scaledWidth, scaledHeight);

        for (int i = 0; i < Board.PUZZLES_NUMBER; i++) {
//            System.out.println(Board.SPAN/2 + i % Board.PUZZLES_NUMBER_X*(Board.imageSize.width/ Board.PUZZLES_NUMBER_X));
//            System.out.println(Board.SPAN / 2 + i / Board.PUZZLES_NUMBER_X * (Board.imageSize.height / Board.PUZZLES_NUMBER_Y));
            subimages[i] = cropImage(image, new Point(
                            Board.SPAN / 2 + i % Board.PUZZLES_NUMBER_X * (scaledWidth / Board.PUZZLES_NUMBER_X),
                            Board.SPAN / 2 + i / Board.PUZZLES_NUMBER_X * (scaledHeight / Board.PUZZLES_NUMBER_Y)),
                    new Rectangle(
                            scaledWidth / Board.PUZZLES_NUMBER_X - Board.SPAN,
                            scaledHeight / Board.PUZZLES_NUMBER_Y - Board.SPAN));
            //System.out.println(Board.imageSize.height / Board.PUZZLES_NUMBER_Y - Board.SPAN);
        }
    }

    private BufferedImage createResizedCopy(java.awt.Image originalImage, int scaledWidth, int scaledHeight){
        System.out.println("resizing...");
        int imageType = BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    private BufferedImage cropImage(BufferedImage image, Point point, Rectangle rect){
        return image.getSubimage(point.x, point.y, rect.width, rect.height);
    }

    ImageIcon getSubimage(int i) {
        return new ImageIcon(subimages[i]);
    }
}