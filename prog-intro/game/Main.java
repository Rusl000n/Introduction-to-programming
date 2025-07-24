package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // установка правил игры
        System.out.println("Это игра m, n, k. Чтобы начать, установите правила игры.");
        System.out.println("Введите размеры поля");
        System.out.print("m: ");
        int m = sc.nextInt();
        System.out.print("n: ");
        int n = sc.nextInt();

        System.out.println("Введите количество знаков в ряд, необходимых для победы: ");
        int k = sc.nextInt();

        System.out.print("Введите имена игроков: ");
        //new RandomPlayer("Лакер", m, n)
        final Game game = new Game(false, new HumanPlayer(sc.next(), m, n),
                new RandomPlayer("Лакер", m, n));
        String result;

        //System.out.println("Игра началась!");
        result = game.match(new MnkBoard(m, n, k), 3);
        System.out.println("Игра завершилась. " + result);
        sc.close();
    }
}