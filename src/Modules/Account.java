package Modules;

import java.util.ArrayList;

import static Modules.Main.*;

public class Account {
    private String userName, passWord;
    private static ArrayList<Account> accounts = new ArrayList<>();

    private void doOrderInAccount() {
        String input;
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("Collection")) {
            //todo go to collection
        } else if (input.equalsIgnoreCase("Shop")) {
            //todo go to shop
        } else if (input.equalsIgnoreCase("Battle")) {
            //ToDo go to battle
        } else if (input.equalsIgnoreCase("show leaderboard")) {
            showshowLeaderboard();
        } else if (input.equalsIgnoreCase("Logout")) {
            Main.showMenu();
            return;
        } else if (input.equalsIgnoreCase("Help")) {
            showHelp();
        } else {
            showInvalidCommand();
        }
    }

    public static void createAccount(String userName) {
        if (checkExistUserName(userName) == false) {
            Account account = new Account();
            account.userName = userName;
            showEnterPassword();
            account.passWord = createPassword();
            accounts.add(account);
            showMenu();
            account.doOrderInAccount();
        } else {
            showUserNameExist();
            createAccount(scanner.nextLine());
        }
    }

    public static void singIn(String userName, String passWord) {
        for (Account account : accounts) {
            if (account.userName.equalsIgnoreCase(userName)) {
                if (account.passWord.equals(passWord)) {
                    showMenu();
                    account.doOrderInAccount();
                    break;
                }
            }
        }
        showIncorrectUserNameOrPassword();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Back")) {
            Main.showMenu();
            return;
        } else {
            singIn(input, scanner.nextLine());
        }
    }

    private static boolean checkExistUserName(String userName) {
        for (Account account : accounts) {
            if (account.userName.equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    private static String createPassword() {
        String password = scanner.nextLine();
        if (checkPasswordNote(password)) {
            return password;
        } else {
            showPasswordNote();
            createPassword();
        }
        return "";
    }

    private static void showMenu() {
        System.out.print("1. Collection\n2. Shop\n3. Battle\n4. Leaderboard\n5. Logout\n6. Help\n");
    }

    private static void showHelp() {
        System.out.println("1. Collection : see your cart and item");
        System.out.println("2. Shop : go to shop for buy or sale");
        System.out.println("3. Battle : go to battle menu");
        System.out.println("4. Leaderboard : show user list sort by their win");
        System.out.println("5. Logout : logout from your account");
    }

    private static void showUserNameExist() {
        System.out.println("This user name has exist please use another user name");
        System.out.println("Note : user name is not case sensitive");
    }

    private static void showEnterPassword() {
        System.out.println("please insert password");
        showPasswordNote();
    }

    private static void showPasswordNote() {
        System.out.println("Note : password should have 8-16 character and ([a-z]and[A-Z]and[0-9] and special character)");
    }

    private static void showIncorrectUserNameOrPassword() {
        System.out.println("Incorrect UserName or password");
        System.out.println("1. try another userName or password\n2. Back : use this word to back to previous menu");
    }

    private void showInvalidCommand() {
        System.out.println("Invalid command please use Help to show menu");
    }

    private void showshowLeaderboard() {
        //todo show leaderboard
    }
}
