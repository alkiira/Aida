package org.bookstore.service;


import org.bookstore.model.Book;
import org.bookstore.repository.BookRepository;
import org.bookstore.observer.BookObserver;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;
    private final List<BookObserver> observers;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.observers = new ArrayList<>();
    }

    public void addObserver(BookObserver observer) {
        observers.add(observer);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
        notifyObservers(book);
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book);
        notifyObservers(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteBook(id);
    }

    private void notifyObservers(Book book) {
        for (BookObserver observer : observers) {
            observer.update(book);
        }
    }

    public Book getBookById(int id) {
        return null;
    }

}