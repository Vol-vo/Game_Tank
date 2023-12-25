package map;

public class Brick {
    private int state;

    public Brick() {
        state = 2;
    }

    public void hit() {
        if (state == 0) {
            return;
        }
        state -= 1;
    }

    public int getState() {
        return state;
    }
}
