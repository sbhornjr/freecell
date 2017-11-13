package controller;

import model.FreecellOperations;

import java.util.List;

/**
 * The interface of the Freecell Controller.
 * It is parametrized over the card type.
 */
public interface IFreecellController<K> {

  /**
   * Starts a new game of Freecell using the provided model,
   * number of cascade and open piles, and deck. If "shuffle"
   * is false, the deck should be used as-is, else the deck
   * should be shuffled.
   * @param deck        The deck to be used for the Freecell game
   * @param model       The Freecell model for the game
   * @param numCascades The number of cascade piles for the game
   * @param numOpens    The number of open piles for the game
   * @param shuffle     Whether or not the deck should be shuffled
   * @throws IllegalStateException  if the controller has not been initialized
   *                                properly to receive input and transmit output
   */
  void playGame(List<K> deck, FreecellOperations<K> model,
                int numCascades, int numOpens, boolean shuffle)
          throws IllegalStateException;
}