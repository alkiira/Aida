package org.bookstore.strategy;

import org.bookstore.model.Book;

import java.util.List;

public interface SortStrategy {
    void sort(List<Book> books);
}

