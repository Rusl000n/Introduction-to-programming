package game;

public class Game {
    private final boolean log;
    private final Player[] players;
    public Game(boolean log, Player... players) {
        this.log = log;
        this.players = players;
    }

    public int play(Board board) {
        System.out.println(board.toString());
        while (true) {
            int i = 0;
            for (Player player : players) {
                System.out.println("Ход игрока " + player.getIndentifier());
                final int result = move(board, player, i);
                if (result == -2) {
                    return -2;
                }
                if (result > -1) {
                    return result;
                }
                i++;
            }
        }
    }
    public String match(Board board, int pointsForWin) {
        int[] results = new int[players.length];
        int roundCount = 1;
        while (true) {

            System.out.println("-".repeat(15) + "РАУНД " + roundCount + "-".repeat(15));
            int roundResult = play(board);
            if (roundResult == -2) {
                return "Игра сломана.";
            }
            if (roundResult == players.length) {
                for (int i = 0; i < results.length; i++) {
                    results[i]++;
                    break;
                }
            } else {
                results[roundResult]++;
                
                Player temp = players[roundResult];
                players[roundResult] = players[players.length - 1 - roundResult];
                players[players.length - 1 - roundResult] = temp;

                int tempRes = results[roundResult];
                results[roundResult] = results[results.length - 1 - roundResult];
                results[results.length - 1 - roundResult] = tempRes;
            }
            for (int i = 0; i < results.length; i++) {
                if (results[i] >= pointsForWin) {
                    return matchResult(results, i);
                }
            }
            board.clean();
            roundCount++;
        }
    }

    private String matchResult(int[] results, int winner) {
        StringBuilder sb = new StringBuilder(System.lineSeparator());
        sb.append("-".repeat(20)).append("РЕЗУЛЬТАТЫ ИГРЫ").append("-".repeat(20));
        sb.append(System.lineSeparator());
        sb.append("Победитель матча: ").append(players[winner].getIndentifier()).append(System.lineSeparator());
        sb.append("Игроки, которые старались, но так и не смогли победить: ").append(System.lineSeparator());
        for (int i = 0; i < players.length; i++) {
            if (i != winner) {
                sb.append(players[i].getIndentifier()).append(" с результатом ").append(results[i])
                        .append(System.lineSeparator());
            }
        }
        sb.append("-".repeat("РЕЗУЛЬТАТЫ ИГРЫ".length() + 40));
        return sb.toString();
    }

    private int move(final Board board, final Player player, int numb) {
        final Move move = player.move(board.getTurn());
        if (move == null) {
            return -2;
        }
        final Result result = board.makeMove(move);
        System.out.println(board);
        if (result == Result.WIN) {
            System.out.println(players[numb].getIndentifier() + " выйграл!");
            return numb;
        } else if (result == Result.LOSE) {
            System.out.println(players[players.length - numb - 1].getIndentifier() + " выйграл!");
            return players.length - numb - 1;
        } else if (result == Result.DRAW) {
            System.out.println("Ничья");
            return players.length;
        } else {
            return -1;
        }
    }
}