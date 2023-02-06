package states;


import cards.Card;
import cards.Queen;
import java.util.Map;
import java.util.Optional;


public class PlayerState {
    public Map<Integer, Optional<Card>> cards;
    public Map<Integer, Queen> awokenQueens;
}
