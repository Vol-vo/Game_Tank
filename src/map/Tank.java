package map;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Tank extends Cell {

    private static final String ANSI_CYAN = "\u001B[36m";

    private static final String SIDE_UP = "up";
    private static final String SIDE_DOWN = "down";
    private static final String SIDE_LEFT = "left";
    private static final String SIDE_RIGHT = "right";


    private final Map map;
    private final int number; // number of tank
    private int point, health;
    private String side;


    public Tank(int number, Map map, int x, int y) {
        super(x, y);
        this.number = number;
        this.map = map;
        point = 0;
        health = 7;
        side = getSide();
    }

    private String getSide() {
        int r = ThreadLocalRandom.current().nextInt(0, 3);
        return switch (r) {
            case 0 -> SIDE_RIGHT;
            case 1 -> SIDE_LEFT;
            case 2 -> SIDE_UP;
            case 3 -> SIDE_DOWN;
            default -> null;
        };
    }

    public void addPoint(int point) {
        this.point += point;
    }

    /**
     * public LocationTank move(){
     * <p>
     * }
     */
    @Override
    public void hit() {
        health -= 2;
    }

    public void shoot() {
        if (Objects.equals(side, SIDE_LEFT)) {
            if (getX() - 1 >= 0) {
                for (int i = getX() - 1; i >= 0; i--) {
                    if (shoot(i, getY())) {
                        return;
                    }
                }
            }
            return;
        }
        if (Objects.equals(side, SIDE_RIGHT)) {
            if (getX() + 1 < 12) {
                for (int i = getX() + 1; i < 12; i++) {
                    if (shoot(i, getY())) {
                        return;
                    }
                }
            }
            return;
        }
        if (Objects.equals(side, SIDE_UP)) {
            if (getY() - 1 >= 0) {
                for (int i = getY() - 1; i >= 0; i--) {
                    if (shoot(getX(), i)) {
                        return;
                    }
                }
            }
            return;
        }
        if (Objects.equals(side, SIDE_DOWN)) {
            if (getY() + 1 < 12) {
                for (int i = getY() + 1; i < 12; i++) {
                    if (shoot(getX(), i)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean shoot(int x, int y) {
        if (map.getCellInCoordination(y, x).getClass() == Brick.class
                || map.getCellInCoordination(y, x).getClass() == Concrete.class) {
            if (map.getCellInCoordination(y, x).isLive()) {
                map.getCellInCoordination(y, x).hit();
                return true;
            }
        }
        if (map.getCellInCoordination(y, x).getClass() == Tank.class) {
            map.getCellInCoordination(y, x).hit();
            point++;
            return true;
        }
        if (map.getCellInCoordination(y, x).getClass() == Eagle.class) {
            map.getCellInCoordination(y, x).hit();
            point += 3;
            return true;
        }
        return false;
    }

    private int getRandom() {
        return ThreadLocalRandom.current().nextInt(1, 3);
    }

    @Override
    public void toPrint() {
        switch (side) {
            case "left" -> {
                System.out.print(ANSI_CYAN + "[◀]" + ANSI_RESET);
            }
            case "right" -> {
                System.out.print(ANSI_CYAN + "[▶]" + ANSI_RESET);
            }
            case "up" -> {
                System.out.print(ANSI_CYAN + "[▲]" + ANSI_RESET);
            }
            case "down" -> System.out.print(ANSI_CYAN + "[▼]" + ANSI_RESET);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean isLive() {
        return health > 0;
    }

    public void printStatus(){
        System.out.println("Status " + number + "a tank: ");
        System.out.println("health " + health);
        System.out.println("points " + point);
    }
}
