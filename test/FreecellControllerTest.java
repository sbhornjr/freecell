import model.*;
import controller.*;
import org.junit.Test;

//import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for Freecell Controller.
 */
public class FreecellControllerTest {

  /**
  public static void main(String[] args) {
   new FreecellController(new InputStreamReader(System.in), System.out)
   .playGame(new MultiMoveFreecellModel().getDeck(), new FreecellModel(), 8, 10, true);
   }*/

  FreecellModel model = new FreecellModel();

  List<Card> normalDeck = Arrays.asList(new Card(1, "heart"), new Card(2, "heart"),
          new Card(3, "heart"),
          new Card(4, "heart"), new Card(5, "heart"), new Card(6, "heart"),
          new Card(7, "heart"), new Card(8, "heart"), new Card(9, "heart"),
          new Card(10, "heart"), new Card(11, "heart"), new Card(12, "heart"),
          new Card(13, "heart"), new Card(1, "diamond"), new Card(2, "diamond"),
          new Card(3, "diamond"), new Card(4, "diamond"), new Card(5, "diamond"),
          new Card(6, "diamond"), new Card(7, "diamond"), new Card(8, "diamond"),
          new Card(9, "diamond"), new Card(10, "diamond"), new Card(11, "diamond"),
          new Card(12, "diamond"), new Card(13, "diamond"), new Card(1, "club"),
          new Card(2, "club"), new Card(3, "club"), new Card(4, "club"),
          new Card(5, "club"), new Card(6, "club"), new Card(7, "club"),
          new Card(8, "club"), new Card(9, "club"), new Card(10, "club"),
          new Card(11, "club"), new Card(12, "club"), new Card(13, "club"),
          new Card(1, "spade"), new Card(2, "spade"), new Card(3, "spade"),
          new Card(4, "spade"), new Card(5, "spade"), new Card(6, "spade"),
          new Card(7, "spade"), new Card(8, "spade"), new Card(9, "spade"),
          new Card(10, "spade"), new Card(11, "spade"), new Card(12, "spade"),
          new Card(13, "spade"));

  List<Card> movableDeck = Arrays.asList(new Card(11, "diamond"), new Card(13, "spade"),
          new Card(4, "diamond"), new Card(11, "spade"), new Card(8, "club"),
          new Card(1, "diamond"), new Card(7, "heart"), new Card(7, "spade"),
          new Card(3, "heart"), new Card(7, "club"), new Card(1, "club"),
          new Card(12, "club"), new Card(8, "heart"), new Card(6, "spade"),
          new Card(5, "heart"), new Card(12, "heart"), new Card(2, "spade"),
          new Card(4, "spade"), new Card(10, "heart"), new Card(1, "heart"),
          new Card(11, "heart"), new Card(13, "heart"), new Card(3, "diamond"),
          new Card(9, "club"), new Card(4, "club"), new Card(9, "spade"),
          new Card(5, "club"), new Card(10, "diamond"), new Card(12, "spade"),
          new Card(13, "diamond"), new Card(13, "club"), new Card(4, "heart"),
          new Card(10, "spade"), new Card(2, "club"), new Card(2, "diamond"),
          new Card(6, "heart"), new Card(11, "club"), new Card(7, "diamond"),
          new Card(9, "diamond"), new Card(2, "heart"), new Card(6, "club"),
          new Card(12, "diamond"), new Card(5, "diamond"), new Card(9, "heart"),
          new Card(5, "spade"), new Card(8, "spade"), new Card(3, "club"),
          new Card(1, "spade"), new Card(10, "club"), new Card(6, "diamond"),
          new Card(3, "spade"), new Card(8, "diamond"));

