package vjezba_xo;

public interface Player {
    String getSymbol();
    int[] makeMove(Player player1, Player player2, String[][] board);
    String getName();
}
