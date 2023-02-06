import java.util.Random;

public class ComputerExpertPlayer extends ComputerPlayer implements Player{
    private String symbol;
    private String name;
    private static boolean firstMove = true;


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
    public int[] makeMove() {
        String[][] tempBoard = TicTacToe.board;
        int[][] heuristicBoard = evaluate();
        Random random = new Random();

        int rowPositionMin = 0, colPositionMin = 0;
        int rowPositionMax = 0, colPositionMax = 0;
        int retRow = 0, retCol = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        computerThinking();

        for (int row=0; row < 3; row++) {
            for (int col=0; col < 3; col++) {
                if (tempBoard[row][col].equals(" ") && heuristicBoard[row][col] > max) {
                    max = heuristicBoard[row][col];
                    rowPositionMax = row;
                    colPositionMax = col;
                }
                if (tempBoard[row][col].equals(" ") && heuristicBoard[row][col] < min) {
                    min = heuristicBoard[row][col];
                    rowPositionMin = row;
                    colPositionMin = col;
                }
            }
        }

        boolean ind = true;
        int diffBetweenMaxMin = Math.abs(min) - max;
        if (min == -11 || min == -10 || min == -9 || min == -8) {
            retRow = rowPositionMin;
            retCol = colPositionMin;
            ind = false;
        } else if (ind && (max == 11 || max == 10 || max == 9 || max == 8)) {
            retRow = rowPositionMax;
            retCol = colPositionMax;
        } else if (ind && diffBetweenMaxMin == 1 || diffBetweenMaxMin == 2 || diffBetweenMaxMin == 3) {

        }
        else {
            retRow = rowPositionMax;
            retCol = colPositionMax;
        }

        if (firstMove) {
            retRow = random.nextInt(3);
            retCol = random.nextInt(3);
            firstMove = false;
        }

        int[] retVal = {retRow, retCol};
        return retVal;

    }

    public int[][] evaluate() {
        String[][] tempBoard;
        tempBoard = TicTacToe.board;
        int[][] heuristicBoard = new int[3][3];

        int numX, numO, heur;
        for (int row=0; row < 3; row++) {
            numX = 0;
            numO = 0;
            heur = 0;
            for (int col=0; col < 3; col++) {
                if (tempBoard[row][col].equals("X")) numX++;
                else if (tempBoard[row][col].equals("O")) numO++;
            }

            if (numX == 1) heur += 1;
            if (numX == 2) heur += 10;
            if (numO == 1) heur += -1;
            if (numO == 2) heur += -10;
            for (int col=0; col < 3; col++) {
                if (tempBoard[row][col].equals(" ")) {
                    heuristicBoard[row][col] += heur;
                } else {
                    heuristicBoard[row][col] = 0;
                }
            }
        }

        for (int row=0; row < 3; row++) {
            numX = 0;
            numO = 0;
            heur = 0;

            for (int col=0; col < 3; col++) {
                if (tempBoard[col][row].equals("X")) numX++;
                else if (tempBoard[col][row].equals("O")) numO++;
            }

            if (numX == 1) heur += 1;
            if (numX == 2) heur += 10;
            if (numO == 1) heur += -1;
            if (numO == 2) heur += -10;

            for (int col=0; col < 3; col++) {
                if (tempBoard[col][row].equals(" ")) {
                    heuristicBoard[col][row] += heur;
                } else {
                    heuristicBoard[col][row] = 0;
                }
            }
        }

        numX = 0;
        numO = 0;
        heur = 0;
        for (int row=0; row < 3; row++) {
            for (int col=0; col < 3; col++) {
                if (row == col) {
                    if (tempBoard[col][row].equals("X")) numX++;
                    else if (tempBoard[col][row].equals("O")) numO++;
                }
            }
        }
        if (numX == 1) heur += 1;
        if (numX == 2) heur += 10;
        if (numO == 1) heur += -1;
        if (numO == 2) heur += -10;

        for (int row=0; row < 3; row++) {
            for (int col=0; col < 3; col++) {
                if (row == col) {
                    if (tempBoard[row][col].equals(" ")) {
                        heuristicBoard[row][col] += heur;
                    } else {
                        heuristicBoard[row][col] = 0;
                    }
                }
            }
        }

        numX = 0;
        numO = 0;
        heur = 0;
        for (int row=0; row < 3; row++) {
            for (int col=0; col < 3; col++) {
                if ((row + col) == 2) {
                    if (tempBoard[row][col].equals("X")) {
                        numX++;
                    } else if (tempBoard[row][col].equals("O")) {
                        numO++;
                    }
                }
            }

        }

        if (numX == 1) heur += 1;
        if (numX == 2) heur += 10;
        if (numO == 1) heur += -1;
        if (numO == 2) heur += -10;

        for (int row=0; row < 3; row++) {
            for (int col=0; col < 3; col++) {
                if ((row + col) == 2) {
                    if (tempBoard[row][col].equals(" ")) {
                        heuristicBoard[row][col] += heur;
                    } else {
                        heuristicBoard[row][col] = 0;
                    }
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
