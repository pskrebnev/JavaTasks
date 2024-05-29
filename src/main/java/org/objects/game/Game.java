package org.objects.game;

public class Game {

  public Game(String teamFirst
      , String teamSecond
      , int scoreFirst
      , int scoreSecond) {
    this.teamFirst = teamFirst;
    this.teamSecond = teamSecond;
    this.scoreFirst = scoreFirst;
    this.scoreSecond = scoreSecond;
  }

  @Override
  public String toString() {
    return "Game{" +
        "teamFirst='" + teamFirst + '\'' +
        ", teamSecond='" + teamSecond + '\'' +
        ", scoreFirst=" + scoreFirst +
        ", scoreSecond=" + scoreSecond +
        '}';
  }

  public String getTeamFirst() {
    return teamFirst;
  }

  public void setTeamFirst(String teamFirst) {
    this.teamFirst = teamFirst;
  }

  public String getTeamSecond() {
    return teamSecond;
  }

  public void setTeamSecond(String teamSecond) {
    this.teamSecond = teamSecond;
  }

  public int getScoreFirst() {
    return scoreFirst;
  }

  public void setScoreFirst(int scoreFirst) {
    this.scoreFirst = scoreFirst;
  }

  public int getScoreSecond() {
    return scoreSecond;
  }

  public void setScoreSecond(int scoreSecond) {
    this.scoreSecond = scoreSecond;
  }

  private String teamFirst;
  private String teamSecond;
  private int scoreFirst;
  private int scoreSecond;

}
