package com.example.quizapplication.JsonModels;

import java.util.ArrayList;

public class CategoryItem {
    public ArrayList<Category> getItems() {
        return items;
    }

    public CategoryItem(ArrayList<Category> items) {
        this.items = items;
    }

    public void setItems(ArrayList<Category> items) {
        this.items = items;
    }

    ArrayList<Category> items;
}
