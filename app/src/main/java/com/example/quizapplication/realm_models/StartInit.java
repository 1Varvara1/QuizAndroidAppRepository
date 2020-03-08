package com.example.quizapplication.realm_models;

import io.realm.Realm;

public class StartInit {

    public void StartInit(){

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        User user = realm.createObject(User.class);

        user.setLogin("moderator");
        user.setEmail("moderator@gmail.com");
        user.setRole("moderator");
        user.setPassword("Aa111_");
        user.setPoints(0);


        User user2 = realm.createObject(User.class);
        user2.setLogin("mimi");
        user2.setEmail("mimi@gmail.com");
        user2.setRole("user");
        user2.setPassword("111111");
        user2.setPoints(9.5);

        realm.commitTransaction();

        realm.beginTransaction();
        TestPass pass = realm.createObject(TestPass.class);
        pass.setLogin("mimi");
        pass.setPoints(9.5);
        pass.setTestName("Android Test 1");
        pass.setWrongAns(2);
        pass.setRighAns(23);
        pass.setTotalAns(25);
        pass.setDate("16/02/2020 15:05");



        Book book = realm.createObject(Book.class);
        book.setName("CLR via C#");
        book.setTheme(".Net");
        book.setAutor("Jeffrey Richter");
        book.setImageUrl("https://avatars.mds.yandex.net/get-mpic/1901647/img_id6771723293185391410.png/240x320");
        realm.commitTransaction();

        realm.beginTransaction();
        Book book2 = realm.createObject(Book.class);
        book2.setName("Android Application Development All-in-One For Dummies");
        book2.setTheme("Android");
        book2.setAutor("Barry A. Burd");
        book2.setImageUrl("https://m.media-amazon.com/images/I/51LraPVnVML._SR500,500_.jpg");
        realm.commitTransaction();

        realm.beginTransaction();
        Book book3 = realm.createObject(Book.class);
        book3.setName("C# 7.0 in a Nutshell: The Definitive Reference");
        book3.setTheme(".Net");
        book3.setAutor("Joseph Albahari, Ben Albahari");
        book3.setImageUrl("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1493983030i/35023933._UY630_SR1200,630_.jpg");
        realm.commitTransaction();

        realm.beginTransaction();
        Book book4 = realm.createObject(Book.class);
        book4.setName("Head First Android Development: A Brain-Friendly Guide");
        book4.setTheme("Android");
        book4.setAutor("Dawn Griffiths, DAVID GRIFFITHS");
        book4.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/91Z%2BjbisikL.jpg");
        realm.commitTransaction();

        realm.beginTransaction();
        Book book5 = realm.createObject(Book.class);
        book5.setName("Pro ASP.NET Core MVC 2");
        book5.setTheme(".Net");
        book5.setAutor(" Adam Freeman");
        book5.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51gDJxpEX-L.jpg");
        realm.commitTransaction();

        realm.close();
    }
}
