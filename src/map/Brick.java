package map;

public class Brick extends Cell{

    private int state;

    public Brick(int x, int y) {
        super(x, y);
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

    @Override
    public void toPrint(){
        switch (state) {
            case 0 -> {
                System.out.print(ANSI_RED + "[☐]" + ANSI_RESET);
            }
            case 1 -> {
                System.out.print(ANSI_RED + "[⚀]" + ANSI_RESET);
            }
            case 2 -> System.out.print(ANSI_RED + "[⚁]" + ANSI_RESET);
        }
    }
    @Override
    public boolean isLive(){
        return state > 0;
    }
}
