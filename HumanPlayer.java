package vjezba_xo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer implements Player{
    private final String symbol;
    private final String name;

    HumanPlayer(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] makeMove(Player player1, Player player2, String[][] board) {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[1-3][a-cA-C]");
        String userInput;

        System.out.print("\n" + getName() + " choose field u want to put " + getSymbol() +" (for example: 2B): ");

        userInput = scanner.nextLine();
        Matcher matcher = pattern.matcher(userInput);
        while (!matcher.find()) {
            System.out.print("\nPlease input a valid field: ");
            userInput = scanner.nextLine();
            matcher = pattern.matcher(userInput);
        }
        userInput = userInput.substring(matcher.start(), matcher.end()).toUpperCase();

        int[][] retVal = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}};

        switch (userInput) {
            case "1B":
                return retVal[1];
            case "1C":
                return retVal[2];
            case "2A":
                return retVal[3];
            case "2B":
                return retVal[4];
            case "2C":
                return retVal[5];
            case "3A":
                return retVal[6];
            case "3B":
                return retVal[7];
            case "3C":
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
