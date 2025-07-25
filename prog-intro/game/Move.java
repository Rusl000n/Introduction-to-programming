package game;

public class Move {
    private final int row;
    private final int column;
    private final Cell value;

    public Move(int row, int column, Cell value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public Cell getValue() {
        return value;
    }
}