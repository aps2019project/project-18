package View.View;

public class ShowAccount {
    public static void showMenu() {
        System.out.print("1. Collection\n2. Shop\n3. Battle\n4. Leaderboard\n5. Logout\n6. Exit\n7. Help\n");
    }

    public static void showHelp() {
        System.out.println("1. Collection : see your cart and item");
        System.out.println("2. Shop : go to shop for buy or sale");
        System.out.println("3. Battle : go to battle menu");
        System.out.println("4. Leaderboard : show user list sort by their win");
        System.out.println("5. Logout : logout from your account");
        System.out.println("6. Exit : close the game");
    }

    public static void showUserNameExist() {
        System.out.println("This user name has exist please use another user name");
        System.out.println("Note : user name is not case sensitive");
    }

    public static void showEnterPassword() {
        System.out.println("please insert password");
        showPasswordNote();
    }

    public static void showPasswordNote() {
        System.out.println("Note : password should have 8-16 character and ([a-z]and[A-Z]and[0-9] and special character)");
    }

    public static void showIncorrectUserNameOrPassword() {
        System.out.println("Incorrect UserName or password");
        System.out.println("1. try another userName and password\n2. Back : use this word to back to previous menu");
    }

    public static void showNotEnoughMoney(){
        System.out.println("Not enough money");
    }

    public static void showInvalidCommand() {
        System.out.println("Invalid command please use Help to show menu");
    }

    public static void showLeaderboard(int i, String userName, int winCount) {
        System.out.println(i + "-UserName : " + userName + " Wins : " + winCount);
    }
}
