import cards.Card;
import cards.CardType;
import position.HandPosition;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Hand {
    int playerIdx;

    Optional<List<Card>> pickCards(List<HandPosition> positions){
        return Optional.empty();
    }

    Map<HandPosition,Card> removePickedCardsAndRedraw(){
        return null;
    }

    void returnPickedCards(){};

    HandPosition hasCardOfType(CardType type){return null;}
}
