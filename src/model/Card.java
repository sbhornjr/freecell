package model;

/**
 * Represents a playing card.
 */
public final class Card {
  public final int value;
  public final String suit;
  public final String color;

  /**
   * Constructs a Card object.
   * @param value   the value of the card (A, 2, 3, ... , J, Q, K)
   * @param suit    the suit of the card (heart, diamond, club, spade)
   */
  public Card(int value, String suit) {
    if (1 <= value && value <= 13) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Invalid card value.");
    }
    if (suit.equals("heart") || suit.equals("diamond")) {
      this.suit = suit;
      this.color = "red";
    }
    else if (suit.equals("club") || suit.equals("spade")) {
      this.suit = suit;
      this.color = "black";
    }
    else {
      throw new IllegalArgumentException("Invalid card suit.");
    }
  }

  /**
   * Converts the card to a String based on its value and suit.
   * @return    the Card as a string
   */
  public String toString() {
    switch (value) {
      case 1: return "A" + getSuitSymbol();
      case 11: return "J" + getSuitSymbol();
      case 12: return "Q" + getSuitSymbol();
      case 13: return "K" + getSuitSymbol();
      default: return Integer.toString(value) + getSuitSymbol();
    }
  }

  /**
   * Converts the Card's suit to a string of its symbol.
   * @return    The Card's suit as a string of its symbol.
   */
  private String getSuitSymbol() {
    switch (suit) {
      case "heart": return "♥";
      case "diamond": return "♦";
      case "club": return "♣";
      case "spade": return "♠";
      default: throw new IllegalArgumentException("Invalid card suit.");
    }
  }

  /**
   * Determines whether the given object is equal to this card.
   * @param o   The object that may or may not be equal to this card.
   * @return    True if they are the same, false if they are not.
   */
  public boolean equals(Object o) {
    if (!(o instanceof Card)) {
      return false;
    }
    else {
      return this.toString().equals(o.toString());
    }
  }

  /**
   * Override for hashCode because I did an override for equals.
   * @return    an int representing this Card.
   */
  public int hashCode() {
    switch (this.suit) {
      case "heart": return this.value;
      case "diamond": return this.value + 13;
      case "club": return this.value + 26;
      case "spade": return this.value + 39;
      default: throw new IllegalArgumentException("Invalid card suit.");
    }
  }
}