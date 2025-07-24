package game;

public interface Board {
    boolean isValid(Move move);
    Result makeMove(Move move);
    Cell getTurn();
    void clean();
}