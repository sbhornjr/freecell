package model;

import model.FreecellModel;
import model.FreecellOperations;

public class FreecellModelCreator {
  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }

  /**
   * Creates either a single move or multi game of Freecell.
   * @param type  The type of game to be created (single or multi move).
   * @return      A FreecellOperations (either single or multi move).
   */
  public static FreecellOperations<Card> create(GameType type) {
    if (type == GameType.SINGLEMOVE) {
      return new FreecellModel();
    }
    else if (type == GameType.MULTIMOVE) {
      return new MultiMoveFreecellModel();
    }
    else {
      throw new IllegalArgumentException("Game type must be either SINGLEMOVE or MULTIMOVE.");
    }
  }
}
