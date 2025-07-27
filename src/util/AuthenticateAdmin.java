package util;

import java.util.Scanner;

public class AuthenticateAdmin {
    public boolean authenticateAdmin(Scanner scanner) {
        final String adminUsername = "admin";
        final String adminPassword = "12345";

        System.out.print("Admin Username: ");
        String username = scanner.nextLine();

        System.out.print("Admin Password: " );
        String password = scanner.nextLine();

        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return true;
        }
        else{
            System.out.println("Username or password doesn't match");
            return false;
        }
    }
}
