import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private static final int IMAGE_WIDHT = 800;
    static final int PUZZLES_NUMBER_X = 4;
    static final int PUZZLES_NUMBER_Y = 3;
    static final int PUZZLES_NUMBER = PUZZLES_NUMBER_X*PUZZLES_NUMBER_Y;
    static final int SPAN = 2;
    private static final int REFRESH_RATE = 250;

    static Dimension imageSize = new Dimension(IMAGE_WIDHT,600);
    private int time = 0, clicks = 0;
    private int index, correctPuzzles = 0;

    private JFrame frame;
    private JLabel timeLabel = new JLabel();
    private JLabel clicksLabel = new JLabel();
    private Puzzle[] puzzles = new Puzzle[PUZZLES_NUMBER];
    private Mechanics mechanics = new Mechanics();


    Board(JFrame frame){
        this.frame = frame;
        setLayout(new GridLayout(PUZZLES_NUMBER_Y, PUZZLES_NUMBER_X, SPAN, SPAN));
        setLocation(50,50);
        index = randomBoard();
        setPreferredSize(imageSize);
        setBackground(Color.LIGHT_GRAY);

        timeLabel.setFont(new Font ("Verdana", Font.BOLD, 30));
        clicksLabel.setFont(new Font ("Verdana", Font.BOLD, 30));

        Timer timer = new Timer(REFRESH_RATE, this);
        timer.start();
        setVisible(true);
    }

    ///////////////////////////////////////////
    private int randomBoard(){
        for (int i = 0; i < PUZZLES_NUMBER; i++) {
            index = mechanics.shuffleIndexes(i);
            puzzles[index] = new Puzzle(index, frame);
            add(puzzles[index]);
        }
        puzzles[index].setVisible(false);
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time += REFRESH_RATE;
        timeLabel.setText("Time: " + Integer.toString(time/1000));

        for (int i = 0; i < PUZZLES_NUMBER; i++) {
            if (puzzles[mechanics.getPuzzleNr(i)].isPuzzleClicked()) {
                clicks ++;
                clicksLabel.setText("Clicks: " + Integer.toString(clicks));
                puzzles[mechanics.getPuzzleNr(i)].setPuzzleClicked();
                mechanics.changePuzzleOrder(mechanics.getPuzzlePos(mechanics.getPuzzleNr(i)), mechanics.getPuzzlePos(index));
            }
//            System.out.print("Puzzle\t"+mechanics.getPuzzleNr(i));
//            System.out.println("\tempty Puzzle: \t"+ index);
        }
        drawBoard();

    }

    ///////////////////////////////////////////
    private void drawBoard(){
        for (int i = 0; i < PUZZLES_NUMBER; i++){
            if (i == mechanics.getPuzzleNr(i))
                correctPuzzles ++;
            add(puzzles[mechanics.getPuzzleNr(i)]);
//            System.out.println("dwg order: "+mechanics.getPuzzleNr(i));
        }
//        System.out.println(correctPuzzles);
        if (correctPuzzles == PUZZLES_NUMBER) {
            puzzles[index].setVisible(true);
            JOptionPane.showMessageDialog(this, "You win");
            System.exit(ABORT);
        }

        correctPuzzles = 0;
        frame.add(timeLabel);
        frame.add(clicksLabel);
        revalidate();
        //System.out.println(puzzles);
    }

    //private boolean pu


    //get/set////////////////////////////////////

}