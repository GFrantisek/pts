package position;

import java.util.Objects;

public class HandPosition implements Position {
    private int cardIndex;
    private int player;

    public HandPosition(int player, int cardIndex){
        this.player = player;
        this.cardIndex = cardIndex;
    }

    public int getPlayerIndex(){
        return player;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setPlayerIndex(int player){
        this.player = player;
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
        HandPosition other = (HandPosition) obj;
        if(player != other.player) return false;
        return cardIndex == other.cardIndex;
    }
}
