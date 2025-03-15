package org.example.behavioral.iterator.code;

import java.util.List;

// concrete implementation of Aggregate
public class Library implements Aggegrate {
    private List<Book> bookList;

    public Library(List<Book> bookList){
        this.bookList = bookList;
    }

    @Override
    public Iterator createIterator() {
       return new BookIterator(bookList);
    }
}
