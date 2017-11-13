package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * My implementation of a multi move Freecell game.
 */
public class MultiMoveFreecellModel extends FreecellModel {

  /**
   * Constructor for the multi move freecell model.
   */
  public MultiMoveFreecellModel() {
    started = false;
    cascade = new ArrayList<>();
    open = new ArrayList<>();
    foundation = new ArrayList<>();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) throws IllegalArgumentException {
    if (pileNumber < 0) {
      throw new IllegalArgumentException("Source pile number must be above 0.");
    }
    if (pileNumber >= cascade.size()) {
      throw new IllegalArgumentException("Source pile number "
              + "must be less than the number of Cascade piles.");
    }
    if (source == PileType.CASCADE
            && cascade.get(pileNumber).size() - 1 != cardIndex
            && destination == PileType.CASCADE) {
      this.multiMove(pileNumber, cardIndex, destPileNumber);
    }
    else {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

  /**
   * Handles multi-card moves in the Freecell game.
   * @param pileNumber      The pile number of the Cascade pile to move from.
   * @param cardIndex       The card index of the bottom-most card to be moved.
   * @param destPileNumber  The pile number of the destination pile.
   * @throws IllegalArgumentException If the cards to be moved are not "built",
   *                                      if the resulting stack of cards would not be built,
   *                                      if there are not enough empty piles
   *                                      to make the move, or if the destination pile is invalid.
   */
  private void multiMove(int pileNumber, int cardIndex,
                         int destPileNumber) throws IllegalArgumentException {

    if (destPileNumber < 0) {
      throw new IllegalArgumentException("Destination pile number must be above 0.");
    }
    if (destPileNumber >= cascade.size()) {
      throw new IllegalArgumentException("Destination pile number must be "
              + "less than the number of Cascade piles.");
    }

    ArrayList<Card> onTheMove = new ArrayList<>();
    for (int i = cardIndex; i < cascade.get(pileNumber).size(); i++) {
      onTheMove.add(cascade.get(pileNumber).get(i));
    }

    this.checkBuilt(onTheMove);

    int emptyOpens = this.countEmpties(PileType.OPEN, destPileNumber);
    int emptyCascades = this.countEmpties(PileType.CASCADE, destPileNumber);

    if ((emptyOpens + 1) * Math.pow(2, emptyCascades)
            < onTheMove.size()) {
      throw new IllegalArgumentException("Can't perform multi-move: not enoguh empty piles.");
    }
    else {
      Card origBottom = cascade.get(pileNumber).get(cardIndex);

      if (cascade.get(destPileNumber).size() == 0) {
        cascade.get(destPileNumber).addAll(onTheMove);
      }
      else {
        Card destTop = cascade.get(destPileNumber).get(cascade.get(destPileNumber).size() - 1);

        if (destTop.color != origBottom.color && destTop.value - 1 == origBottom.value) {
          cascade.get(destPileNumber).addAll(onTheMove);
        } else if (destTop.color != origBottom.color) {
          throw new IllegalArgumentException("To move a card on top of another on a Cascade pile, "
                  + "its value must be one lower than the card that is already on the top.");
        } else if (destTop.value - 1 == origBottom.value) {
          throw new IllegalArgumentException("To move a card on top of another on a Cascade pile, "
                  + "its suit must be the opposite color of the card that is already on top.");
        } else {
          throw new IllegalArgumentException("To move a card on top of another on a Cascade pile, "
                  + "its value must be one lower and its suit must be the opposite color of the "
                  + "card that is already on top.");
        }
      }

      Iterator<Card> iter = cascade.get(pileNumber).iterator();

      while (iter.hasNext()) {
        Card curr = iter.next();

        if (onTheMove.contains(curr)) {
          iter.remove();
        }
      }
    }
  }

  /**
   * Checks that the given list is "built" (is in descending order and alternates in color).
   * @param onTheMove    The list that we are checking is built.
   * @throws IllegalArgumentException If the list is not built.
   */
  private void checkBuilt(List<Card> onTheMove) throws IllegalArgumentException {
    String lastColor = onTheMove.get(0).color;
    int lastValue = onTheMove.get(0).value;

    for (int i = 1; i < onTheMove.size(); i++) {
      Card curr = onTheMove.get(i);
      if (!curr.color.equals(lastColor) && curr.value == lastValue - 1) {
        lastColor = curr.color;
        lastValue = curr.value;
      } else {
        throw new IllegalArgumentException("Stack is not Built: \nIndex: "
                + Integer.toString(i) + "\nValue: "
                + Integer.toString(curr.value) + "\nColor: " + curr.color + "\nLast Value: "
                + Integer.toString(lastValue) + "\nLast Color: " + lastColor);
      }
    }
  }

  /**
   * Counts the number of Open and Cascade piles that are empty,
   * not including the destination pile.
   * @param type            The type of pile
   * @param destPileNumber  The destination pile number.
   * @return                The amount of empty Open and Cascade piles.
   */
  private int countEmpties(PileType type, int destPileNumber) {
    int count = 0;

    if (type == PileType.OPEN) {
      for (int i = 0; i < open.size(); i++) {
        if (open.get(i).size() == 0) {
          count++;
        }
      }
    }
    else {
      for (int i = 0; i < cascade.size(); i++) {
        if (i == destPileNumber) {
          continue;
        }
        if (cascade.get(i).size() == 0) {
          count++;
        }
      }
    }
    return count;
  }
}