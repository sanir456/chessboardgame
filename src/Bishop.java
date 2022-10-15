public class Bishop {

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // ability to move only diagonally
        if(board.verifyDiagonal(this.row,this.col,endRow,endCol))
        {
            // case 1: diagonally movement
            if(board.getPiece(endRow,endCol)==null)
            {
                return true;
            }
            // case 2: Capturing a piece
            else
            {
                // There is a piece of the opposite color to be captured.
                if(board.getPiece(endRow, endCol).getIsBlack() != this.isBlack)
                {
                    return  true;
                }
                // same color
                else
                {
                    return false;
                }
            }
        }
        // Case 3: Moving in an illegal direction.
        else
        {
            return false;
        }
    }
}
