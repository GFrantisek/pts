package pile_and_hand_managment;

import cards.Card;
import cards.CardType;
import position.HandPosition;

import java.util.*;

public class Hand {
    private List<Card> pickedCards;
    private DrawingAndTrashPile pile;
    private List<Card> handCards;
    private final int playerIndex;

    public Hand(DrawingAndTrashPile pile, int playerIndex1){

        this.pile = pile;
        this.playerIndex = playerIndex1;
        this.pickedCards = new ArrayList<>();
        this.handCards =  new ArrayList<>();
        handCards.addAll(pile.drawFullHandOf5Cards());
    }

    public Optional<List<Card>> pickCards(List<HandPosition> positions){
        if (positions.isEmpty()) {
            return Optional.empty();
        }
        for(HandPosition pos : positions) pickedCards.add(handCards.get(pos.getCardIndex()));
        return Optional.of(pickedCards);
    }

    public Map<HandPosition,Card> removePickedCardsAndRedraw(){
        Map<HandPosition, Card> toReturn = new HashMap<>();
        handCards.removeAll(pickedCards);
        List<Card> drawingCards = pile.discardAndDraw(pickedCards);
        for(int i = 0; i < drawingCards.size(); i++){
            toReturn.put(new HandPosition(i + handCards.size(), playerIndex), drawingCards.get(i));
        }
        handCards.addAll(drawingCards);
        returnPickedCards();
        return toReturn;
    }

    public void returnPickedCards(){ pickedCards.clear(); };

    public HandPosition hasCardOfType(CardType type){
        for(int i = 0; i < handCards.size(); i++){
            if(handCards.get(i).getType() == type) return new HandPosition(playerIndex, i );
        }
        return null;
    }

    public List<Card> getCards(){ return handCards;}
    public List<Card> getPickedCards() { return pickedCards; }
}
