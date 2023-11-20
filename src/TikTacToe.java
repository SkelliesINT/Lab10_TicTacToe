import java.util.Objects;
import java.util.Scanner;

public class TikTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String[][] board = new String[ROW][COL];


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row;
        int col;
        String player1Name = "Player 1";
        String player1Symbol = " X ";
        String player2Name = "Player 2";
        String player2Symbol = " O ";
        String currentPlayerName;
        String currentPlayerSymbol;

        System.out.println("Welcome to Tic Tac Toe!\n");

        do {
            //the change starting player toggle.
            String tempName = player2Name;
            String tempSymbol = player2Symbol;
            player2Name = player1Name;
            player2Symbol = player1Symbol;
            player1Name = tempName;
            player1Symbol = tempSymbol;
            clearBoard();
            display();

            int rounds = 0;

            for (int turns = 0; turns < 9; turns++) {
                //determine player based on odd or even round
                if (rounds % 2 == 0) {
                    currentPlayerName = player2Name;
                    currentPlayerSymbol = player2Symbol;
                } else {
                    currentPlayerName = player1Name;
                    currentPlayerSymbol = player1Symbol;
                }

                System.out.println("It's " + currentPlayerName + "'s turn!");
                //Takes player input and assigns to arrays
                do {
                    row = SafeInput.getRangedInt(in, "Enter your row number", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Enter your column number", 1, 3) - 1;
                    if (!isValidMove(row, col)){
                        System.out.println("Not a valid move. Please try again.");
                        display();
                    }
                } while (!isValidMove(row, col));

                board[row][col] = currentPlayerSymbol;
                display();
                rounds += 1;

                // win or tie processing
                if (isWin(currentPlayerSymbol)) {
                    System.out.println(currentPlayerName + " Wins!");
                    break;
                } else if (isTie(turns)) {
                    System.out.println("It's a tie!");
                    break;

                }
            }
        } while (SafeInput.getYNConfirm(in, "Would you like to play again?"));
    }

    private static void clearBoard() //sets all the board elements to a space.
    {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = "   "; //Makes this cell a space
            }
        }
    }

    private static void display() {
        StringBuilder ticBoard = new StringBuilder();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                ticBoard.append(board[row][col]);
                if (col < COL - 1) {
                    ticBoard.append("|");
                }
            }
            if (row < ROW - 1) {
                ticBoard.append("\n-----------\n");
            }
        }

        System.out.println(ticBoard);
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals("   ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false; //no row win
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false; //no row win
    }

    private static boolean isDiagonalWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
                return true;
            } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
                return true;
            }
        }
        return false; //no diagonal win
    }

    private static boolean isTie(int turns) {
        //Checks for a full board tie
        if (turns == 9) {
            return true;
        }
        // Check for a tie after 7 moves
        if (turns >= 7) {
            // Check each row, column, and diagonal for the presence of both 'X' and 'O'
            for (int i = 0; i < 3; i++) {
                // Check row
                if (Objects.equals(board[i][0], board[i][1]) && Objects.equals(board[i][0], board[i][2]) && !Objects.equals(board[i][0], "  ")) {
                    return false; // There is a win in this row, not a tie
                }
                // Check column
                if (Objects.equals(board[0][i], board[1][i]) && Objects.equals(board[0][i], board[2][i]) && !Objects.equals(board[0][i], "  ")) {
                    return false; // There is a win in this column, not a tie
                }
            }

            // Check diagonals
            return (!Objects.equals(board[0][0], board[1][1]) || !Objects.equals(board[0][0], board[2][2]) || Objects.equals(board[0][0], "  ")) &&
                    (!Objects.equals(board[0][2], board[1][1]) || !Objects.equals(board[0][2], board[2][0]) || Objects.equals(board[0][2], "  ")); // There is a win in one of the diagonals, not a tie
            // If no win is found in any row, column, or diagonal, it's a tie
        }

        // If less than 7 moves, it's not yet possible to have a tie
        return false;
    }
}
