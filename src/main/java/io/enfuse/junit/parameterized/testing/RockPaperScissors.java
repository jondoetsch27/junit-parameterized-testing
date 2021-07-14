package io.enfuse.junit.parameterized.testing;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RockPaperScissors {

  private static final String[][] winningMoves =
      new String[][]{{"paper", "rock"}, {"rock", "scissors"}, {"scissors", "paper"}};

  public String play(String player1, String player2) {

    if (player1 == null || player2 == null) {
      throw new RuntimeException("invalid input - null values");
    } else if (player1.isEmpty() || player2.isEmpty()) {
      throw new RuntimeException("invalid input - empty strings");
    } else if (player1.toLowerCase().equals(player2.toLowerCase())) {
      return "Draw";
    }

    for (String[] winningMove : winningMoves) {
      if (player1.toLowerCase().equals(winningMove[0])
          && player2.toLowerCase().equals(winningMove[1])) {
        return "Player One Wins";
      } else if (player2.toLowerCase().equals(winningMove[0])
          && player1.toLowerCase().equals(winningMove[1])) {
        return "Player Two Wins";
      }
    }
    throw new RuntimeException("Unable to determine the winner");
  }
}
