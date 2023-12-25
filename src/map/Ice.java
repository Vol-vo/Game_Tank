package map;

public class Ice extends Cell{

    public Ice(int x, int y){
        super(x, y);
    }

    @Override
    public void toPrint() {
        System.out.print(ANSI_BLUE + "[▧]" + ANSI_RESET);
    }
}
