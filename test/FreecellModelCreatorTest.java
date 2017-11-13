import model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the FreecellModelCreator.
 */
public class FreecellModelCreatorTest {
  FreecellModel singleMoveModel = new FreecellModel();
  MultiMoveFreecellModel multiMoveModel = new MultiMoveFreecellModel();
  FreecellModelCreator creator = new FreecellModelCreator();
  FreecellModelCreator.GameType singleMove = FreecellModelCreator.GameType.SINGLEMOVE;
  FreecellModelCreator.GameType multiMove = FreecellModelCreator.GameType.MULTIMOVE;

  // Checks that a single move model is successfully created.
  @Test
  public void singleMoveModelTest() {
    assertEquals(singleMoveModel, creator.create(singleMove));
  }

  // Checks that a multi move model is succesfully created.
  @Test
  public void multiMoveModelTest() {
    assertEquals(multiMoveModel, creator.create(multiMove));
  }
}
