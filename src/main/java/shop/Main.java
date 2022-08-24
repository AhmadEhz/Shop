package shop;
import shop.exception.NotFoundException;
import shop.util.Menu;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
     /*   try {
            Menu.mainMenu();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }*/
        Menu.mainMenu();
    }
}