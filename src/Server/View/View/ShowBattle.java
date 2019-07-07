package Server.View.View;

public class ShowBattle {
    public static void showMenu() {
        System.out.println("1. Single player\n2. Multi player\n3. Back\n4. Help");
    }

    public static void showHelpMenu() {
        System.out.println("1. Single player : play with AI");
        System.out.println("2. Multi player : play with other player");
        System.out.println("3. Back : go to previous menu");
    }

    public static void showGameModeMenu() {
        System.out.println("1. Kill enemy hero\n2. Capture flag for 6 turn\n3. Capture more than half flags [number of flags]\n4. Help");
    }

    public static void showHelpGameModeMenu() {
        System.out.println("1. This mode goal is killing enemy hero");
        System.out.println("2. This mode goal is capturing flag for 6 turn");
        System.out.println("3. This mode goal is capturing more than half flags and you can set total number of flags");
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }
}
