import map.GameException;
import map.Map;
import map.Tank;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
    /**
     * @Rules Point:
     * Tank: 1
     * Eagle: 3
     **/

    private final Map map;
    private final Tank[] tanks;

    private boolean isRunning;

    public Game(int tanksCount) throws GameException {
        tanks = new Tank[tanksCount];
        if (tanksCount < 2 || tanksCount > 4) {
            throw new GameException("Invalid number of tanks");
        }
        map = new Map(tanks);
        buildTank();
        isRunning = true;
    }

    private void buildTank() {
        for (int i = 0; i < tanks.length; i++) {
            int r = ThreadLocalRandom.current().nextInt(0, 11);
            tanks[i] = new Tank(i, map, r, r);
        }
    }

    public void start() {
        map.generateMap();
        while (isRunning) {
            update();
            break;
        }
    }

    private void update() {
        printGame();
        for (Tank tank : tanks) {
            tank.shoot();
        }
        printGame();
        System.out.println("--------------------------------------------------------");


    }

    private void printGame(){
        try {
            map.printMap();
            System.out.println("Tanks statistics: ");
            for (Tank tank : tanks) {
                tank.printStatus();
                System.out.println("==============");
            }
        }catch (GameException e) {
            System.out.println(e.getMessage());
        }
    }


}
