package states;

import cards.Card;
import cards.Queen;

import java.util.Map;
import java.util.Optional;

public class PlayerState {
    Map<Integer, Optional<Card>> cards;
    Map<Integer, Queen> awokenQueens;
}
