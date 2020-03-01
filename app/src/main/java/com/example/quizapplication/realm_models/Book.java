package com.example.quizapplication.realm_models;

import io.realm.RealmObject;

public class Book  extends RealmObject {

    public String Theme;

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public  String Name;

    public String getName() {
        return Name;
    }

    public Book(String theme, String name, String autor, String imageUrl) {
        Theme = theme;
        Name = name;
        Autor = autor;
        ImageUrl = imageUrl;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public   String Autor;
    public  String ImageUrl;

    public Book() {

    }
}
