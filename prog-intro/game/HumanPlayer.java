package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final String indentifier;
    private final int m;
    private final int n;
    private final Scanner in;
    public HumanPlayer(String indentifier, int m, int n) {
        this.indentifier = indentifier;
        this.in = new Scanner(System.in);
        this.m = m;
        this.n = n;
    }

    @Override
    public Move move(final Cell cell) {
        cycle: while(true) {
            System.out.println("Введите свой ход: ");
            int count = 0;
            ArrayList<String> tokens = new ArrayList<String>();
            try {
                Scanner parseSc = new Scanner(in.nextLine());
                while (parseSc.hasNext()) {
                    tokens.add(parseSc.next());
                    count++;
                    if (count > 2) {
                        System.out.println("Ход должен составлять два числа - координаты клетки.");
                        System.out.println("Попробуйте еще раз.");
                        continue cycle;
                    }
                }
                if (count == 1) {
                    parseSc = new Scanner(in.nextLine());
                    while (parseSc.hasNext()) {
                        tokens.add(parseSc.next());
                        count++;
                        if (count > 2) {
                            System.out.println("Ход должен составлять два числа - координаты клетки.");
                            System.out.println("Попробуйте еще раз.");
                            continue cycle;
                        }
                    }
                }
                String row = tokens.get(0);
                String column = tokens.get(1);
                try {
                    int cordR = Integer.parseInt(row);
                    int cordC = Integer.parseInt(column);
                    if (cordR <= 0 || cordC <= 0 || cordR > m  || cordC > n)
                        throw new InputMismatchException("Input is bad");
                    return new Move(cordR - 1, cordC - 1, cell);
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Ход должен составлять два числа - координаты клетки.");
                    System.out.println("Попробуйте еще раз.");
                }
            } catch (NoSuchElementException ne) {
                System.out.println("Ввод окончен.");
                return null;
            }
        }
    }

    public String getIndentifier() {
        return indentifier;
    }
}