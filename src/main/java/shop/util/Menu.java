package shop.util;


public class Menu {
    public static void mainMenu(){
    checkConnection();
    }
    private static void checkConnection() {
        try {
            DbConfig.getConnection();
        }
        catch (ExceptionInInitializerError e) {
            System.err.print("Can't connect to database.\nCheck your setting and run the program again.");
            System.exit(1);
        }
    }
}
