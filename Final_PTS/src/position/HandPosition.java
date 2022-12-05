package position;

public class HandPosition implements Position {
    int index;

    public HandPosition(int index){
        this.index = index;
    }

    @Override
    public int getCardIndex() {
        return index;
    }
}
