package Modules;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import View.ShowAccount;
import View.ShowMain;

import java.util.ArrayList;
import java.util.Comparator;

import static Modules.Main.*;

public class Account implements Comparator {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<GameData> matchHistory = new ArrayList<>();
    private String userName, passWord;
    private int winCount, money;
    private Collection collection;

    private void doOrderInAccount() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Collection")) {
                collection.menu();
            } else if (input.equalsIgnoreCase("Shop")) {
                Shop.getInstance().menu(this);
            } else if (input.equalsIgnoreCase("Battle")) {
                //ToDo go to battle
            } else if (input.equalsIgnoreCase("show leaderboard")) {
                showLeaderboard();
            } else if (input.equalsIgnoreCase("Logout")) {
                ShowMain.showMenu();
                return;
            } else if (input.equalsIgnoreCase("Help")) {
                ShowAccount.showHelp();
            } else {
                ShowAccount.showInvalidCommand();
            }
        }
    }

    public static void createAccount(String userName) {
        if (checkExistUserName(userName) == false) {
            Account account = new Account();
            account.userName = userName;
            ShowAccount.showEnterPassword();
            account.passWord = createPassword();
            accounts.add(account);
            ShowAccount.showMenu();
            account.doOrderInAccount();
        } else {
            ShowAccount.showUserNameExist();
            createAccount(scanner.nextLine());
        }
    }

    public static void singIn(String userName, String passWord) {
        for (Account account : accounts) {
            if (account.userName.equalsIgnoreCase(userName)) {
                if (account.passWord.equals(passWord)) {
                    ShowAccount.showMenu();
                    account.doOrderInAccount();
                    break;
                }
            }
        }
        ShowAccount.showIncorrectUserNameOrPassword();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Back")) {
            ShowMain.showMenu();
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
            ShowAccount.showPasswordNote();
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
        if (firstAccount.winCount == secondAccount.winCount) {
            return firstAccount.userName.compareTo(secondAccount.userName);
        }
        if (firstAccount.winCount > secondAccount.winCount) {
            return 1;
        } else {
            return -1;
        }
    }

    private void sortAccounts() {
        accounts.sort(this::compare);
    }

    private static Account findAccount(String userName) {
        for (Account account : accounts) {
            if (account.userName.equalsIgnoreCase(userName)) {
                return account;
            }
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void buyCard(Card card) {
        collection.addCard(card);
    }

    public void sellCard(String id) {
        for (Card card : collection.getCards()) {
            if (card.getId().equalsIgnoreCase(id)) {
                collection.removeCard(card);
                money += card.getPrice();
                return;
            }
        }
    }

    public void winReward(int reward) {
        money += reward;
    }

    public void buyItem(Item item) {
        collection.addItem(item);
    }

    public void saveGameData(GameData gameData) {
        matchHistory.add(gameData);
    }

    public Collection getCollection() {
        return collection;
    }

    public void beginGame() {
        // TODO:
    }

    private void showLeaderboard() {
        sortAccounts();
        int i = 1;
        for (Account account : accounts) {
            ShowAccount.showLeaderboard(i, account.userName, account.winCount);
            i++;
        }
    }

}
