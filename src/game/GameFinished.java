package game;

import cards.Queen;
import queen_collection.AwokenQueens;

import java.util.Map;
import java.util.Optional;

public class GameFinished implements GameFinishedStrategy{
    private Map<Integer, AwokenQueens> awokenQueens;
    private int scoreNec;
    private int queensNec;

    public GameFinished(Map<Integer, AwokenQueens> awokenQueens, int numberOfPlayers){
        this.awokenQueens = awokenQueens;
        //ratam v podstate s tym ze to je hra pre 2 - 5 ludi cize som
        this.scoreNec = 1000;
        this.queensNec = 13;

        if(numberOfPlayers == 2 || numberOfPlayers == 3){
            this.scoreNec = 50;
            this.queensNec = 5;
        }else if(numberOfPlayers == 4 || numberOfPlayers == 5){
            this.scoreNec = 40;
            this.queensNec = 4;
        }else {
            System.out.println("Hra s viac ako 6 ludmi nie je mozna");
            // maybe some stop or len predpoklad ze nedostanem <2 >5 staci ?
        }
    }

    @Override
    public Optional<Integer> isFinished() {
        for(Map.Entry<Integer, AwokenQueens> entry : this.awokenQueens.entrySet()){
            int player = entry.getKey();
            int queensCount = entry.getValue().getQueens().size();
            int scoreOfPlayer = 0;

            for(Queen queen : entry.getValue().getQueens().values()){
                scoreOfPlayer =+ queen.getPoints();
            }

            if( scoreOfPlayer >= this.scoreNec || queensCount >= this.queensNec ){
                return Optional.of(player); //won
            }
        }
        return Optional.empty();
    }
}
