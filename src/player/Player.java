package player;

import cards.Card;
import cards.CardType;
import cards.Queen;
import pile_and_hand_managment.DrawingAndTrashPile;
import pile_and_hand_managment.EvaluateAttack;
import pile_and_hand_managment.Hand;
import position.AwokenQueenPosition;
import position.HandPosition;
import position.Position;
import queen_collection.AwokenQueens;
import queen_collection.MoveQueen;
import states.PlayerState;

import java.util.*;

public class Player {

    private final int playerIndex;
    private final Hand hand;
    private final EvaluateAttack evaluateAttack;
    private final MoveQueen moveQueen;
    private final EvaluateNumberedCards evaluateNumberedCards;
    private final AwokenQueens awokenQueens;


    public Player(int playerIndex,
                  EvaluateAttack evaluateAttack,
                  MoveQueen moveQueen,
                  EvaluateNumberedCards evaluateNumberedCards,
                  DrawingAndTrashPile drawAndTrashPile){

        this.playerIndex = playerIndex;
        this.evaluateAttack = evaluateAttack;
        this.moveQueen = moveQueen;
        this.evaluateNumberedCards = evaluateNumberedCards;

        hand = new Hand(drawAndTrashPile,playerIndex);
        awokenQueens = new AwokenQueens(playerIndex);
    }

    public boolean play (List<Position> cards){
        if (cards.size() == 0) {
            System.out.println("You didnt play -> empty card");
            return false;
        }
        if (!(cards.get(0) instanceof HandPosition)) {
            System.out.println("Incorrect play");
            return false;
        }

        List<HandPosition> avaliableCards = new ArrayList<>();
        if (cards.size() == 1) {
            avaliableCards.add((HandPosition) cards.get(0));
        }
        else if (cards.size() == 2) {
            Optional<Card> firstCard = getPlayerState().cards.get(cards.get(0).getCardIndex());
            Optional<Card> secondCard = getPlayerState().cards.get(cards.get(1).getCardIndex());
            if (firstCard.isEmpty()) {
                System.out.println("You dont have this card");
                return false;
            }
            moveQueen.setQueenCollection(awokenQueens);
            switch(firstCard.get().type) {
                case King:
                    //king wrong
                    if (!moveQueen.play(cards.get(1))) {
                        return false;
                    }
                    avaliableCards.add((HandPosition) cards.get(0));
                    break;
                case SleepingPotion:
                    if (!(cards.get(1) instanceof AwokenQueenPosition)) return false;

                    moveQueen.setQueenCollectionToSleepingQueens();
                    evaluateAttack.setDefenceCardType(CardType.MagicWand);
                    if (!evaluateAttack.play(cards.get(1), ((AwokenQueenPosition) cards.get(1)).getPlayerIndex()))return false;
                    avaliableCards.add((HandPosition) cards.get(0));
                    break;
                case Knight:
                    if (!(cards.get(1) instanceof AwokenQueenPosition)) return false;

                    evaluateAttack.setDefenceCardType(CardType.Dragon);
                    if (!evaluateAttack.play(cards.get(1), ((AwokenQueenPosition) cards.get(1)).getPlayerIndex())) return false;
                    avaliableCards.add((HandPosition) cards.get(0));
                    break;

                default:
                    //is present to every get method might be necessary
                    boolean condition1 = secondCard.isEmpty();
                    boolean condition2 = (cards.get(1) instanceof  HandPosition);
                    boolean condition3 = firstCard.get().type != CardType.Number || secondCard.get().type != CardType.Number;
                    boolean condition4 = !evaluateNumberedCards.play(List.of(firstCard.get(), secondCard.get()));
                    if(condition1 || (!condition2) || (condition3 || condition4)) {
                        System.out.println("Failed one of 4 conditions");
                        return false;
                    }
                    else {
                        avaliableCards.add((HandPosition) cards.get(0));
                        avaliableCards.add((HandPosition) cards.get(1));
                    }
            }
        }
        else {
            ArrayList<Card> evaluateCards = new ArrayList<>();

            for (Position position : cards) {
                Optional<Card> card = getPlayerState().cards.get(cards.get(0).getCardIndex());

                if (!(position instanceof HandPosition) || card.isEmpty() || ((HandPosition) position).getPlayerIndex() != playerIndex) {
                    System.out.println("Wrong play...");
                    return false;
                }
                evaluateCards.add(card.get());
                avaliableCards.add((HandPosition) position);
            }

            for (Card card : evaluateCards) {
                if (card.type != CardType.Number) {
                    System.out.println("Put just Number cards");
                    return false;
                }
            }
            if (!evaluateNumberedCards.play(evaluateCards)) {
                System.out.println("Failed to eveluate numbered cards");
                return false;
            }
        }
        hand.pickCards(avaliableCards);
        hand.removePickedCardsAndRedraw();
        return true;
    }


    public PlayerState getPlayerState(){
        //
        PlayerState playerState = new PlayerState();

        Map<Integer, Optional<Card>> cards = new HashMap<>();
        List<Card> handCards = hand.getCards();
        for (int i = 0; i < 5; i++) {
            cards.put(i, i < handCards.size() ?
                    Optional.ofNullable(handCards.get(i)) :
                    Optional.empty());
        }
        playerState.cards = cards;

        Map<Integer, Queen> queens = new HashMap<>();
        awokenQueens.getQueens().entrySet().forEach(entry -> {
            queens.put(entry.getKey().getCardIndex(), entry.getValue());
        });
        playerState.awokenQueens = queens;

        return playerState;

    }

    public Hand getHand() {
        return hand;
    }
    public AwokenQueens getAwokenQueens() {
        return awokenQueens;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
}
