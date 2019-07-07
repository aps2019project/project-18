package Server.View.View;

public class ShowMultiPlayer {
    public static void showMenu() {
        System.out.println("1. Select user [user name]");
        System.out.println("2. Back");
        System.out.println("3. Help");
    }

    public static void showHelpMenu() {
        System.out.println("1. Select user [user name] : select enemy user name");
        System.out.println("2. Back : go to previous menu");
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }

}
