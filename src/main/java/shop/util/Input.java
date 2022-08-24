package shop.util;

import java.util.Scanner;

public class Input {
    static Scanner scanner = new Scanner(System.in);

    public static int intScanner() {
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid entry!");
        }
        return input;
    }

    public static String stringScanner() {
        return scanner.nextLine();
    }

    public static String[] getUserPassword() {
        String[] userPassword = scanner.nextLine().split(" ");
        if (userPassword[0].equals("0"))
            return null;
        if (userPassword.length != 2)
            throw new IllegalArgumentException("Invalid entry!");
        else return userPassword;
    }
}
