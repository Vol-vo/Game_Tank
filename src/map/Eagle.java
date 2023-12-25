package map;

public class Eagle extends Cell{//Орёл
    public Eagle(int x, int y){
        super(x, y);
    }

    @Override
    public void toPrint() {
        System.out.print(ANSI_PURPLE + "[✪]" + ANSI_RESET);
    }
}
