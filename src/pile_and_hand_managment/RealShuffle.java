package pile_and_hand_managment;

import cards.Card;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RealShuffle implements ShuffleInterface{


    @Override
    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards,new Random());
    }
}
