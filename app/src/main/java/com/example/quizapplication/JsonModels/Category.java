package com.example.quizapplication.JsonModels;

public class Category {

    public Category(String question_category, String category_name) {
        this.question_category = question_category;
        this.category_name = category_name;
    }

    public String getQuestion_category() {
        return question_category;
    }

    public void setQuestion_category(String question_category) {
        this.question_category = question_category;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    String question_category;
    String category_name;
}
