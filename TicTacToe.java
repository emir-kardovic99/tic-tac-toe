package vjezba_xo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TicTacToe {
    private final Player playerOne;
    private final Player playerTwo;
    private final Board board;

    TicTacToe(Player pOne, Player pTwo) {
        playerOne = pOne;
        playerTwo = pTwo;
        board = new Board();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            if (playerMoveCheckWinner(playerOne)) {
                break;
            }

            if (playerMoveCheckWinner(playerTwo)) {
                break;
            }
        }

        System.out.println("Do you want to play one more? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            newGame();
        }
    }

    public boolean playerMoveCheckWinner(Player player) {
        board.printBoard();
        int[] move;
        do {
            move = player.makeMove(board);
        } while (!board.isFieldAvailable(move));
        board.placeSymbolOn(player.getSymbol(), move);

        if (isGameOver()) {
            printWinner(player);
            return true;
        } if (isTie()) {
            System.out.println("It's a TIE!");
            return true;
        } else {
            return false;
        }
    }

    public boolean isTie() {
        String[][] tempBoard = board.getBoard();
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (tempBoard[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void newGame() {
        board.cleanBoard();
        TicTacToe.main(new String[]{});
    }

    public void printWinner(Player player) {
        board.printBoard();
        System.out.println("\n\n" + player.getName() + " WON with " + player.getSymbol() + "!");
    }

    public boolean isGameOver() {
        String[][] tempBoard = board.getBoard();

        // horizontal
        if ( (tempBoard[0][0].equals(tempBoard[0][1]) && tempBoard[0][1].equals(tempBoard[0][2]) && !tempBoard[0][0].equals(" ")) ||
                (tempBoard[1][0].equals(tempBoard[1][1]) && tempBoard[1][1].equals(tempBoard[1][2]) && !tempBoard[1][0].equals(" ")) ||
                (tempBoard[2][0].equals(tempBoard[2][1]) && tempBoard[2][1].equals(tempBoard[2][2]) && !tempBoard[2][0].equals(" ")) ) {
            return true;
        }
        // vertical
        if ( (tempBoard[0][0].equals(tempBoard[1][0]) && tempBoard[1][0].equals(tempBoard[2][0]) && !tempBoard[0][0].equals(" ")) ||
                (tempBoard[0][1].equals(tempBoard[1][1]) && tempBoard[1][1].equals(tempBoard[2][1]) && !tempBoard[0][1].equals(" ")) ||
                (tempBoard[0][2].equals(tempBoard[1][2]) && tempBoard[1][2].equals(tempBoard[2][2]) && !tempBoard[0][2].equals(" ")) ) {
            return true;
        }
        // diagonals
        return (tempBoard[0][0].equals(tempBoard[1][1]) && tempBoard[1][1].equals(tempBoard[2][2]) && !tempBoard[0][0].equals(" ")) ||
                (tempBoard[0][2].equals(tempBoard[1][1]) && tempBoard[1][1].equals(tempBoard[2][0]) && !tempBoard[0][2].equals(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[1-3]");
        String gameMode;

        System.out.println("----GAME MENU----");
        System.out.println("Choose game mode: \n1) Player vs Player \n2) Player vs Computer \n3) Computer vs Computer");
        System.out.print("GAME MODE: ");
        gameMode = scanner.nextLine();
        Matcher matcher = pattern.matcher(gameMode);

        while (!matcher.find()) {
            System.out.print("\nChoose valid number: ");
            gameMode = scanner.nextLine();
            matcher = pattern.matcher(gameMode);
        }
        gameMode = gameMode.substring(matcher.start(), matcher.end());

        String computerLevel = "1";
        if (gameMode.equals("2") || gameMode.equals("3")) {
            pattern = Pattern.compile("[1-2]");
            System.out.println("\nChoose computer level: \n1)Beginner \n2)Expert");
            computerLevel = scanner.nextLine();
            matcher = pattern.matcher(computerLevel);
            while (!matcher.find()) {
                System.out.print("\nChoose valid number: ");
                gameMode = scanner.nextLine();
                matcher = pattern.matcher(gameMode);
            }
            computerLevel = computerLevel.substring(matcher.start(), matcher.end());
        }

        Player player1;
        Player player2;

        if (gameMode.equals("1")) {
            player1 = new HumanPlayer("X", "Human1");
            player2 = new HumanPlayer("O", "Human2");
        }
        else if (gameMode.equals("2")) {
            player1 = new HumanPlayer("X", "Human");
            if (computerLevel.equals("1")) {
                player2 = new ComputerPlayer("O", "Computer2");
            } else {
                player2 = new ComputerExpertPlayer("O", "Computer2");
            }
        }
        else {
            player1 = new ComputerPlayer("X", "Computer1");
            player2 = new ComputerPlayer("O", "Computer2");
        }

        TicTacToe ticTacToe = new TicTacToe(player1, player2);

        ticTacToe.play();
    }
}
