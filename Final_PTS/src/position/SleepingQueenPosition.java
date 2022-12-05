package position;

public class SleepingQueenPosition implements Position {
    private int index;

    public SleepingQueenPosition(int index){
        this.index = index;
    }

    @Override
    public int getCardIndex() {
        return index;
    }
}
