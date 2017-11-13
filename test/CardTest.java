import model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for Cards.
 */
public class CardTest {
  Card testCard1 = new Card(1, "diamond");
  Card testCard2 = new Card(6, "spade");
  Card testCard3 = new Card(8, "club");
  Card testCard4 = new Card(11, "heart");
  Card testCard5 = new Card(12, "club");
  Card testCard6 = new Card(13, "heart");
  Card testCard7 = new Card(1, "diamond");

  @Test
  public void toStringTest1() {
    assertEquals(testCard1.toString(), "A♦");
  }

  @Test
  public void toStringTest2() {
    assertEquals(testCard2.toString(), "6♠");
  }

  @Test
  public void toStringTest3() {
    assertEquals(testCard3.toString(), "8♣");
  }

  @Test
  public void toStringTest4() {
    assertEquals(testCard4.toString(), "J♥");
  }

  @Test
  public void toStringTest5() {
    assertEquals(testCard5.toString(), "Q♣");
  }

  @Test
  public void toStringTest6() {
    assertEquals(testCard6.toString(), "K♥");
  }

  @Test
  public void equalsTest1() {
    assertEquals(true, testCard1.equals(testCard7));
  }

  @Test
  public void equalsTest2() {
    assertEquals(false, testCard1.equals(testCard2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void invValueTest1() {
    new Card(0, "diamond");
  }

  @Test (expected = IllegalArgumentException.class)
  public void invValueTest2() {
    new Card(14, "heart");
  }

  @Test (expected = IllegalArgumentException.class)
  public void invSuitTest() {
    new Card(4, "hello");
  }
}
