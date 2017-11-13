import model.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Abstract testing class for either a single or multi move game.
 * For hw04: Added a test to make sure moving a card to an
 *           empty Cascade pile goes through (lines 323-341).
 */
public abstract class FreecellOperationsTest {
  FreecellOperations<Card> testModel1;

  /**
   * Constructor for a FreecellOperationsTest class.
   * @param type  Indicates whether testModel1 will be a single or multi move model.
   */
  public FreecellOperationsTest(String type) {
    if (type.equals("single")) {
      this.testModel1 = new FreecellModel();
    }
    else if (type.equals("multi")) {
      this.testModel1 = new MultiMoveFreecellModel();
    }
    else {
      throw new IllegalArgumentException("Game type can only be single or multi move.");
    }
  }

  List<Card> d = Arrays.asList(new Card(1, "heart"), new Card(2, "heart"), new Card(3, "heart"),
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

  List<Card> duplicateDeck = Arrays.asList(new Card(1, "heart"),
          new Card(2, "heart"), new Card(3, "heart"),
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
          new Card(7, "club"), new Card(9, "club"), new Card(10, "club"),
          new Card(11, "club"), new Card(12, "club"), new Card(13, "club"),
          new Card(1, "spade"), new Card(2, "spade"), new Card(3, "spade"),
          new Card(4, "spade"), new Card(5, "spade"), new Card(6, "spade"),
          new Card(7, "spade"), new Card(8, "spade"), new Card(9, "spade"),
          new Card(10, "spade"), new Card(11, "spade"), new Card(12, "spade"),
          new Card(13, "heart"));

  List<Card> tooManyDeck = Arrays.asList(new Card(1, "heart"),
          new Card(2, "heart"), new Card(3, "heart"),
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
          new Card(13, "spade"), new Card(13, "spade"));

  List<Card> notEnoughDeck = Arrays.asList(new Card(1, "heart"),
          new Card(2, "heart"), new Card(3, "heart"),
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
          new Card(10, "spade"), new Card(11, "spade"), new Card(12, "spade"));

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

  String movableOriginalGameState =
          "F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
                  "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
                  "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
                  "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
                  "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
                  "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
                  "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
                  "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
                  "C8: 7♠, Q♥, 9♣, 4♥, 2♥, A♠";


  // Checks that the getDeck method returns the correct deck.
  @Test
  public void getDeckTest() {
    assertEquals(testModel1.getDeck(), d);
  }

  // Checks that the deck is shuffled if shuffle is true.
  @Test
  public void doesShuffle() {
    testModel1.startGame(movableDeck, 8, 4, true);
    assertNotEquals(movableOriginalGameState, testModel1.getGameState());
  }

  // Checks that the deck is not shuffled if shuffle is false.
  @Test
  public void doesNotShuffle() {
    testModel1.startGame(movableDeck, 8, 4, false);
    assertEquals(movableOriginalGameState, testModel1.getGameState());
  }

  // Checks that getGameState returns the correct String in a default game.
  @Test
  public void getGameStateAfterStart8Cascade4OPenNoShuffle() {
    this.testModel1.startGame(d, 8, 4, false);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n"
                    + "C1: A♥, 9♥, 4♦, Q♦, 7♣, 2♠, 10♠\n"
                    + "C2: 2♥, 10♥, 5♦, K♦, 8♣, 3♠, J♠\n"
                    + "C3: 3♥, J♥, 6♦, A♣, 9♣, 4♠, Q♠\n"
                    + "C4: 4♥, Q♥, 7♦, 2♣, 10♣, 5♠, K♠\n"
                    + "C5: 5♥, K♥, 8♦, 3♣, J♣, 6♠\n"
                    + "C6: 6♥, A♦, 9♦, 4♣, Q♣, 7♠\n"
                    + "C7: 7♥, 2♦, 10♦, 5♣, K♣, 8♠\n"
                    + "C8: 8♥, 3♦, J♦, 6♣, A♠, 9♠",
            testModel1.getGameState());
  }

  // Checks that getGameState returns an empty string before the game starts.
  @Test
  public void getGameStateTestBeforeStart() {
    assertEquals("", testModel1.getGameState());
  }

  // Checks that isGameOver returns false if the game is not over.
  @Test
  public void gameNotOverTest() {
    this.testModel1.startGame(d, 8, 4, false);
    assertEquals(this.testModel1.isGameOver(), false);
  }

  // Checks that isGameOver returns true if the game has been won.
  @Test
  public void gameWonTest() {
    this.testModel1.startGame(d, 13, 4, false);
    for (int i = 0; i < 4; i++) {
      int j;
      switch (i) {
        case 0:
          j = 3;
          break;
        case 1:
          j = 2;
          break;
        case 2:
          j = 1;
          break;
        case 3:
          j = 0;
          break;
        default:
          throw new IllegalArgumentException("no.");
      }

      for (int q = 0; q < 13; q++) {
        testModel1.move(PileType.CASCADE, q, j, PileType.FOUNDATION, i);
      }
    }

    assertEquals(true, testModel1.isGameOver());
  }

