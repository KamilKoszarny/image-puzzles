import java.util.Random;

class Mechanics {

    private int[] puzzlesOrder = new int[Board.PUZZLES_NUMBER];
    //int[] drawingOrder = new int[Board.PUZZLES_NUMBER];

    Mechanics() {
        for (int i = 0; i < puzzlesOrder.length; i++) {
            puzzlesOrder[i] = -1;
        }
    }

    int shuffleIndexes(int index){
        int tmpIndex = index;

        Random r = new Random();
        do
            index = r.nextInt(puzzlesOrder.length);
        while ((isInRandomIndexes(index)));
        puzzlesOrder[tmpIndex] = index;
        return index;
    }

    private boolean isInRandomIndexes(int index){
        for (int aPuzzlesOrder : puzzlesOrder)
            if (index == aPuzzlesOrder)
                return true;
        return false;
    }



    void changePuzzleOrder(int checkedPos, int emptyPos){
        if (checkedPos - 1 == emptyPos || checkedPos + 1 == emptyPos || checkedPos - Board.PUZZLES_NUMBER_X == emptyPos || checkedPos + Board.PUZZLES_NUMBER_X == emptyPos) {
            int tmp = getPuzzleNr(checkedPos);
            puzzlesOrder[checkedPos] = getPuzzleNr(emptyPos);
            puzzlesOrder[emptyPos] = tmp;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
//    protected boolean isNextToEmpty(int checkedPos, int emptyPos){
//        if (checkedPos - 1 == emptyPos || checkedPos + 1 == emptyPos || checkedPos - Board.PUZZLES_NUMBER_X == emptyPos || checkedPos + Board.PUZZLES_NUMBER_X == emptyPos)
//            return true;
//        return false;
//    }

    int getPuzzleNr(int puzzlePosition) {
        return puzzlesOrder[puzzlePosition];
    }


    int getPuzzlePos(int puzzleNr) {
        for (int puzzlePosition = 0; puzzlePosition < puzzlesOrder.length; puzzlePosition++)
            if (puzzleNr == puzzlesOrder[puzzlePosition])
                return puzzlePosition;
        return -1;
    }
}