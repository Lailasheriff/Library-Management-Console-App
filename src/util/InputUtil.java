package util;

import java.util.Scanner;

public class InputUtil {
    public static int getInt(Scanner scanner) {
        int num;
        while (true) {
            if(scanner.hasNextInt()) {
                num = scanner.nextInt();
                scanner.nextLine();
                break;
            }
            else {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        return num;
    }
}
