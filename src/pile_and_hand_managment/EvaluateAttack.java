package pile_and_hand_managment;

import cards.CardType;
import player.Player;
import position.HandPosition;
import position.Position;
import queen_collection.MoveQueen;

import java.util.ArrayList;
import java.util.Map;

public class EvaluateAttack {
    private CardType defenseCardType;
    private final MoveQueen moveQueen;
    private final Map<Integer, Player> playerMap;

    public EvaluateAttack( Map<Integer, Player> playerMap, MoveQueen moveQueen){

        this.moveQueen = moveQueen;
        this.playerMap = playerMap;

    }

    public boolean play(Position targetQueen, int targetPlayerIndex){
        if (( targetPlayerIndex >= playerMap.size()) || (!playerMap.get(targetPlayerIndex).getAwokenQueens().getQueens().containsKey(targetQueen)))
            return false;

       HandPosition defenseCardHandPosition = playerMap.get(targetPlayerIndex).getHand().hasCardOfType(defenseCardType);
        if (defenseCardHandPosition == null) {
            moveQueen.play((Position) targetQueen);
        } else {
            ArrayList<HandPosition> defenseCardList = new ArrayList<>();
            defenseCardList.add(defenseCardHandPosition);
            playerMap.get(targetPlayerIndex).getHand().pickCards(defenseCardList);
            playerMap.get(targetPlayerIndex).getHand().removePickedCardsAndRedraw();
        }
        return true;
    }

    public void setDefenceCardType(CardType defenseCardType){
        this.defenseCardType = defenseCardType;
    }
}
