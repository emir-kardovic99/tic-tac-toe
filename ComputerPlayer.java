package vjezba_xo;

import java.util.Random;

public class ComputerPlayer implements Player{
    private String symbol;
    private String name;

    ComputerPlayer(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] makeMove() {
        Random random = new Random();

        int move = random.nextInt(9);

        int[][] retVal = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}};
        return retVal[move];
    }

    @Override
    public String getName() {
        return name;
    }
}
