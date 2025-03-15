package org.example.behavioral.iterator.code;

public class Book {
    private final int price;
    String bookName;

    Book(int price , String name){
        this.price = price;
        this.bookName = name;
    }

    public int getPrice(){
        return price;
    }

    public String getBookName(){
        return bookName;
    }
}
