package com.example.quizapplication.JsonModels;

public class CategoryItem {
    public CategoryItem(Category[] items) {
        this.items = items;
    }

    public Category[] getItems() {
        return items;
    }

    public void setItems(Category[] items) {
        this.items = items;
    }

    Category [] items;
}
