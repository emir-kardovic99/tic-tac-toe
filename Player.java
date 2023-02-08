package vjezba_xo;

public interface Player {
    String getSymbol();
    int[] makeMove(Board board);
    String getName();
}
