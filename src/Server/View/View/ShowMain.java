package Server.View.View;

public class ShowMain {
    public static void showHelp() {
        System.out.println("1. sign in : login to your account");
        System.out.println("2. sign up : create account");
        System.out.println("3. Exit : you can close the game");
    }

    public static void showMenu() {
        System.out.println("1. sign in\n2. sign up\n3. Exit");
    }

    public static void showInvalidCommand() {
        System.out.println("Invalid command, please use 'help' to show menu");
    }

    public static void showTextForInsertUserName(){
        System.out.println("please insert user name");
    }

    public static void showTextForSignIn(){
        System.out.println("please insert user name and password");
    }
}