  // Checks that calling startGame twice deals the same deck each time.
  @Test
  public void dealTwiceSameTest() {
    this.testModel1.startGame(d, 4, 2, false);
    String s1 = testModel1.getGameState();
    this.testModel1.startGame(d, 4, 2, false);
    String s2 = testModel1.getGameState();
    assertEquals(s1, s2);
  }

  // Checks that a valid move from a Cascade pile to another goes through.
  @Test
  public void moveFromCascadeToCascade() {
    this.testModel1.startGame(this.movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 1);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦, 5♠\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥, A♠", testModel1.getGameState());
  }

  // Checks that a valid move from a Cascade pile to an Open pile goes through.
  @Test
  public void moveFromCascadeToOpen() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 1);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1: 5♠\nO2: A♠\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", testModel1.getGameState());
  }

  // Checks that a valid move from an Open pile to a Cascade pile goes through.
  @Test
  public void moveFromOpenToCascade() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 6, 5, PileType.OPEN, 2);
    testModel1.move(PileType.CASCADE, 6, 4, PileType.OPEN, 1);
    testModel1.move(PileType.OPEN, 1, 0, PileType.CASCADE, 0);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3: 3♣\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣, 9♦\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥, A♠", testModel1.getGameState());
  }

  // Checks that moving an Ace from a Cascade pile to an empty Foundation pile goes through.
  @Test
  public void moveAceFromCascadeToFoundation() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥", testModel1.getGameState());
  }

  // Checks that moving a 2 from a Cascade pile onto on Ace on a Foundation pile goes through.
  @Test
  public void moveOntoAceFromCascade() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 0);
    testModel1.move(PileType.CASCADE, 3, 4, PileType.OPEN, 1);
    testModel1.move(PileType.CASCADE, 3, 3, PileType.OPEN, 2);
    testModel1.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION, 2);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 1);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 2);
    assertEquals("F1:\nF2: A♠\nF3: A♥, 2♥\nF4:\nO1: 8♦\nO2: 6♥\nO3: 10♦\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣, 9♥\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥", testModel1.getGameState());
  }

  // Checks that a move to an empty Cascade pile goes through
  @Test
  public void moveOntoEmptyCascadePile() {
    testModel1.startGame(movableDeck, 8, 6, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    testModel1.move(PileType.CASCADE, 7, 3, PileType.OPEN, 2);
    testModel1.move(PileType.CASCADE, 7, 2, PileType.OPEN, 3);
    testModel1.move(PileType.CASCADE, 7, 1, PileType.OPEN, 4);
    testModel1.move(PileType.CASCADE, 7, 0, PileType.OPEN, 5);
    testModel1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 7);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1: A♠\nO2: 2♥\nO3: 4♥\nO4: 9♣\nO5: Q♥\nO6: 7♠\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 10♣", testModel1.getGameState());
  }

  // Checks that calling startGame while a game is in progress restarts the game.
  @Test
  public void startGameAgainToRestart() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    testModel1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 0);
    testModel1.move(PileType.CASCADE, 3, 4, PileType.OPEN, 1);
    testModel1.move(PileType.CASCADE, 3, 3, PileType.OPEN, 2);
    testModel1.startGame(movableDeck, 8, 4, false);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦, 3♠\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣, 5♠\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣, 4♥, 2♥, A♠", testModel1.getGameState());
  }

  // Checks that getGameState returns an empty string if startGame threw an exception.
  @Test
  public void gameStateIsEmptyStringAfterStartThrowsException() {
    try {
      testModel1.startGame(movableDeck, 8, 0, false);
    } catch (IllegalArgumentException e) {
      // Throw away the exception and do nothing.
    }
    assertEquals("", testModel1.getGameState());
  }

  // Checks that calling startGame with a deck with duplicates in it throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void invalidDeckDupe() {
    this.testModel1.startGame(duplicateDeck, 8, 4, false);
  }

  // Checks that calling startGame with a deck with over 52 cards throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void invalidDeckTooMany() {
    this.testModel1.startGame(tooManyDeck, 8, 4, true);
  }

  // Checks that calling startGame with a deck with under 52 cards throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void invalidDeckNotEnough() {
    this.testModel1.startGame(notEnoughDeck, 8, 4, false);
  }

  // Checks that calling startGame with less than 4 Cascade piles throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void notEnoughCascadePiles() {
    this.testModel1.startGame(d, 3, 3, false);
  }

  // Checks that calling startGame with less than 1 Open pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void notEnoughOpenPiles() {
    this.testModel1.startGame(d, 6, 0, true);
  }

  // Checks that moving from a Cascade pile number greater
  // than the number of cascade piles throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromPileNumberGreaterThanCascades() {
    this.testModel1.startGame(movableDeck, 8, 4, true);
    this.testModel1.move(PileType.CASCADE, 12, 4, PileType.OPEN, 1);
  }

  // Checks that moving from a Cascade pile number less than 0 throws an exception
  @Test (expected = IllegalArgumentException.class)
  public void moveFromCascadePileNumberUnder0() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, -2, 4, PileType.OPEN, 2);
  }

  // Checks that moving from a Cascade pile with
  // a card index that's too small throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromCascadeCardIndexTooSmall() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 3, 3, PileType.OPEN, 0);
  }

  // Checks that moving from a Cascade pile with
  // a card index that's too big throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromCascadeCardIndexTooBig() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 3, 7, PileType.OPEN, 0);
  }

  // Checka that moving from an Open pile number greater
  // than the number of cascade piles throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromPileNumberGreaterThanOpens() {
    this.testModel1.startGame(movableDeck, 8, 4, false);
    this.testModel1.move(PileType.OPEN, 5, 0, PileType.OPEN, 1);
  }

  // Checks that moving from an Open pile number less than 0 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromOpenPileNumberUnder0() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.OPEN, -2, 0, PileType.OPEN, 2);
  }

  // Checks that moving from an Open pile with a card index that's too small throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromOpenCardIndexTooSmall() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.OPEN, 3, -3, PileType.OPEN, 0);
  }

  // Checks that moving from an Open pile with a card index that's too big throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromOpenCardIndexTooBig() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.OPEN, 3, 1, PileType.OPEN, 0);
  }

  // Checks that moving from a Foundation pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromFoundation() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.FOUNDATION, 2, 2, PileType.OPEN, 1);
  }

  // Checks that moving to a Cascade pile number greater
  // than the number of cascades throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToPileNumberGreaterThanCascades() {
    this.testModel1.startGame(movableDeck, 8, 4, true);
    this.testModel1.move(PileType.CASCADE, 1, 4, PileType.CASCADE, 10);
  }

  // Checks that moving to a Cascade pile number less than 0 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToCascadePileNumberUnder0() {
    this.testModel1.startGame(movableDeck, 8, 4, true);
    this.testModel1.move(PileType.CASCADE, 1, 4, PileType.CASCADE, -4);
  }

  // Checks that moving a card with an incorrect value to a Cascade pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToCascadeCardIncorrectValue() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 6, 5, PileType.CASCADE, 0);
  }

  // Checks that moving a card with the incorrect color to a Cascade pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToCascadeCardIncorrectColor() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 5, 5, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 3);
  }

  // Checks that moving a card to an Open pile number
  // greater than the number of Opens throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToPileNumberGreaterThanOpens() {
    this.testModel1.startGame(movableDeck, 8, 4, false);
    this.testModel1.move(PileType.CASCADE, 2, 5, PileType.OPEN, 5);
  }

  // Checks that moving a card to an Open pile number less than 0 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToOpenPileNumberUnder0() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.OPEN, -3);
  }

  // Checks that moving a card to an Open pile
  // with a card already in it throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToFullOpenPile() {
    testModel1.startGame(d, 8, 1, false);
    testModel1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
  }

  // Checks that moving to a Foundation pile number greater than 4 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToPileNumberGreaterThanFoundations() {
    testModel1.startGame(movableDeck, 8, 8, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 5);
  }

  // Checks that moving to a Foundation pile number less than 0 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToFoundationPileNumberUnder0() {
    testModel1.startGame(movableDeck, 8, 2, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, -1);
  }

  // Checks that moving an Ace to a non-empty Foundation pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveAceToNonEmptyFoundationPile() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    testModel1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 0);
    testModel1.move(PileType.CASCADE, 3, 4, PileType.OPEN, 1);
    testModel1.move(PileType.CASCADE, 3, 3, PileType.OPEN, 2);
    testModel1.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION, 0);
  }

  // Checks that moving a non-Ace to an empty Foundation pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveNonAceToEmptyFoundationPile() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 2);
  }

  // Checks that moving a card with an incorrect value onto a Foundation pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToFoundationSameSuitIncorrectValue() {
    testModel1.startGame(movableDeck, 8, 3, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 1);
    testModel1.move(PileType.CASCADE, 2, 6, PileType.FOUNDATION, 1);
  }

  // Checks that moving a card with an incorrect suit onto a Foundation pile throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToFoundationIncorrectSuitCorrectValue() {
    testModel1.startGame(movableDeck, 8, 3, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 1);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 1);
  }
}
