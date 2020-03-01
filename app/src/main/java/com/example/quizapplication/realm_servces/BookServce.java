package com.example.quizapplication.realm_servces;

import com.example.quizapplication.realm_models.Book;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class BookServce {


    public ArrayList<Book> getBooks(){
        Realm realm = Realm.getDefaultInstance();
        List<Book> booksRealm =  realm.where(Book.class).findAll();
        ArrayList<Book> books=new ArrayList<Book>();

        realm.beginTransaction();

        for (Book b: booksRealm  ) {

            Book book  = new Book(b.Theme, b.Name,b.Autor, b.ImageUrl);
            books.add(book);
        }

        realm.commitTransaction();

        return books;
    }

    public ArrayList<String> getBooksTitles(){
        Realm realm = Realm.getDefaultInstance();
        List<Book> booksRealm =  realm.where(Book.class).findAll();
        ArrayList<String> bookTitles=new ArrayList<String>();

        realm.beginTransaction();

        for (Book b: booksRealm  ) {

            Book book  = new Book(b.Theme, b.Name,b.Autor, b.ImageUrl);
            bookTitles.add(book.Name+"  by  "+book.Autor);
        }

        realm.commitTransaction();

        return  bookTitles;
    }

    public ArrayList<String> getBooksUrls(){
        Realm realm = Realm.getDefaultInstance();
        List<Book> booksRealm =  realm.where(Book.class).findAll();
        ArrayList<String> bookUrls=new ArrayList<String>();

        realm.beginTransaction();

        for (Book b: booksRealm  ) {

          //  Book book  = new Book(b.Theme, b.Name,b.Autor, b.ImageUrl);
            bookUrls.add(b.ImageUrl);
        }

        realm.commitTransaction();

        return  bookUrls;
    }

    public void  AddBook(Book book){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Book bookRealm =  realm.createObject(Book.class);

        bookRealm.setName(book.Name);
        bookRealm.setTheme(book.Theme);
        bookRealm.setAutor(book.Autor);
        bookRealm.setImageUrl(book.ImageUrl);

        realm.commitTransaction();
    }
}
