package org.bookstore.factory;

import org.bookstore.model.Book;

public class BookFactory {
    public static Book createBook(String title, String author, double price, boolean available) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setAvailable(available);
        return book;
    }
}