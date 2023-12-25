package map;

import java.util.concurrent.ThreadLocalRandom;

public class Map {
    private final Cell[][] cells;  // cells on the map
    private final Tank[] tanks; // tank on the map
    public Map(Tank[] tanks) {
        this.tanks = tanks;
        cells = new Cell[12][12];
    }

    public void generateMap() {
        for (int i = 0; i < 12; i++) {  //y
            for (int j = 0; j < 12; j++) {  //x
                if (i != j && !(11 - i == j)) {
                    cells[i][j] = randomCell(j, i);
                }else {
                    cells[i][j] = new Empty(i, j);
                }
            }
        }
    }


    /**
     * @return Cell
     * 0 - empty
     * 1 - Forest
     * 2 - Ice
     * 3 - Concrete
     * 4 - Brick
     * 5 - Eagle
     */
    private Cell randomCell(int x, int y) {
        while (true) {
            int r = ThreadLocalRandom.current().nextInt(0, 5+1);
            switch (r) {
                case 0 -> {
                    return new Empty(x, y);
                }
                case 1 -> {
                    return new Forest(x, y);
                }
                case 2 -> {
                    return new Ice(x, y);
                }
                case 3 -> {
                    return new Concrete(x, y);
                }
                case 4 -> {
                    return new Brick(x, y);
                }
                case 5 -> {
                    return new Eagle(x, y);
                }

            }
        }

    }

    public void printMap() throws GameException{
        for (Tank t1 : tanks){
            for (Tank t2 : tanks){
                if (t1.getX() == t2.getX() && t1.getY() == t2.getY() && t1.getNumber() != t2.getNumber()){
                    throw new GameException("Tanks location on 1 cell");
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {
            secondFor:
            for (int j = 0; j < cells[i].length; j++){
                for (Tank tank : tanks) {
                    if (i == tank.getY() && j == tank.getY()) {
                        if (cells[i][j].getClass() == Forest.class) {
                            cells[i][j].toPrint();
                        } else {
                            tank.toPrint();
                        }
                        continue secondFor;
                    }
                }
                cells[i][j].toPrint();
            }
            System.out.println(" ");
        }
    }

    public Cell getCellInCoordination(int x, int y){
        return cells[x][y];
    }
}
