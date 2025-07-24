package game;

import java.util.Arrays;
import java.util.Map;

public class MnkBoard implements Board {

    private static final Map<Cell, String> SYMBOLS = Map.of(
            Cell.PEACH, "\uD83D\uDD34",
            Cell.EGGPLANT, "\uD83D\uDD35",
            Cell.GROUND, "⚪"
    );
    private final int k;
    private Cell turn;
    private final Cell[][] cells;
    public MnkBoard(int m, int n, int k) {
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.GROUND);
        }
        this.turn = Cell.EGGPLANT;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()] == Cell.GROUND
                && turn == move.getValue();
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            System.out.println("Запрещенный ход, очень жаль.");
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        int inDiag1 = 0;
        int inDiag2 = 0;
        int empty = 0;
        for (int i = 0; i < cells.length; i++) {
            int inRow = 0;
            int inColumn = 0;
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == turn) {
                    inRow++;
                }
                if (cells[i][j] == Cell.GROUND) {
                    empty++;
                }
                if (j < cells.length && i < cells[0].length) {
                    if (cells[j][i] == turn) {
                        inColumn++;
                    }
                }
            }
            if (inRow == k || inColumn == k) {
                return Result.WIN;
            }
            if (i < cells[0].length) {
                if (cells[i][i] == turn){
                    inDiag1++;
                }
                if (cells[i][cells[0].length - 1 - i] == turn) {
                    inDiag2++;
                }
            }
        }
        if (inDiag1 == k || inDiag2 == k) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.PEACH ? Cell.EGGPLANT : Cell.PEACH;
        return Result.UNKNOWN;
    }

    public void clean() {
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.GROUND);
        }
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("▨ ");
        for (int i = 1; i < cells[0].length + 1; i++) {
            sb.append(i).append("  ");
        }
        for (int r = 1; r < cells.length + 1; r++) {
            sb.append(System.lineSeparator());
            sb.append(r).append(" ");
            for (int c = 0; c < cells[r - 1].length; c++) {
                sb.append(SYMBOLS.get(cells[r - 1][c])).append(" ");
            }
        }
        return sb.toString();
    }

}