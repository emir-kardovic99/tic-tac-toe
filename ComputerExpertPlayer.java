package vjezba_xo;

public class ComputerExpertPlayer extends ComputerPlayer implements Player{
    private final String symbol;
    private final String name;


    ComputerExpertPlayer(String symbol, String name) {
        super(symbol, name);
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] makeMove(Player player1, Player player2, String[][] board) {
        int[][] heuristicBoard = evaluate(board);

        int rowPositionMin = 0, colPositionMin = 0;
        int rowPositionMax = 0, colPositionMax = 0;
        int retRow, retCol;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        computerThinking();

        for (int row=0; row < 3; row++) {
            for (int col=0; col < 3; col++) {
                if (board[row][col].equals(" ") && heuristicBoard[row][col] > max) {
                    max = heuristicBoard[row][col];
                    rowPositionMax = row;
                    colPositionMax = col;
                }
                if (board[row][col].equals(" ") && heuristicBoard[row][col] < min) {
                    min = heuristicBoard[row][col];
                    rowPositionMin = row;
                    colPositionMin = col;
                }
            }
        }

        if (max == 11 || max == 10 || max == 9 || max == 8) {
            retRow = rowPositionMax;
            retCol = colPositionMax;
            return new int[]{retRow, retCol};
        }
        else if (min == -11 || min == -10 || min == -9 || min == -8) {
            retRow = rowPositionMin;
            retCol = colPositionMin;
            return new int[]{retRow, retCol};
        }
        else {
            retRow = rowPositionMax;
            retCol = colPositionMax;
            return new int[]{retRow, retCol};
        }
    }

    public int returnHeur(int numX, int numO) {
        int heur = 0;

        if (numX == 1) heur += 1;
        if (numX == 2) heur += 10;
        if (numO == 1) heur -= 1;
        if (numO == 2) heur -= 10;

        return heur;
    }

    public int[][] evaluate(String[][] board) {
        int[][] heuristicBoard = new int[3][3];

        int numXrow, numOrow, heurRow;
        int numXcol, numOcol, heurCol;
        int numXmain = 0, numOmain = 0, heurMain;
        int numXsec = 0, numOsec = 0, heurSec;
        for (int row=0; row < 3; row++) {
            numXrow = 0;
            numOrow = 0;
            numXcol = 0;
            numOcol = 0;

            for (int col=0; col < 3; col++) {
                if (board[row][col].equals(symbol)) {
                    numXrow++;
                } else if (!board[row][col].equals(" ")) {
                    numOrow++;
                }

                if (board[col][row].equals(symbol)) {
                    numXcol++;
                } else if (!board[col][row].equals(" ")) {
                    numOcol++;
                }

                if (row == col) {
                    if (board[row][col].equals(symbol)) {
                        numXmain++;
                    } else if (!board[row][col].equals(" ")) {
                        numOmain++;
                    }
                }

                if ((row + col) == 2) {
                    if (board[row][col].equals(symbol)) {
                        numXsec++;
                    } else if (!board[row][col].equals(" ")) {
                        numOsec++;
                    }
                }
            }
            heurRow = returnHeur(numXrow, numOrow);
            heurCol = returnHeur(numXcol, numOcol);
            heurMain = returnHeur(numXmain, numOmain);
            heurSec = returnHeur(numXsec, numOsec);

            for (int col=0; col < 3; col++) {
                if (board[row][col].equals(" ")) {
                    heuristicBoard[row][col] += heurRow;
                }
                if (board[col][row].equals(" ")) {
                    heuristicBoard[col][row] += heurCol;
                }
                if (row == col) {
                    if (board[row][col].equals(" ")) {
                        heuristicBoard[row][col] += heurMain;
                    }
                }
                if ((row + col) == 2) {
                    if (board[row][col].equals(" ")) {
                        heuristicBoard[row][col] += heurSec;
                    }
                }
                if (!board[row][col].equals(" ")) {
                    heuristicBoard[row][col] = 0;
                }
            }
        }

        return heuristicBoard;
    }

    @Override
    public String getName() {
        return name;
    }
}
