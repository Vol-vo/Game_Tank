package map;

public class Forest extends Cell{

    public Forest(int x, int y){
        super(x, y);
    }
    @Override
    public void toPrint() {
        System.out.print(ANSI_GREEN + "[✽]" + ANSI_RESET);
    }
}
