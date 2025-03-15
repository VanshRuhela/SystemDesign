package org.example.behavioral.iterator.code;

import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args){
        List<Book> bookList = Arrays.asList(
                new Book(10, "FSI"),
                new Book(20, "Maths"),
                new Book(15, "Hindi")
        );

        Library lib = new Library(bookList);
        Iterator it = lib.createIterator();

        while(it.hasNext()){
            Book book = (Book) it.next();
            System.out.println(book.getBookName() + " price: " + book.getPrice());
        }
    }
}
