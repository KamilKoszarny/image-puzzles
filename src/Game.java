import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {


    //main//////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws InterruptedException{
        SwingUtilities.invokeLater(Game::new);
    }

    //Game//////////////////////////////////////////////////////////////////////////////////


    private Game(){
        super("ImagePuzzle");
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        setLocation(200, 20);
        JFrame frame = this;
        Board boardPanel = new Board(frame);
        add(boardPanel);
        setPreferredSize(new Dimension(900, (int) Board.imageSize.getHeight() + 100));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
}