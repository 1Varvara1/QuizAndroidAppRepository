package com.example.quizapplication.JsonModels;

public class Question {

    String question;
    String [] answers;
    int correct_answer;
    Questinarie questionCollection;
    public Question(String question, String[] answers, int correct_answer, String question_category) {
        this.question = question;
        this.answers = answers;
        this.correct_answer = correct_answer;
        this.question_category = question_category;
    }
    public Question() {

    }

    String question_category;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getQuestion_category() {
        return question_category;
    }

    public void setQuestion_category(String question_category) {
        this.question_category = question_category;
    }
}
