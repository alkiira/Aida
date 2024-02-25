package org.bookstore.model;
import java.util.UUID;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private boolean available;

    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.price = 0.0;
        this.available = false;
    }
    public Book(String title, String author) {
        this.id = Integer.parseInt(UUID.randomUUID().toString());
        this.title = title;
        this.author = author;
        this.price = 0.0;
        this.available = false;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}



