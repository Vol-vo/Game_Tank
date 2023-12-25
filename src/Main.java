import map.GameException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Write Tank number: ");
            int tanks = scanner.nextInt();
            Game game = new Game(tanks);
            game.start();
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }
    }
}