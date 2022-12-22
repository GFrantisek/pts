package pile_and_hand_managment;

import cards.Card;
import cards.CardType;
import jdk.internal.event.DeserializationEvent;
import player.Player;
import position.HandPosition;

import javax.swing.text.html.Option;
import java.util.*;

public class Hand {
    private List<Card> pickedCards;
    private DrawingAndTrashPile pile;
    private List<Card> handCards;
    private Player player;

    public Hand(Player player, DrawingAndTrashPile pile){
        this.player = player;
        this.pile = pile;
        this.pickedCards = new ArrayList<>();
        this.handCards =  new ArrayList<>();
        pickedCards.addAll(pile.frstDraw());
        for (int i = 0; i < ) {
}
        System.out.println("HAND frst drwa "+ pickedCards);
    }

    public Optional<List<Card>> pickCards(List<HandPosition> positions){
        for(HandPosition pos : positions) pickedCards.add(handCards.get(pos.getCardIndex()));
        return Optional.of(pickedCards);
    }

    public Map<HandPosition,Card> removePickedCardsAndRedraw(){
        return null;
    }

    public void returnPickedCards(){ pickedCards.clear(); };

    public HandPosition hasCardOfType(CardType type){
        for(int i = 0; i < handCards.size(); i++){
            if(handCards.get(i).getType() == type) return new HandPosition(player.playerIndex, i );
        }
        return null;
    }

    public List<Card> getCards(){ return handCards;}
}
