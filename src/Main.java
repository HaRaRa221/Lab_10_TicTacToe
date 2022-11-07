import java.util.Scanner;

public class Main {

    private static final int ROW = 3;

    private static final int COL = 3;

    private static String board[][] = new String[ROW][COL];

    /**
     * @param args the argument for the command line
     */
    public static void main(String[] args) {
        boolean donePlaying = false;
        boolean continuePlay = true;
        Scanner in = new Scanner(System.in);
        String player = "X";
        int moveCnt = 0;
        int row = -1;
        int col = -1;
        final int WINNING_MOVEs = 5;
        final int TYING_WAYS = 7;
        do {

            player = "X";
            continuePlay = true;
            moveCnt = 0;
            clearBoard();

            do {

                do {


                    display();
                    System.out.println("Enter your move: " + player);
                    row = SafeInput.getRangedInt(in, "Enter row" + player, 1, 3);
                    col = SafeInput.getRangedInt(in, "Enter col" + player, 1, 3);
                    row--; col--;
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCnt++;

                if (moveCnt >= WINNING_MOVEs && isWin(player)) {

                        display();
                        System.out.println("The Player " + player + " is the winner! ");
                        continuePlay = false;

                }
                if (moveCnt >= TYING_WAYS && isTie()) {

                        display();
                        System.out.println("It's a Tie!");
                        continuePlay = false;

                }

                if (player.equals("X"))
                {
                    player = "O";
                } else
                {
                    player = "X";
                }
            } while (continuePlay);

            donePlaying = SafeInput.getYNconfirm(in, "Are you done with this game?");
        } while(!donePlaying);


    }

    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = "";
            }
        }
    }

    private static void display()
    {
        for (int row = 0; row < ROW; row++) {
            System.out.print(" | ");
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }

    }

    private static boolean isValidMove(int row, int col) {
        if (board[row][col].equals(" "))

            return true;

        return false;
    }
    private static boolean isWin(String player) {
        if (isRowWin(player) || isColWin(player) || isDiagonalWin(player))
            return true;

        return false;

    }
    /** Determines if there is a diagonal win (3 in a diagonal) for the specified player
     * used by the isWin master method
     *
     * @param player
     * @return true if there is a diagonal win false otherwise
     */
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++)
            if     (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
                return true;


        return false;
    }
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++)
            if     (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
                return true;


        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        if     (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player))
            return true;

        else  if(board[0][2].equals(player) &&
                 board[1][1].equals(player) &&
                 board[2][0].equals(player))
            return true;

        return false;
    }

    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;

        for(int row = 0; row < ROW; row++)
        {
            if     (board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X"))
            {
                xFlag = true;
            }
            if     (board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O"))
            {
                oFlag = true;
            }

            if(! (xFlag && oFlag) ) {
                return false;
            }
            xFlag = oFlag = false;
        }

        for(int col = 0; col < COL; col++)
        {
            if     (board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("O"))
            {
                xFlag = true;
            }
            if     (board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O"))
            {
                oFlag = true;
            }

            if(! (xFlag && oFlag)) {
                return false;
            }
        }
        xFlag = oFlag = false;

        if (board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X")) {
            xFlag = true;
        }
        if (board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O")) {
            oFlag = true;
        }

        if (!(xFlag && oFlag))
            return false;

        return true;

    }
}
