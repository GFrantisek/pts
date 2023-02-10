package tests;

import cards.Card;
import cards.CardType;
import cards.Queen;
import org.junit.Before;
import org.junit.Test;
import pile_and_hand_managment.BasicDeck;
import pile_and_hand_managment.DrawingAndTrashPile;
import pile_and_hand_managment.EvaluateAttack;
import pile_and_hand_managment.RealShuffle;
import player.EvaluateNumberedCards;
import player.Player;
import position.Position;
import position.SleepingQueenPosition;
import queen_collection.MoveQueen;
import queen_collection.SleepingQueens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTestOldVersion {
    private Player player1;
    private Player player2;
    private EvaluateAttack evaluateAttack1;
    private EvaluateAttack evaluateAttack2;
    private EvaluateNumberedCards evaluateNumberedCards;
    private MoveQueen moveQueen1;
    private MoveQueen moveQueen2;
    private SleepingQueens sleepingQueens;
    private DrawingAndTrashPile drawingAndTrashPile;
    private Map<Integer, Player> players;
    // mal som pokus to spravit cez Mockito pomoocu mock class -> example
    // @Mock
    // private MoveQueen moveQueenMock;
    // pricom nasledne v setupe by sa vytvoril mock danej class moveQueenMock = mock(MoveQueen.class);
    // to by sa spravilo pre nepotrebne classes a tam kde by som potreboval kontrolovat input by sa vytvoril double
    // ale all in all by player vyzeral ako tvorba mockov / double teda by som sa zbavil zavislosti na funkcnosti vsetkych tried v nom
    // ale pri spusteni hocicoho co sa len oskrelo o Mock mi moj compiler vyhodi -> java.lang.ExceptionInInitializerError
    // a teda nemam ako testy zbehnut (snazil som sa dost dlho zistit kde je chyba ale ani po vyskusani metod na https://stackoverflow.com/questions/45718145/intellij-errorjava-java-lang-exceptionininitializererror
    // mi nefungovalo teda sa pokusim spravit nejako rozumne vyzerajuce testy bez mockov -> mozno by pomohol prerobenie starych za pomoci kontrolovaneho pilu implementovaneho podla bodov 1 a 2
    // aj ked je sanca ze by bol dany pristup zly kvoli tomu ze mam prilis prepletene pouzivanie aj v play metode
    // taktiez sa ospravedlnujem za neskoresie odovzdanie ale mal som este 1 skusku a prilis dlho som sa snazil prist na to preco mi na pocitaci nefunguje mockito

    @Before
    public void setup() {
        BasicDeck basicDeck = new BasicDeck();
        List<Card> basicCard = basicDeck.createBasicDeck();
        players = new HashMap<>();
        evaluateNumberedCards = new EvaluateNumberedCards();
        drawingAndTrashPile = new DrawingAndTrashPile(basicCard, new RealShuffle());
        sleepingQueens = new SleepingQueens();
        moveQueen1 = new MoveQueen(players, sleepingQueens);
        moveQueen2 = new MoveQueen(players, sleepingQueens);
        evaluateAttack1 = new EvaluateAttack(players, moveQueen1);
        evaluateAttack2 = new EvaluateAttack(players, moveQueen2);
        player1 = new Player(0, evaluateAttack1, moveQueen1, evaluateNumberedCards, drawingAndTrashPile);
        player2 = new Player(1, evaluateAttack2, moveQueen2, evaluateNumberedCards, drawingAndTrashPile);
        players.put(0, player1);
        players.put(1, player2);
    }


    @Test
    public void playNumberedCardTest() {
        setup();
        if(player1.getHand().hasCardOfType(CardType.Number) != null){
            List<Position> list = new ArrayList<>();
            list.add(player1.getHand().hasCardOfType(CardType.Number));
            assertTrue(player1.play(list));
        }


    }

    @Test
    public void playKingCardTest() {
        setup();
        if (player1.getHand().hasCardOfType(CardType.King) != null) {
            List<Position> list = new ArrayList<>();
            list.add(player1.getHand().hasCardOfType(CardType.King));
            list.add(new SleepingQueenPosition(1));
            sleepingQueens.addQueen(new Queen(10));
            assertTrue(player1.play(list));
            assertEquals(1, player1.getAwokenQueens().getQueens().size());
        }
    }

    @Test
    public void playKnightCardTest() {
        setup();

        if (player1.getHand().hasCardOfType(CardType.Knight) != null && player2.getAwokenQueens().getQueens().size() > 1)  {
            if (player2.getHand().hasCardOfType(CardType.Dragon) != null) {
                List<Position> list2 = new ArrayList<>();
                list2.add(player1.getHand().hasCardOfType(CardType.Knight));
                list2.add(player2.getHand().hasCardOfType(CardType.Dragon));
                assertFalse(player1.play(list2));
            } else {
                List<Position> list2 = new ArrayList<>();
                list2.add(player1.getHand().hasCardOfType(CardType.Knight));
                assertTrue(player1.play(list2));
                assertEquals(1, player1.getAwokenQueens().getQueens().size());
            }
        }
    }

    @Test
    public void playSleepingPotionCardTest() {
        setup();
        if (player1.getHand().hasCardOfType(CardType.SleepingPotion) != null && player2.getAwokenQueens().getQueens().size() > 1) {
            if (player2.getHand().hasCardOfType(CardType.MagicWand) != null) {
                List<Position> list2 = new ArrayList<>();
                list2.add(player1.getHand().hasCardOfType(CardType.SleepingPotion));
                list2.add(player2.getHand().hasCardOfType(CardType.MagicWand));
                assertFalse(player1.play(list2));
            } else {
                List<Position> list2 = new ArrayList<>();
                list2.add(player1.getHand().hasCardOfType(CardType.SleepingPotion));
                assertTrue(player1.play(list2));
                assertEquals(1, player1.getAwokenQueens().getQueens().size());
            }
        }
    }

}
