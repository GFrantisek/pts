package states;

import cards.Card;
import cards.Queen;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerState {
    public Map<Integer, Optional<Card>> cards;
    public Map<Integer, Queen> awokenQueens;

    public PlayerState() {
        this.cards = new HashMap<>();
        this.awokenQueens = new HashMap<>();
    }

    public Map<Integer, Optional<Card>> getCards() {
        return cards;
    }

    public Map<Integer, Queen> getAwokenQueens() {
        return awokenQueens;
    }

    public void setAwokenQueens(Map<Integer, Queen> awokenQueens) {
        this.awokenQueens = new HashMap<>();
        this.awokenQueens.putAll(awokenQueens);
    }

    public void setCards(Map< Integer, Optional<Card>> card){
        this.cards = new HashMap<>();
        this.cards.putAll(card);
    }
}
