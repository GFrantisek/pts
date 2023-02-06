package tests;

import cards.Card;
import cards.CardType;
import game.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pile_and_hand_managment.DrawingAndTrashPile;
import player.Player;
import position.AwokenQueenPosition;
import position.HandPosition;
import position.Position;
import states.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game(2);
    }

    @Test
    public void testPlay() {
        List<Position> cards = new ArrayList<>();
        // Add cards to the list

        Optional<GameState> gameState = game.play(0, cards);
        //cannot play this since i put empty handed therefor you cannot reacte game state
        //if it puts something playable than it creates -> Player test
        Assert.assertFalse(gameState.isPresent());

        // Add additional tests for different scenarios and validations
    }

    @Test
    public void testInitialStateAndOnTurn() {
        Game game = new Game(2);
        GameState gameState = game.gameState;
        assertEquals(2, gameState.numberOfPlayers);
        assertEquals(0, gameState.onTurn);
        assertNotNull(gameState.sleepingQueens);
        assertNotNull(gameState.cards);
        assertNotNull(gameState.awokenQueens);
        assertNotNull(game.getDrawingPile());
    }


}
