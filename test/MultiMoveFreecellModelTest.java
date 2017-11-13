import model.PileType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Main testing class for FreecellModel.
 */
public class MultiMoveFreecellModelTest extends FreecellOperationsTest {
  public MultiMoveFreecellModelTest() {
    super("multi");
  }

  // Checks that moving a build of 3 cards with enough empty opens is successful.
  @Test
  public void move3EnoughEmpties() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 1);
    testModel1.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 7);
    testModel1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 7);
    testModel1.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 1);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦, 6♦, 5♠, 4♥, 3♠, 2♥\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 7♠, Q♥, 9♣", testModel1.getGameState());
  }

  // Checks that moving a build of 5 cards onto an
  // empty cascade with enough empty opens is successful.
  @Test
  public void move5OntoEmptyCascade() {
    testModel1.startGame(movableDeck, 8, 10, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 1);
    testModel1.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 7);
    testModel1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 7);
    testModel1.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 1);
    testModel1.move(PileType.CASCADE, 7, 2, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 7, 1, PileType.OPEN, 1);
    testModel1.move(PileType.CASCADE, 7, 0, PileType.OPEN, 2);
    testModel1.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 7);
    assertEquals("F1: A♠\nF2:\nF3:\nF4:\nO1: 9♣\nO2: Q♥\nO3: 7♠\nO4:" +
            "\nO5:\nO6:\nO7:\nO8:\nO9:\nO10:\n" +
            "C1: J♦, 3♥, 2♠, 4♣, 10♠, 6♣, 10♣\n" +
            "C2: K♠, 7♣, 4♠, 9♠, 2♣, Q♦\n" +
            "C3: 4♦, A♣, 10♥, 5♣, 2♦, 5♦\n" +
            "C4: J♠, Q♣, A♥, 10♦, 6♥, 9♥, 8♦\n" +
            "C5: 8♣, 8♥, J♥, Q♠, J♣\n" +
            "C6: A♦, 6♠, K♥, K♦, 7♦, 8♠\n" +
            "C7: 7♥, 5♥, 3♦, K♣, 9♦, 3♣\n" +
            "C8: 6♦, 5♠, 4♥, 3♠, 2♥", testModel1.getGameState());
  }

  // Checks that moving from a pile number less than 0 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromCascadeLessThan0() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, -2, 4, PileType.CASCADE, 3);
  }

  // Checks that moving from a pile number greater than the
  // number of Cascade piles throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveFromCascadeGreaterThanCascades() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 9, 1, PileType.CASCADE, 8);
  }

  // Checks that moving without enough empty opens throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void move3NotEnoughEmpties() {
    testModel1.startGame(movableDeck, 8, 1, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 1);
    testModel1.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 7);
    testModel1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 7);
    testModel1.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 1);
  }

  // Checks that moving a non-built stack throws an exception because of an invalid card value.
  @Test (expected = IllegalArgumentException.class)
  public void nonBuiltStackInvalidValue() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
    testModel1.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 4);
  }

  // Checks that moving a non-built stack throws an exception because of an invalid card color.
  @Test (expected = IllegalArgumentException.class)
  public void nonBuiltStackInvalidColor() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 0);
  }

  // Checks that moving a non-built stack throws
  // an exception because of a completely invalid card.
  @Test (expected = IllegalArgumentException.class)
  public void nonBuiltStackBothInvalid() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 1, 6, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 1);
  }

  // Checks that an exception is thrown if moving a built stack creates an
  // un-built stack due to the top card being an invalid card due to value.
  @Test (expected = IllegalArgumentException.class)
  public void nonBuiltAfterMoveInvalidValue() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 4);
  }

  // Checks that an exception is thrown if moving a built stack creates an
  // un-built stack due to the top card being an invalid card due to color.
  @Test (expected = IllegalArgumentException.class)
  public void nonBuiltAfterMoveInvalidColor() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    testModel1.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 0);
    testModel1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 1);
    testModel1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 4);
  }

  // Checks that an exception is thrown if moving a built stack creates an
  // un-built stack due to the top card being an invalid card due to both.
  @Test (expected = IllegalArgumentException.class)
  public void nonBuiltAfterMoveBothInvalid() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 3);
  }

  // Checks that moving a built stack to a pile number less than 0 throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToCascadeLessThan0() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.CASCADE, -3);
  }

  // Checks that moving a built stack to a pile number
  // greater than the number of Cascades throws an exception.
  @Test (expected = IllegalArgumentException.class)
  public void moveToCascadeGreaterThanCascades() {
    testModel1.startGame(movableDeck, 8, 4, false);
    testModel1.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 12);
  }

}