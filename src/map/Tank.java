package map;

public class Tank extends Cell {

    private Map map;
    private final int number; // number of tank

    public Tank(int number, Map map) {
        this.number = number;
        this.map = map;
    }
}
