package game;

import java.util.Random;

public class RandomPlayer implements Player{
    private final Random random;
    private final String indentifier;
    private final int boundM;
    private final int boundN;
    public RandomPlayer(String indentifier, int boundM, int boundN) {
        this.indentifier = indentifier;
        this.random = new Random();
        this.boundM = boundM;
        this.boundN = boundN;
    }

    public Move move(final Cell cell) {
        int row = random.nextInt(0, boundM);
        int column = random.nextInt(0, boundN);
        final Move move = new Move(row, column, cell);
        return move;
    }

    public String getIndentifier() {
        return this.indentifier;
    }

}