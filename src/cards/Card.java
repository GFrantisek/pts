package cards;

public class Card {
    public final CardType type;
    private final int value;

    public Card(CardType type, int value) {
        this.type = type;
        this.value = value;
    }

    //in case of need
    public int getValue() {
        return value;
    }

    public CardType getType() {
        return type;
    }

    //finished
}
