package map;

public class Concrete extends Cell {

    public Concrete(int x, int y) {
        super(x, y);
    }

    @Override
    public void toPrint() {
        System.out.print(ANSI_WHITE + "[☒]" + ANSI_RESET);
    }
}
