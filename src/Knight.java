
public class Knight {

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
    public Knight(int row, int col, boolean isBlack) {
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
        int rowValue[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int colValue[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        for(int i=0;i<8;i++)
        {
            if(this.row + rowValue[i] == endRow && this.col + colValue[i] == endCol)
            {
                // case 1: Knight movement of 'L' shape
                if(board.getPiece(endRow,endCol) == null)
                {
                    return true;
                }
                else
                {
                    // case 2: Capturing a piece
                    if(board.getPiece(endRow, endCol).getIsBlack() != this.isBlack)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }

        // case 3: not match any L shape position from starting position
        return false;
    }
}
