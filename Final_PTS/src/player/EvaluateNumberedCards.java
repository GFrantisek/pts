package player;

import cards.Card;
import cards.CardType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvaluateNumberedCards {
    boolean play(List<Card> cards){
        if(cards.size() == 1) return cards.get(0).getType() == CardType.Number;
        if(cards.size() == 2){
            if (cards.get(0).getType().equals(cards.get(1)) && cards.get(0).getValue() == (cards.get(1).getValue())){
                return true;
            }
            return false;
        }
        List<Integer> values = new ArrayList<>();
        for(Card card : cards){
            values.add(card.getValue());
        }
        values.sort(Comparator.naturalOrder());
        int sum = 0;
        for(int i =0; i<values.size()-1; i++){
            sum += values.get(i);
        }
        if (sum==values.get(values.size())) {return true;}

        return false;
    }
}
