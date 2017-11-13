package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * My implementation of a single move Freecell game.
 * For hw04: Added the ability to move a card to an empty Cascade pile (lines 154-156).
 */
public class FreecellModel implements FreecellOperations<Card> {
  protected ArrayList<ArrayList<Card>> cascade = new ArrayList<>();
  protected ArrayList<ArrayList<Card>> open = new ArrayList<>();
  protected ArrayList<ArrayList<Card>> foundation = new ArrayList<>();
  protected boolean started;

  /**
   * Constructor for the single move freecell model.
   */
  public FreecellModel() {
    started = false;
    cascade = new ArrayList<>();
    open = new ArrayList<>();
    foundation = new ArrayList<>();
  }

  @Override
  public List<Card> getDeck() {
    List<Card> d = new ArrayList<>();
    Card newCard;
    for (int i = 0; i < 4; i++) {
      for (int j = 1; j < 14; j++) {
        switch (i) {
          case 0:
            newCard = new Card(j, "heart");
            break;
          case 1:
            newCard = new Card(j, "diamond");
            break;
          case 2:
            newCard = new Card(j, "club");
            break;
          case 3:
            newCard = new Card(j, "spade");
            break;
          default:
            throw new IllegalArgumentException("This should be impossible to get to");
        }
        d.add(newCard);
      }
    }
    return d;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles,
                        boolean shuffle) throws IllegalArgumentException {
    if (deck.size() != 52) {
      throw new IllegalArgumentException("Invalid deck.");
    }

    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("Must have at least 4 cascade piles.");
    }

    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("Must have at least 1 open pile.");
    }

    if (this.hasDuplicates(deck)) {
      throw new IllegalArgumentException("Deck cannot have duplicates.");
    }

    this.started = true;
    List<Card> d = deck;

    if (shuffle) {
      Collections.shuffle(d);
    }

    this.open.clear();
    this.foundation.clear();
    this.cascade.clear();

    for (int i = 0; i < numCascadePiles; i++) {
      this.cascade.add(new ArrayList<>());
    }

    for (int i = 0; i < numOpenPiles; i++) {
      this.open.add(new ArrayList<>());
    }

    for (int i = 0; i < 4; i++) {
      this.foundation.add(new ArrayList<>());
    }

    int deckIndex = 0;

    for (int i = 0; i < (52 / numCascadePiles) + 1; i++) {
      if (deckIndex >= 52) {
        break;
      }
      for (int j = 0; j < numCascadePiles; j++) {
        if (deckIndex >= 52) {
          break;
        }
        this.cascade.get(j).add(d.get(deckIndex));
        deckIndex++;
      }
    }
  }

  /**
   * Determines whether the given deck contains any duplicate cards.
   * @param deck  the game's deck to be checked for duplicates.
   * @return      true if the deck has duplicates, false if it doesn't.
   */
  private static boolean hasDuplicates(List<Card> deck) {
    Set<Card> set = new HashSet<>(deck);

    return set.size() < deck.size();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) throws IllegalArgumentException {
    Card onTheMove = null;

    if (source == PileType.CASCADE) {
      if (pileNumber >= cascade.size() || pileNumber < 0) {
        throw new IllegalArgumentException("Invalid source pile number.");
      }
      if (cascade.get(pileNumber).size() - 1 != cardIndex) {
        throw new IllegalArgumentException("You can only move the top card of a Cascade pile.");
      }
      onTheMove = cascade.get(pileNumber).remove(cardIndex);
    }
    else if (source == PileType.OPEN) {
      if (pileNumber >= open.size() || pileNumber < 0) {
        throw new IllegalArgumentException("Invalid source pile number.");
      }
      if (cardIndex != 0) {
        throw new IllegalArgumentException("You can only move the top card of an Open pile.");
      }
      onTheMove = open.get(pileNumber).remove(cardIndex);
    }
    else if (source == PileType.FOUNDATION) {
      throw new IllegalArgumentException("Can't move a card out of a "
              + "Foundation pile once it is on.");
    }

    if (destination == PileType.CASCADE) {
      if (destPileNumber >= cascade.size() || destPileNumber < 0) {
        this.returnToSource(onTheMove, source, pileNumber);
        throw new IllegalArgumentException("Invalid destination pile number.");
      }

      if (cascade.get(destPileNumber).size() == 0) {
        cascade.get(destPileNumber).add(onTheMove);
      }
      else {
        int destCascPileTopIndex = cascade.get(destPileNumber).size() - 1;

        Card destCascPileTop = cascade.get(destPileNumber).get(destCascPileTopIndex);

        if (destCascPileTop.value - 1 != onTheMove.value) {
          this.returnToSource(onTheMove, source, pileNumber);
          throw new IllegalArgumentException("To move a card on top of another on a Cascade pile, "
                  + "its value must be one lower than the card that is already on the top.");
        }

        if (destCascPileTop.color.equals(onTheMove.color)) {
          this.returnToSource(onTheMove, source, pileNumber);
          throw new IllegalArgumentException("To move a card on top of another on a Cascade pile, "
                  + "its suit must be the opposite color of the card that is already on top.");
        }

        cascade.get(destPileNumber).add(onTheMove);
      }
    }
    else if (destination == PileType.OPEN) {
      if (destPileNumber >= open.size() || destPileNumber < 0) {
        this.returnToSource(onTheMove, source, pileNumber);
        throw new IllegalArgumentException("Invalid destination pile number.");
      }
      if (open.get(destPileNumber).size() > 0) {
        this.returnToSource(onTheMove, source, pileNumber);
        throw new IllegalArgumentException("Already a card in that Open pile.");
      } else {
        open.get(destPileNumber).add(onTheMove);
      }
    }
    else if (destination == PileType.FOUNDATION) {
      if (destPileNumber >= 4 || destPileNumber < 0) {
        this.returnToSource(onTheMove, source, pileNumber);
        throw new IllegalArgumentException("Invalid destination pile number.");
      }

      if (onTheMove.value == 1 && foundation.get(destPileNumber).isEmpty()) {
        foundation.get(destPileNumber).add(onTheMove);
      }
      else if (onTheMove.value == 1) {
        this.returnToSource(onTheMove, source, pileNumber);
        throw new IllegalArgumentException("Must move an Ace onto an empty Foundation pile.");
      }
      else if (foundation.get(destPileNumber).isEmpty()) {
        this.returnToSource(onTheMove, source, pileNumber);
        throw new IllegalArgumentException("Only an Ace can be "
                + "moved onto an empty Foundation pile.");
      }
      else {
        int destFoundPileTopIndex = foundation.get(destPileNumber).size() - 1;

        Card destFoundPileTop = foundation.get(destPileNumber).get(destFoundPileTopIndex);

        if (destFoundPileTop.value + 1 != onTheMove.value) {
          this.returnToSource(onTheMove, source, pileNumber);
          throw new IllegalArgumentException("To move a card to a Foundation pile, "
                  + "its value must be one higher than the card that is already on the top.");
        }

        if (!destFoundPileTop.suit.equals(onTheMove.suit)) {
          this.returnToSource(onTheMove, source, pileNumber);
          throw new IllegalArgumentException("To move a card to a Foundation pile, "
                  + "its suit must be the same as the card that is already on the top.");
        }

        foundation.get(destPileNumber).add(onTheMove);
      }
    }
  }

  /**
   * Returns the card to its original place due to an attempted invalid move.
   * @param toBeReturned    The card to be returned
   * @param source          The source pile type
   * @param pileNumber      The source pile number
   */
  private void returnToSource(Card toBeReturned, PileType source, int pileNumber) {
    if (source == PileType.CASCADE) {
      cascade.get(pileNumber).add(toBeReturned);
    }
    else if (source == PileType.OPEN) {
      open.get(pileNumber).add(toBeReturned);
    }
    else {
      throw new IllegalArgumentException("Shouldn't be possible to get here.");
    }
  }

  @Override
  public boolean isGameOver() {
    return this.foundation.get(0).size() == 13
            && this.foundation.get(1).size() == 13
            && this.foundation.get(2).size() == 13
            && this.foundation.get(3).size() == 13;
  }

  @Override
  public String getGameState() {
    String soFar = "";

    if (!started) {
      return soFar;
    }
    for (int i = 1; i < 5; i++) {
      soFar = soFar.concat("F" + i + ":");
      ArrayList<Card> currPile = foundation.get(i - 1);
      if (currPile.size() == 0) {
        soFar = soFar.concat("");
      } else {
        for (int j = 0; j < currPile.size(); j++) {
          soFar = soFar.concat(" " + currPile.get(j).toString() + ",");
        }
      }
      soFar = soFar.concat("\n");
    }
    for (int i = 1; i < open.size() + 1; i++) {
      soFar = soFar.concat("O" + i + ":");
      ArrayList<Card> currPile = open.get(i - 1);
      if (currPile.size() == 0) {
        soFar = soFar.concat("");
      } else {
        soFar = soFar.concat(" " + currPile.get(0).toString());
      }
      soFar = soFar.concat("\n");
    }
    for (int i = 1; i < cascade.size(); i++) {
      soFar = soFar.concat("C" + i + ":");
      ArrayList<Card> currPile = cascade.get(i - 1);
      if (currPile.size() == 0) {
        soFar = soFar.concat("");
      } else {
        for (int j = 0; j < currPile.size(); j++) {
          soFar = soFar.concat(" " + currPile.get(j).toString() + ",");
        }
      }
      soFar = soFar.concat("\n");
    }

    ArrayList<Card> lastCascadePile = cascade.get(cascade.size() - 1);

    soFar = soFar.concat("C" + cascade.size() + ":");

    if (lastCascadePile.size() == 0) {
      soFar = soFar.concat("");
    }
    else if (lastCascadePile.size() == 1) {
      soFar = soFar.concat(lastCascadePile.get(0).toString());
    }
    else {
      for (int i = 0; i < lastCascadePile.size() - 1; i++) {
        soFar = soFar.concat(" " + lastCascadePile.get(i).toString() + ",");
      }
      soFar = soFar.concat(" " + lastCascadePile.get(lastCascadePile.size() - 1).toString());
    }

    soFar = soFar.replace(",\n", "\n");

    return soFar;
  }
}