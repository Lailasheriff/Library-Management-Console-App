package util;

import dao.BookDao;
import dao.UserDao;
import model.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class AdminMenuUtil {
    public void handleAdmin(Session session, Scanner scanner, UserDao userDao, BookDao bookDao) {
        InputUtil inputUtil = new InputUtil();

        while (true) {
            System.out.println("1 Add book");
            System.out.println("2 Delete book");
            System.out.println("3 Edit book");
            System.out.println("4 Register regular user");
            System.out.println("5 Back");
            System.out.println("choose: ");

            Admin admin = new Admin(bookDao, userDao);

            int choice = inputUtil.getInt(scanner);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter available copies: ");
                    int copies = inputUtil.getInt(scanner);

                    admin.addBook(title, author, genre, copies);
                }

                case 2 -> {
                    System.out.print("Enter book id to delete: ");
                    int id = inputUtil.getInt(scanner);
                    admin.deleteBook(id);
                }

                case 3 -> {
                    System.out.println("Enter book id to edit: ");
                    int id = inputUtil.getInt(scanner);

                    System.out.print("Enter new title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter new genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter new available copies: ");
                    int copies = inputUtil.getInt(scanner);

                    admin.editBook(id, title, author, genre, copies);
                }

                case 4 -> {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    admin.registerUser(name);
                }

                case 5 -> {
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
}
