import cards.Card;
import cards.CardType;

import java.io.IOException;

public class Test {
    public static void main(String [] args) throws IOException {
        Card King = new Card(CardType.King,2);
        System.out.println(King.getValue() +"  " +  King.getType());
    }
}
