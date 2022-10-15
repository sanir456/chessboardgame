import java.util.*;
public class Game {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        boolean isBlackTern = false;
        while(true)
        {
            System.out.println("Board:");
            System.out.println(myBoard.toString());
            if(isBlackTern)
            {
                System.out.println("It is currently black's turn to play.");
            }
            else
            {
                System.out.println("It is currently white's turn to play.");
            }
            int startRow,startCol,endRow,endCol;

            do {
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                startRow = s.nextInt();
                startCol = s.nextInt();
                endRow = s.nextInt();
                endCol = s.nextInt();
                if (!myBoard.verifySourceAndDestination(startRow, startCol, endRow, endCol, isBlackTern)) {
                    System.out.println("It is illegal move");
                }
                else if(!myBoard.getPiece(startRow,startCol).isMoveLegal(myBoard,endRow,endCol))
                {
                    System.out.println("It is illegal move");
                }
                else
                {
                    myBoard.movePiece(startRow,startCol,endRow,endCol);
                    break;
                }
            }while (true);
            if(myBoard.isGameOver())
            {
                System.out.println(myBoard.toString());
                if(isBlackTern)
                {
                    System.out.println("Black has win the game!");
                }
                else
                {
                    System.out.println("White has win the game!");
                }
                break;
            }
            isBlackTern = !isBlackTern;
        }

    }
}
