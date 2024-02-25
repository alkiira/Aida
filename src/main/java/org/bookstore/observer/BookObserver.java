package org.bookstore.observer;

import org.bookstore.model.Book;

public interface BookObserver {
    void update(Book book);
}
