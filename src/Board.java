import java.util.Scanner;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
        this.clear();
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        Piece piece = this.board[row][col];
        return piece;
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = this.board[startRow][startCol];
        this.board[startRow][startCol] = null;
        piece.setPosition(endRow,endCol);
        this.board[endRow][endCol] = piece;
        if(piece.getCharacter() == '\u2659' || piece.getCharacter() == '\u265F' )
        {
            Scanner s = new Scanner(System.in);
            if(piece.getIsBlack() && endRow == 7)
            {
                System.out.println("Promoted pawn to(Queen, Bishop, Rook, Knight, Pawn): ");
                String promotedTo = s.nextLine();
                piece.pawnPromotion(endRow,endCol,promotedTo);
            }
            else if (!piece.getIsBlack() && endRow == 0)
            {
                System.out.println("Promoted pawn to(Queen, Bishop, Rook, Knight, Pawn): ");
                String promotedTo = s.nextLine();
                piece.pawnPromotion(endRow,endCol,promotedTo);
            }
        }
        return true;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        boolean whileKing = false;
        boolean blackKing = false;
        for(int row=0;row<8;row++)
        {
            for(int col=0;col<8;col++)
            {
                Piece piece = this.board[row][col];
                if(piece != null)
                {
                    if(piece.getCharacter() == '\u2654')
                    {
                        whileKing = true;
                    }

                    if(piece.getCharacter() == '\u265A')
                    {
                        blackKing = true;
                    }
                }
            }
        }
        return !(whileKing && blackKing);
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String boardString="\u2001\u2001\u20010";
        for(int col=1;col<8;col++)
        {
            boardString+="         "+Integer.toString(col);
        }
        boardString+="\n";
        for(int row=0;row<8;row++)
        {
            boardString+=(Integer.toString(row) + "\u2001|\u2001");
            for(int col=0;col<8;col++)
            {
                Piece piece = this.board[row][col];
                if(piece == null)
                {
                    boardString+="\u2001\u2001|\u2001";
                }
                else
                {
                    boardString+=(piece.getCharacter()+"\u2001|\u2001");
                }
            }
            boardString+="\n";
        }
        boardString+="\n";
        return boardString;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for(int row=0;row<8;row++)
        {
            for(int col=0;col<8;col++)
            {
                this.board[row][col]=null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - Both contain a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - Destination contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // startRow, startCol, endRow, endCol must be within the bounds of the board.
        if(startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 )
        {
            return false;
        }
        else if(endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7 )
        {
            return false;
        }
        // The start position must contain a piece.
        else if(this.board[startRow][startCol]==null)
        {
           return false;
        }
        // The color of the starting piece must match the color provided to this function.
        else if(this.board[startRow][startCol].getIsBlack() != isBlack)
        {
            return false;
        }
        //The destination location must either contain no piece or must contain a piece of the
        //opposite color. (a piece of the opposite color is said to be “captured” when this piece
        //moves to the end location)
        else if(this.board[endRow][endCol]!=null && this.board[endRow][endCol].getIsBlack() == isBlack )
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow-endRow);
        int colDiff = Math.abs(startCol-endCol);

        if(rowDiff>1 || colDiff>1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // The move must take place in the same row.
        if(startRow != endRow)
        {
            return false;
        }
        // All spaces on the board between the start and the destination must not contain a piece.
        else if(startCol<endCol)
        {
            for(int col = startCol+1;col<endCol;col++)
            {
                if(this.board[startRow][col]!=null)
                {
                   return false;
                }
            }
        }
        else
        {
            for(int col = startCol-1;col>endCol;col--)
            {
                if(this.board[startRow][col]!=null)
                {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // The move must take place in the same column.
        if(startCol != endCol)
        {
            return false;
        }
        // All spaces on the board between the start and the destination must not contain a piece.
        else if(startRow<endRow)
        {
            for(int row = startRow+1;row<endRow;row++)
            {
                if(this.board[row][startCol]!=null)
                {
                    return false;
                }
            }
        }
        else
        {
            for(int row = startRow-1;row>endRow;row--)
            {
                if(this.board[row][startCol]!=null)
                {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        boolean verification = true;

        int rowDiff = Math.abs(startRow-endRow);
        int colDiff = Math.abs(startCol-endCol);
        if(rowDiff != colDiff)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
