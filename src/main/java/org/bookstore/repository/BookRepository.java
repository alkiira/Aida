package org.bookstore.repository;

import org.bookstore.model.Book;
import org.bookstore.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                book.setAvailable(resultSet.getBoolean("available"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, price, available) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getPrice());
            statement.setBoolean(4, book.isAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?, author=?, price=?, available=? WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getPrice());
            statement.setBoolean(4, book.isAvailable());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}