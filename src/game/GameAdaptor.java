package game;

import position.AwokenQueenPosition;
import position.HandPosition;
import position.Position;
import position.SleepingQueenPosition;
import states.GameState;

import java.util.*;

public class GameAdaptor implements GamePlayerInterface{

    private Game game;
    private Map<String,Integer> players;
    private GameObservable gameObservable;

    public GameAdaptor(GameObservable gameObservable, Map<String, Integer> players) {
        this.gameObservable = gameObservable;
        this.players = players;
        game = new Game(gameObservable.getNumberOfObservers());

    }

    @Override
    public String play(String player, String cards) {
        if(players.size() <= 1) {
            return ("You cant play alone, go find some friends!");
            //maybe some error return as well or force break ?
        }

        if(!players.containsKey(player)){
            return ("Player not found");
        }

        int playerIndex = players.get(player);
        List<Position> card = new ArrayList<>();

        Scanner scanner = new Scanner(cards);
        while (scanner.hasNext()){
            String c = scanner.next();
            char c1 = c.charAt(0);

            ArrayList<Integer> helpList = new ArrayList<>();
            for (int i = 1; i < c.length(); i++) {
                helpList.add(Integer.parseInt(String.valueOf(c.charAt(i))));
            }

            switch (c1){
                case 'h' -> card.add(new HandPosition(helpList.get(0),playerIndex));
                case 's' -> card.add(new SleepingQueenPosition(Integer.parseInt(c.substring(1))));
                case 'a' -> card.add(new AwokenQueenPosition(helpList.get(1), helpList.get(0)));
            }


        }
        scanner.close();

        Optional<GameState>gameState= game.play(playerIndex,card);
        if (gameState.isEmpty()) return "Error";
        gameObservable.notifyAll(gameState.get());
        return  "Cards played correctly";
    }

}
