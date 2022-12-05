package states;

import cards.Card;
import cards.Queen;

import java.util.Map;
import java.util.Optional;

public class PlayerState {
    private Map<Integer, Optional<Card>> cards;
    private Map<Integer, Queen> awokenQueens;

    PlayerState(Map<Integer, Optional<Card>> cards, Map<Integer, Queen> awokenQueens){
        this.cards = cards;
        this.awokenQueens = awokenQueens;
    }

    Map<Integer, Optional<Card>> getCards(){
        return cards;
    }

    Map<Integer, Queen> getAwokenQueens(){
        return awokenQueens;
    }
}
