import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Puzzle extends JButton implements ActionListener{
    private static JFrame frame;
    private static Image image = new Image(frame);
    private boolean puzzleClicked = false;


    Puzzle(int i, JFrame frame) {
        this(image.getSubimage(i));
        Puzzle.frame = frame;
        addActionListener(this);
    }

    private Puzzle(ImageIcon imageIcon) {
        super(imageIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        puzzleClicked = true;
    }

    boolean isPuzzleClicked() {
        return puzzleClicked;
    }

    void setPuzzleClicked() {
        this.puzzleClicked = false;
    }

}
