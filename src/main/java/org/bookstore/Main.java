package org.bookstore;

import org.bookstore.factory.BookFactory;
import org.bookstore.factory.SortStrategyFactory;
import org.bookstore.model.Book;
import org.bookstore.observer.AvailabilityObserver;
import org.bookstore.observer.PriceChangeObserver;
import org.bookstore.repository.BookRepository;
import org.bookstore.service.BookService;
import org.bookstore.strategy.SortStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);
        AvailabilityObserver availabilityObserver = new AvailabilityObserver();
        PriceChangeObserver priceChangeObserver = new PriceChangeObserver();
        bookService.addObserver(availabilityObserver);
        bookService.addObserver(priceChangeObserver);

        int choice = 0;
        while (choice != 6) {
            System.out.println("Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. Update a book");
            System.out.println("3. Delete a book");
            System.out.println("4. Show all books");
            System.out.println("5. Sort books by title");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(scanner, bookService);
                    break;
                case 2:
                    updateBook(scanner, bookService);
                    break;
                case 3:
                    deleteBook(scanner, bookService);
                    break;
                case 4:
                    showAllBooks(bookService);
                    break;
                case 5:
                    sortBooksByTitle(bookService);
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void addBook(Scanner scanner, BookService bookService) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Is available? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        Book book = BookFactory.createBook(title, author, price, available);
        bookService.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void updateBook(Scanner scanner, BookService bookService) {
        System.out.print("Enter the ID of the book to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Book book = bookService.getBookById(id);
        if (book == null) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Is available? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setAvailable(available);
        bookService.updateBook(book);
        System.out.println("Book updated successfully.");
    }

    private static void deleteBook(Scanner scanner, BookService bookService) {
        System.out.print("Enter the ID of the book to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        bookService.deleteBook(id);
        System.out.println("Book deleted successfully.");
    }

    private static void showAllBooks(BookService bookService) {
        List<Book> allBooks = bookService.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : allBooks) {
                System.out.println(book);
            }
        }
    }

    private static void sortBooksByTitle(BookService bookService) {
        List<Book> allBooks = bookService.getAllBooks();
        SortStrategy sortByTitleStrategy = SortStrategyFactory.createSortStrategy("title");
        sortByTitleStrategy.sort(allBooks);
        System.out.println("Books sorted by title:");
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }
}