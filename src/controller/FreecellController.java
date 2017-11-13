package controller;

import model.Card;
import model.FreecellOperations;
import model.PileType;

import java.io.IOException;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * My implementation of the Freecell Controller.
 */
public class FreecellController implements IFreecellController<Card> {
  private Readable in;
  private Appendable out;
  private PileType srcPileType;
  private int srcPileNum;
  private int cardIndex;
  private PileType destPileType;
  private int destPileNum;

  /**
   * Constructor for the Freecell Controller.
   * @param rd  The readable input.
   * @param ap  The appendable output.
   */
  public FreecellController(Readable rd, Appendable ap) {
    this.in = rd;
    this.out = ap;
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model,
                       int numCascades, int numOpens, boolean shuffle)
          throws IllegalStateException {

    if (deck == null) {
      throw new IllegalArgumentException("Deck must not be null.");
    }

    if (model == null) {
      throw new IllegalArgumentException("Model must not be null.");
    }

    if (this.in == null) {
      throw new IllegalStateException("The Readable must not be null.");
    }

    if (this.out == null) {
      throw new IllegalStateException("The Appendable must not be null.");
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      try {
        out.append("Could not start game.");
      } catch (IOException e1) {
        System.out.println("Caught IOException: " + e1.getMessage());
      }
      return ;
    }

    Scanner sc = new Scanner(Objects.requireNonNull(this.in));

    while (!model.isGameOver()) {
      try {
        out.append(model.getGameState() + "\n");
      } catch (IOException e) {
        System.out.println("Caught IOException: " + e.getMessage());
      }

      try {
        out.append("Enter source pile:\n");
      } catch (IOException e) {
        System.out.println("Caught IOException: " + e.getMessage());
      }

      boolean srcDone = this.handleSource(sc);
      if (!srcDone) {
        return ;
      }

      try {
        out.append("Enter card index:\n");
      } catch (IOException e) {
        System.out.println("Caught IOException: " + e.getMessage());
      }

      boolean cIDone = this.handleCardIndex(sc);
      if (!cIDone) {
        return ;
      }

      try {
        out.append("Enter destination pile:\n");
      } catch (IOException e) {
        System.out.println("Caught IOException: " + e.getMessage());
      }

      boolean destDone = this.handleDestination(sc);
      if (!destDone) {
        return ;
      }

      try {
        model.move(srcPileType, srcPileNum - 1,
                cardIndex - 1, destPileType, destPileNum - 1);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid move:\n" + e.getMessage()
                + "\nTry again:\n");
      }
    }

    try {
      out.append(model.getGameState() + "\nGame over.");
    } catch (IOException e) {
      System.out.println("Caught IOException: " + e.getMessage());
    }
  }

  /**
   * Takes in and sets the given source pile info.
   * @param sc  The Scanner whose next item should be the source pile info.
   */
  private boolean handleSource(Scanner sc) {
    while (true) {
      String src = sc.next();
      char sPT = src.charAt(0);
      StringBuilder sPNBuild = new StringBuilder();

      for (int i = 1; i < src.length(); i++) {
        sPNBuild.append(src.charAt(i));
      }

      String sPN = sPNBuild.toString();

      switch (sPT) {
        case 'C':
          srcPileType = PileType.CASCADE;
          break;
        case 'O':
          srcPileType = PileType.OPEN;
          break;
        case 'q' :
          try {
            out.append("Game quit prematurely.");
          } catch (IOException e) {
            System.out.println("Caught IOException" + e.getMessage());
          }
          return false;
        case 'Q' :
          try {
            out.append("Game quit prematurely.");
          } catch (IOException e) {
            System.out.println("Caught IOException" + e.getMessage());
          }
          return false;
        default:
          try {
            out.append("Invalid source input. "
                    + "Must be correct Pile type.\nEnter source pile:\n");
          } catch (IOException e) {
            System.out.println("Caught IOException" + e.getMessage());
          }
          continue;
      }

      try {
        srcPileNum = Integer.parseInt(sPN);
      } catch (NumberFormatException e) {
        System.out.println("Invalid source input. "
                + "Pile number must be a number.\nEnter source pile:\n");
        continue;
      }

      return true;
    }
  }

  /**
   * Takes in and sets the given card index.
   * @param sc  The Scanner whose next item should be the card index.
   */
  private boolean handleCardIndex(Scanner sc) {
    while (true) {
      String index = sc.next();

      if (index.equals("q") || index.equals("Q")) {
        try {
          out.append("Game quit prematurely.");
        } catch (IOException e) {
          System.out.println("Caught IOException" + e.getMessage());
        }
        return false;
      }

      try {
        cardIndex = Integer.parseInt(index);
      } catch (NumberFormatException e) {
        System.out.println("Invalid card index. "
                + "Card index must be a number.\nEnter card index:\n");
        continue;
      }

      return true;
    }
  }

  /**
   * Takes in and sets the given destination pile info.
   * @param sc  The Scanner whose next item should be the destination pile info.
   */
  private boolean handleDestination(Scanner sc) {
    while (true) {
      String dest = sc.next();
      char dPT = dest.charAt(0);
      StringBuilder dPNBuild = new StringBuilder();

      for (int i = 1; i < dest.length(); i++) {
        dPNBuild.append(dest.charAt(i));
      }

      String dPN = dPNBuild.toString();

      switch (dPT) {
        case 'C':
          destPileType = PileType.CASCADE;
          break;
        case 'O':
          destPileType = PileType.OPEN;
          break;
        case 'F':
          destPileType = PileType.FOUNDATION;
          break;
        case 'q' :
          try {
            out.append("Game quit prematurely.");
          } catch (IOException e) {
            System.out.println("Caught IOException" + e.getMessage());
          }
          return false;
        case 'Q' :
          try {
            out.append("Game quit prematurely.");
          } catch (IOException e) {
            System.out.println("Caught IOException" + e.getMessage());
          }
          return false;
        default:
          try {
            out.append("Invalid destination input. "
                    + "Must be correct Pile type.\nEnter destination pile:\n");
          } catch (IOException e) {
            System.out.println("Caught IOException" + e.getMessage());
          }
          continue;
      }

      try {
        destPileNum = Integer.parseInt(dPN);
      } catch (NumberFormatException e) {
        System.out.println("Invalid destination input. "
                + "Pile number must be a number.\nEnter destination pile:\n");
        continue;
      }

      return true;
    }
  }
}
