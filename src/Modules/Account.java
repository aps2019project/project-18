package Modules;

import java.util.ArrayList;
import java.util.Comparator;

import static Modules.Main.*;

public class Account implements Comparator {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private String userName, passWord;
    private int winCount, money;

    private void doOrderInAccount() {
        String input;
        while (true){
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

    private static boolean checkPasswordNote(String passWord) {
        boolean a_z = false, A_Z = false, _0_9 = false, specialChar = false;
        if (passWord.length() < 8 || passWord.length() > 16) {
            return false;
        } else {
            for (int i = 0; i < passWord.length(); i++) {
                if (passWord.charAt(i) >= 'a' && passWord.charAt(i) <= 'z') {
                    a_z = true;
                } else if (passWord.charAt(i) >= 'A' && passWord.charAt(i) <= 'Z') {
                    A_Z = true;
                } else if (passWord.charAt(i) >= '0' && passWord.charAt(i) <= '9') {
                    _0_9 = true;
                } else {
                    specialChar = true;
                }
            }
        }
        if (a_z && A_Z && _0_9 && specialChar) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        Account firstAccount = (Account) o1;
        Account secondAccount = (Account) o2;
        if (firstAccount.winCount == secondAccount.winCount){
            return firstAccount.userName.compareTo(secondAccount.userName);
        }
        if(firstAccount.winCount > secondAccount.winCount){
            return 1;
        } else {
            return -1;
        }
    }

    private void sordAccounts(){
        accounts.sort(this::compare);
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
        //todo ...
    }

}
