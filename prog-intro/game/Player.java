package game;

public interface Player {
    String indentifier = null;
    Move move(Cell cell);
    String getIndentifier();
}