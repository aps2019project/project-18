package Server.View.View;

public class ShowSinglePlayer {
    public static void showMenu() {
        System.out.print("1. Story\n2. Custom\n3. Back\n4. Help\n");
    }

    public static void showHelpMenu() {
        System.out.println("1. Story : contains 3 level");
        System.out.println("2. Custom : you can select hero that want play against it");
        System.out.println(("3. Back : go to previous menu"));
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }
}
