package game;

import cards.Queen;
import position.AwokenQueenPosition;
import queen_collection.AwokenQueens;
import states.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class  GameFinished implements GameFinishedStrategy{
    private GameState gameState;
    private int scoreNec;
    private int queensNec;

    public GameFinished(GameState gameState){
        this.gameState = gameState;
        //ratam v podstate s tym ze to je hra pre 2 - 5 ludi cize som
        this.scoreNec = 1000;
        this.queensNec = 13;

        if(gameState.numberOfPlayers == 2 || gameState.numberOfPlayers == 3){
            this.scoreNec = 50;
            this.queensNec = 5;
        }else if(gameState.numberOfPlayers == 4 || gameState.numberOfPlayers == 5){
            this.scoreNec = 40;
            this.queensNec = 4;
        }else {
            System.out.println("Hra s viac ako 6 ludmi nie je mozna ak som spravne pochopil pravidla");
            // maybe some stop or len predpoklad ze nedostanem <2 >5 staci ?
        }
    }

    @Override
    public Optional<Integer> isFinished() {
        Map<Integer, Integer> score = new HashMap<>();
        Map<Integer, Integer> queens = new HashMap<>();
        for (Map.Entry<AwokenQueenPosition, Queen> queen : gameState.awokenQueens.entrySet()) {
            int player = queen.getKey().getPlayerIndex();
            score.put(player, queen.getValue().getPoints() + score.getOrDefault(player, 0));
            queens.put(player, queens.getOrDefault(player, 0) + 1);

            //end game
            if (score.get(player) >= scoreNec) {
                System.out.println("WON WITH SCORE-NEC");

                return Optional.of(player);
            }
            if (queens.get(player) >= queensNec) {
                System.out.println("WON WITH QUEEN COUNT");

                return Optional.of(player);
            }
        }

        int maxPoints = Integer.MIN_VALUE;
        int winPlayer = Integer.MIN_VALUE;

        for (Map.Entry<Integer, Integer> finalSc : score.entrySet()) {
            if (finalSc.getValue() > maxPoints) {
                maxPoints = finalSc.getValue();
                winPlayer = finalSc.getKey();
            }
        }

        if(gameState.sleepingQueens.isEmpty()) return Optional.of(winPlayer);
        return Optional.empty();

    }
}


