package position;

import java.util.Objects;

public class AwokenQueenPosition implements Position {
    private final int player;
    private int cardIndex;

    public AwokenQueenPosition(int player, int cardIndex){
        this.player = player;
        this.cardIndex = cardIndex;
    }

    public int getPlayerIndex(){
        return player;
    }

    @Override
    public int getCardIndex() {
        return cardIndex;
    }

    @Override
    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardIndex, player);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwokenQueenPosition other = (AwokenQueenPosition) obj;
        if(player != other.player) return false;
        return cardIndex == other.cardIndex;
    }
}
