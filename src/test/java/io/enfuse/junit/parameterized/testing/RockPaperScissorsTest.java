package io.enfuse.junit.parameterized.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RockPaperScissorsTest {

  private RockPaperScissors subject;

  @BeforeEach
  public void setUp() {
    subject = new RockPaperScissors();
  }

  @ParameterizedTest
  @ValueSource(strings = {"paper", "Paper", "PAPER"})
  public void givenPlayerTwoUsesRock_returnWinningPlayer(String player1) {
    String player2 = "rock";
    String expectedResult = "Player One Wins";
    String actualResult = subject.play(player1, player2);
    assertEquals(expectedResult, actualResult);
  }

  @ParameterizedTest
  @NullSource
  public void givenNullInput_throwRuntimeException(String nullInput) {
    String player1 = nullInput;
    String player2 = nullInput;
    String expectedResult = "invalid input";
    try {
      String tempString = subject.play(player1, player2);
    } catch (RuntimeException actualResult) {
      assertTrue(actualResult.getMessage().contains(expectedResult));
    }
  }

  @ParameterizedTest
  @NullAndEmptySource
  public void givenInvalidInput_throwRuntimeException(String invalidInput) {
    String player1 = invalidInput;
    String player2 = invalidInput;
    String expectedResult = "invalid input";
    try {
      String tempString = subject.play(player1, player2);
    } catch (RuntimeException actualResult) {
      assertTrue(actualResult.getMessage().contains(expectedResult));
    }
  }


  @ParameterizedTest
  @CsvSource({"paper,rock", "rock,scissors", "scissors,paper"})
  public void givenWinningMoveByPlayerOne_returnPlayerOneWins(String player1, String player2) {
    String expectedResult = "Player One Wins";
    String actualResult = subject.play(player1, player2);
    assertEquals(expectedResult, actualResult);
  }

  @ParameterizedTest
  @CsvSource(delimiter = ':', value = {
      "paper:rock:Player One Wins",
      "rock:rock:Draw",
      "scissors:rock:Player Two Wins"
  })
  public void givenValidMoves_determineWinner(
      String player1, String player2, String expectedResult) {
    String actualResult = subject.play(player1, player2);
    assertEquals(expectedResult, actualResult);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/player-moves.csv", numLinesToSkip = 1)
  public void givenValidMovesFromFile_determineWinner(
      String player1, String player2, String expectedResult) {
    String actualResult = subject.play(player1, player2);
    assertEquals(expectedResult, actualResult);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/player-moves.csv", numLinesToSkip = 1)
  public void givenValidMoves_determineWinner_withAccessor(ArgumentsAccessor argumentsAccessor) {
    String player1 = argumentsAccessor.getString(0);
    String player2 = argumentsAccessor.getString(1);
    String expectedResult = argumentsAccessor.getString(2);
    String actualResult = subject.play(player1, player2);
    assertEquals(expectedResult, actualResult);
  }

  @ParameterizedTest
  @EnumSource(ValidMove.class)
  public void givenDuplicateMoves_returnDraw(ValidMove validMove) {
    String player1 = validMove.name();
    String player2 = validMove.name();
    String expectedResult = "Draw";
    String actualResult = subject.play(player1, player2);
    assertEquals(expectedResult, actualResult);
  }
}
