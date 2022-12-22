package position;

import java.util.Objects;

public class SleepingQueenPosition implements Position {
    private int cardIndex;

    public SleepingQueenPosition(int cardIndex){
        this.cardIndex = cardIndex;
    }

    @Override
    public int getCardIndex() {
        return cardIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardIndex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SleepingQueenPosition other = (SleepingQueenPosition) obj;
        return cardIndex == other.cardIndex;
    }
}
