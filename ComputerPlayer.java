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
        computerThinking();

        int[][] retVal = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}};
        return retVal[move];
    }

    public void computerThinking() {
        System.out.println("\n" + getName() + " is thinking...");
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(3) * 1000 + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
