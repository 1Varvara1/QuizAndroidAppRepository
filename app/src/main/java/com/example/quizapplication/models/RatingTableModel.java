package com.example.quizapplication.models;

public class RatingTableModel implements Comparable<RatingTableModel> {

    String Login;
    Double Score;

    public RatingTableModel(String login, Double score, Integer amount) {
        Login = login;
        Score = score;
        Amount = amount;
    }

    public RatingTableModel() {
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    Integer Amount ;

    @Override
    public int compareTo(RatingTableModel o) {
        return getScore().compareTo(o.getScore());
    }
}
