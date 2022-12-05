package position;

public class AwokenQueenPosition implements Position {
    private int player;
    private int index;

    public AwokenQueenPosition(int player, int index){
        this.player = player;
        this.index = index;
    }

    public int getPlayerIndex(){
        return player;
    }

    @Override
    public int getCardIndex() {
        return index;
    }
}
