package vjezba_xo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer implements Player{
    private String symbol;
    private String name;

    HumanPlayer(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] makeMove() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.print("\n" + getName() + " u koje polje zelite da upisete " + getSymbol() + " (unesite koordinate npr. 1 2): ");
        userInput = scanner.nextLine();

        int[][] retVal = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}};
        switch (userInput) {
            case "1 1":
                return retVal[0];
            case "1 2":
                return retVal[1];
            case "1 3":
                return retVal[2];
            case "2 1":
                return retVal[3];
            case "2 2":
                return retVal[4];
            case "2 3":
                return retVal[5];
            case "3 1":
                return retVal[6];
            case "3 2":
                return retVal[7];
            case "3 3":
                return retVal[8];
            default:
                return retVal[0];
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
