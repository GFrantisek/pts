package game;

import cards.Card;
import cards.CardType;
import cards.Queen;
import pile_and_hand_managment.BasicDeck;
import pile_and_hand_managment.DrawingAndTrashPile;
import pile_and_hand_managment.EvaluateAttack;
import pile_and_hand_managment.RealShuffle;
import player.EvaluateNumberedCards;
import player.Player;
import position.AwokenQueenPosition;
import position.HandPosition;
import position.Position;
import position.SleepingQueenPosition;
import queen_collection.MoveQueen;
import queen_collection.SleepingQueens;
import states.GameState;
import tests.DrawingAndTrashPileTest;

import javax.swing.plaf.basic.BasicLabelUI;
import java.util.*;


public class Game {
    private DrawingAndTrashPile drawingAndTrashPile;
    public Map<Integer,Player> players;
    private SleepingQueens sleepingQueens;
    public GameState gameState;
    private GameFinishedStrategy gameFinishedStrategy;

    public Game (int numOfPlayers){
        BasicDeck basicDeck = new BasicDeck();
        List<Card> basicCard = basicDeck.createBasicDeck();


        sleepingQueens = new SleepingQueens();
        drawingAndTrashPile = new DrawingAndTrashPile(basicCard,new RealShuffle());

        players = new HashMap<>();
        MoveQueen moveQueen = new MoveQueen(players,sleepingQueens);
        EvaluateAttack evaluateAttack = new EvaluateAttack(players, moveQueen);
        EvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards();
        for (int i = 0; i < numOfPlayers; i++) {
            players.put(i, new Player(i,evaluateAttack, moveQueen, evaluateNumberedCards, drawingAndTrashPile));
        }

        gameState = new GameState();
        gameState.onTurn = 0;
        gameState.numberOfPlayers = numOfPlayers;
        updateGameState();

        this.gameFinishedStrategy = new GameFinished(gameState);
    }

    private void  updateGameState() {
        Set<SleepingQueenPosition> sleepingQueenPositions = new HashSet<>();
        gameState.cardsDiscardedLastTurn = drawingAndTrashPile.getCardsDiscardedThisTurn();

        for (Map.Entry<position.Position, Queen> entry : sleepingQueens.getQueens().entrySet()) {
            sleepingQueenPositions.add((SleepingQueenPosition) entry.getKey());
        }
        gameState.sleepingQueens = sleepingQueenPositions;


        Map<HandPosition, Optional<Card>> playersCards = new HashMap<>();
        for (Map.Entry<Integer, Player> entryPlayer : players.entrySet()) {
            for (Map.Entry<Integer, Optional<Card>> entryCards : entryPlayer.getValue().getPlayerState().cards.entrySet()) {
                playersCards.put(new HandPosition(entryCards.getKey(), entryPlayer.getKey()), entryCards.getValue());
            }
        }
        gameState.cards = playersCards;

        Map<AwokenQueenPosition, Queen> playersQueens = new HashMap<>();
        for (Map.Entry<Integer, Player> entryPlayer : players.entrySet()) {
            for (Map.Entry<Integer, Queen> entryAwokenQueens : entryPlayer.getValue().getPlayerState().awokenQueens.entrySet()) {
                playersQueens.put(new AwokenQueenPosition(entryAwokenQueens.getKey(), entryPlayer.getKey()), entryAwokenQueens.getValue());
            }
        }
        gameState.awokenQueens = playersQueens;
    }

    public Optional<GameState> play(int playerIdx, List<Position> cards) {
        if ( (playerIdx != gameState.onTurn) || (!players.containsKey(playerIdx)) ){
            return Optional.empty();
        }

        if (players.get(playerIdx).play(cards)){
            gameState.onTurn = (gameState.onTurn+1) % gameState.numberOfPlayers;
            updateGameState();
            drawingAndTrashPile.newTurn();
            Optional<Integer> win = gameFinishedStrategy.isFinished();
            if ((win.isPresent() )) {
                gameState.onTurn = -1;
                System.out.println("winner: " + win.get());
            }
            return Optional.ofNullable(gameState);
        }

        System.out.println("new turn \n");
        return  Optional.empty();
    }

    public DrawingAndTrashPile getDrawingPile() {
        return this.drawingAndTrashPile;
    }
}
