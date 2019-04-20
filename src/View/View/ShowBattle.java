package View.View;

public class ShowBattle {
    public static void showMenu() {
        System.out.println("1. Single player\n2. Multi player\n3. Back\n4. Help");
    }

    public static void showHelpMenu() {
        System.out.println("1. Single player : play with AI");
        System.out.println("2. Multi player : play with other player");
        System.out.println("3. Back : go to previous menu");
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }
}
