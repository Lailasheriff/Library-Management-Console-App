import org.hibernate.Session;
import util.*;
import dao.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            if (System.console() == null && System.in.available() == 0) {
                System.out.println("No input available. Skipping interactive mode.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Could not determine input availability. Exiting.");
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Scanner scanner = new Scanner(System.in);

            BookDao bookDao = new BookDao(session);
            UserDao userDao = new UserDao(session);

            InputUtil inputUtil = new InputUtil();
            AdminMenuUtil adminMenuUtil = new AdminMenuUtil();
            UserMenuUtil userMenuUtil = new UserMenuUtil();
            AuthenticateAdmin authenticateAdmin = new AuthenticateAdmin();

            while (true) {
                System.out.println("\n\t\tLibrary Management System\n");
                System.out.println("1) Admin");
                System.out.println("2) Regular User");
                System.out.println("3) Exit\n");
                System.out.print("Choose: ");

                int choice = inputUtil.getInt(scanner);

                switch (choice) {
                    case 1 -> {
                        if (authenticateAdmin.authenticateAdmin(scanner))
                            adminMenuUtil.handleAdmin(session, scanner, userDao, bookDao);
                    }
                    case 2 -> userMenuUtil.handleRegularUser(session, scanner, userDao, bookDao);
                    case 3 -> {
                        System.out.println("Exit");
                        return;
                    }
                    default -> System.out.println("Invalid");
                }
            }
        }
    }

}