  // Checks that a single normal input does its job.
  @Test
  public void testNormalSingleInput() {
    Readable rd = new StringReader("C8 6 F1 q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that 2 inputs go through.
  @Test
  public void testNormalDoubleInput() {
    Readable rd = new StringReader("C8 6 F1 C5 6 C2 Q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦, 5♠\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that the controller handles an invalid source correctly.
  @Test
  public void testInvalidSourcePileInput() {
    Readable rd = new StringReader("A1 C8 6 F1 Q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that the controller handles an invalid index correctly.
  @Test
  public void testInvalidIndexInput() {
    Readable rd = new StringReader("C8 hf   6 F1 q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that the controller handles an invalid destination correctly.
  @Test
  public void testInvalidDestPileInput() {
    Readable rd = new StringReader(" C8 6 fffffff  hh o 4 F1 Q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that the controller will quit at the first step.
  @Test
  public void testQuitAtSourceInput() {
    Readable rd = new StringReader("q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    ap.reverse();
    assertEquals(ap.substring(0, 22), ".ylerutamerp tiuq emaG");
  }

  // Checks that the controller will quit at the second step.
  @Test
  public void testQuitAtIndexInput() {
    Readable rd = new StringReader("C3 Q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    ap.reverse();
    assertEquals(ap.substring(0, 22), ".ylerutamerp tiuq emaG");
  }

  // Checks that the controller will quit at the third step.
  @Test
  public void testQuitAtDestInput() {
    Readable rd = new StringReader("C3 d 4 q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    ap.reverse();
    assertEquals(ap.substring(0, 22), ".ylerutamerp tiuq emaG");
  }

  // Checks that the controller picks up an invalid source pile number.
  @Test
  public void testInvalidSourcePileNumInput() {
    Readable rd = new StringReader("Cr C8 6 O1 q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1: A♠\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that the controller picks up an invalid destination pile number.
  @Test
  public void testInvalidDestPileNumInput() {
    Readable rd = new StringReader("C8 6 Oyyyy O1 Q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1: A♠\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Checks that a move from an Open pile works.
  @Test
  public void testMoveFromOpen() {
    Readable rd = new StringReader("C8 6 O1 O1 1 F1 q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", model.getGameState());
  }

  // Starting an invalid game from controller.
  @Test
  public void testInvalidCascadesAndOpens() {
    Readable rd = new StringReader("C8 6 O1 Q");
    Appendable ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 3, 0, false);
    assertEquals("Could not start game.", ap);
  }

  // Checks that a won game functions correctly.
  @Test
  public void testGameWon() {
    Readable rd = new StringReader("C1 4 F1 C2 4 F1 C3 4 F1 C4 4 F1 C5 4 F1 C6 4 F1 " +
            "C7 4 F1 C8 4 F1 C9 4 F1 C10 4 F1 C11 4 F1 C12 4 F1 C13 4 F1 " +
            "C1 3 F2 C2 3 F2 C3 3 F2 C4 3 F2 C5 3 F2 C6 3 F2 " +
            "C7 3 F2 C8 3 F2 C9 3 F2 C10 3 F2 C11 3 F2 C12 3 F2 C13 3 F2 " +
            "C1 2 F3 C2 2 F3 C3 2 F3 C4 2 F3 C5 2 F3 C6 2 F3 " +
            "C7 2 F3 C8 2 F3 C9 2 F3 C10 2 F3 C11 2 F3 C12 2 F3 C13 2 F3 " +
            "C1 1 F4 C2 1 F4 C3 1 F4 C4 1 F4 C5 1 F4 C6 1 F4 " +
            "C7 1 F4 C8 1 F4 C9 1 F4 C10 1 F4 C11 1 F4 C12 1 F4 C13 1 F4 q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(normalDeck, model, 13, 4, false);
    assertEquals(true, model.isGameOver());
  }

  // Checks that a null deck provides an exception.
  @Test (expected = IllegalArgumentException.class)
  public void testNullDeckException() {
    Readable rd = new StringReader("q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(null, model, 8, 4, false);
  }

  // Checks that a null model provides an exception.
  @Test (expected = IllegalArgumentException.class)
  public void testNullModelException() {
    Readable rd = new StringReader("q");
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, null, 8, 4, false);
  }

  // Checks that a null Readable provides an exception.
  @Test (expected = IllegalStateException.class)
  public void testNullReadableException() {
    Readable rd = null;
    StringBuilder ap = new StringBuilder();
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
  }

  // Checks that a null Appendable provides an exception.
  @Test (expected = IllegalStateException.class)
  public void testNullAppendableException() {
    Readable rd = new StringReader("q");
    StringBuilder ap = null;
    FreecellController controller = new FreecellController(rd, ap);
    controller.playGame(movableDeck, model, 8, 4, false);
  }
}