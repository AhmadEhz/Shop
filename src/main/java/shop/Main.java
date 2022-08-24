package shop;
import shop.exception.NotFoundException;
import shop.util.Menu;

public class Main {
    public static void main(String[] args) {
        try {
            Menu.mainMenu();
        }
        catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}