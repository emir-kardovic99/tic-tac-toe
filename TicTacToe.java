import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TicTacToe <T extends Player, E extends Player>{
    private T playerOne;
    private E playerTwo;
    protected static String[][] board = new String[3][3];

    TicTacToe(T pOne, E pTwo) {
        playerOne = pOne;
        playerTwo = pTwo;

        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            printBoard();

            int[] pOne;
            int[] pTwo;

            do {
                pOne = playerOne.makeMove();
            } while(!validMove(pOne));
            board[pOne[0]][pOne[1]] = playerOne.getSymbol();

            if (isGameOver()) {
                printWinner(playerOne);
                break;
            }
            printBoard();

            if (isTie()) {
                System.out.println("\nIt's a TIE!");
                break;
            }

            do {
                pTwo = playerTwo.makeMove();
            } while(!validMove(pTwo));
            board[pTwo[0]][pTwo[1]] = playerTwo.getSymbol();

            if (isGameOver()) {
                printWinner(playerTwo);
                break;
            }

            if (isTie()) {
                System.out.println("It's a TIE!");
                break;
            }
        }


        System.out.println("Do you want to play one more? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            newGame();
        }
    }


    public boolean isTie() {
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void newGame() {
        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
        TicTacToe.main(new String[]{});
    }

    public <T extends Player> void printWinner(T player) {
        printBoard();
        System.out.println("\n\n" + player.getName() + " WON with " + player.getSymbol() + "!");
    }

    public boolean validMove(int[] move) {
        return board[move[0]][move[1]].equals(" ");
    }

    public boolean isGameOver() {
        // horizontal
        if ( (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && !board[0][0].equals(" ")) ||
                (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && !board[1][0].equals(" ")) ||
                (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && !board[2][0].equals(" ")) ) {
            return true;
        }
        // vertical
        if ( (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && !board[0][0].equals(" ")) ||
                (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && !board[0][1].equals(" ")) ||
                (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && !board[0][2].equals(" ")) ) {
            return true;
        }
        // diagonals
        if ( (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" "))) {
            return true;
        }
        else {
            return false;
        }
    }

    public void printBoard() {
        System.out.println("\n\n    A   B   C ");
        System.out.println("              ");
        for (int i=0; i < 3; i++) {
            System.out.print(i+1 + "  ");
            for (int j=0; j < 3; j++) {
                if (j < 2) {
                    System.out.print(" " + board[i][j] + " |");
                } else {
                    System.out.print(" " + board[i][j] + " ");
                }
            }
            if (i < 2) {
                System.out.println("\n   ---+---+---");
            }
        }
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

        TicTacToe<Player, Player> ticTacToe = new TicTacToe<>(player1, player2);

        ticTacToe.play();
    }

}
