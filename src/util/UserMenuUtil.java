package util;

import dao.BookDao;
import dao.UserDao;
import model.Book;
import model.RegularUser;
import service.SearchService;
import model.User;
import org.hibernate.Session;
import java.util.Scanner;

public class UserMenuUtil {
    public void handleRegularUser(Session session, Scanner scanner, UserDao userDao, BookDao bookDao) {
        InputUtil inputUtil = new InputUtil();

        SearchService<Book> searchServiceBook = new SearchService<>(Book.class, session);
        SearchService<User> searchServiceUser = new SearchService<>(User.class, session);

        System.out.print("Enter your user id: ");
        int userId = inputUtil.getInt(scanner);
        User user = searchServiceUser.searchById(userId);

        if (!(user instanceof RegularUser)) {
            System.out.println("user not found or not a regular user.");
            return;
        }

        RegularUser regularUser = (RegularUser) user;
        regularUser.setSession(session);

        while (true) {
            System.out.println("\n\t\tUser Menu\n");
            System.out.println("1) View all books");
            System.out.println("2) Borrow book");
            System.out.println("3) Return book");
            System.out.println("4) View my borrowed books");
            System.out.println("5) View books for specific author");
            System.out.println("6) View books for specific genre");
            System.out.println("7) Back");
            System.out.print("\nChoose: ");

            int choice = inputUtil.getInt(scanner);

            switch (choice) {
                case 1 -> regularUser.viewBooks();
                case 2 -> {
                    System.out.print("Enter book id to borrow: ");
                    int bookId = inputUtil.getInt(scanner);
                    Book book = searchServiceBook.searchById(bookId);

                    if (book != null) {
                        regularUser.borrowBook(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter book id to return: ");
                    int bookId = inputUtil.getInt(scanner);
                    Book book = searchServiceBook.searchById(bookId);

                    if (book != null) {
                        regularUser.returnBook(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                }

                case 4 -> {
                    System.out.println("Borrowed books: ");

                    if (regularUser.getBorrowedBooks().isEmpty()) {
                        System.out.println("No books borrowed.");
                    }

                    for (Book b : regularUser.getBorrowedBooks()) {
                        System.out.println("ID: " + b.getId() + " " + b.getTitle());
                    }
                }

                case 5 -> {
                    System.out.print("Wanted author: ");
                    String author = scanner.nextLine();
                    if (searchServiceBook.searchByName(author, "author").isEmpty()) {
                        System.out.println("No books for specific author.");
                    } else {
                        System.out.println("Books for author " + author + ": \n");

                        for (Book b : searchServiceBook.searchByName(author, "author")) {
                            System.out.println("Author:  " + b.getAuthor() + " ID: " + b.getId() + " Title: " + b.getTitle() + " Genre:  " + b.getGenre());
                        }
                    }
                }

                case 6 -> {
                    System.out.print("Wanted genre: ");
                    String genre = scanner.nextLine();
                    if (searchServiceBook.searchByName(genre, "genre").isEmpty()) {
                        System.out.println("No books for specific genre.");
                    } else {
                        System.out.println("Books for genre " + genre + ": \n");
                        for (Book b : searchServiceBook.searchByName(genre, "genre")) {
                            System.out.println("Genre: " + b.getGenre() + " ID: " + b.getId() + " Title: " + b.getTitle() + " Author:  " + b.getAuthor());
                        }
                    }
                }

                case 7 -> {
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
}
