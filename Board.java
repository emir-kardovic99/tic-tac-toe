package vjezba_xo;

public class Board {
    private final String[][] board;

    Board() {
        board = new String[3][3];
    }

    public void placeSymbolOn(String symbol, int[] coordinate) {
        int coordX = coordinate[0];
        int coordY = coordinate[1];

        board[coordX][coordY] = symbol;
    }

    public boolean isFieldAvailable(int[] coordinate) {
        int coordX = coordinate[0];
        int coordY = coordinate[1];

        return board[coordX][coordY].equals(" ");
    }

    public void cleanBoard() {
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                board[i][j] = " ";
            }
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

    public String[][] getBoard() {
        return board;
    }
}
