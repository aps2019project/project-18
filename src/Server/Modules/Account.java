package Server.Modules;

import Server.Modules.GameBusiness.Battle.Battle;
import Server.Modules.PlayableThings.Item.Item;
import Server.Modules.PlayableThings.cards.Card;
import Server.View.View.ShowAccount;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class Account implements Comparator, Runnable{

    private static ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<GameData> matchHistory = new ArrayList<>();
    private String userName, passWord;
    private int winCount, money = 150000;
    private Collection collection = new Collection();
    private boolean online = true;
    private Scanner scanner;
    private Formatter formatter;
    private static ArrayList<String> messages = new ArrayList<>();

    @Override
    public void run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Collection")) {
                collection.menu();
            } else if (input.equalsIgnoreCase("Shop")) {
                shop();
            } else if (input.equalsIgnoreCase("Battle")) {
                if (this.getCollection().getMainDeck() != null && this.getCollection().getMainDeck().checkValidity() && this.getCollection().getMainDeck().checkValidity()) {
                    Battle.doOrder(this);
                } else {
                    System.out.println("Main deck is not valid");
                }
            } else if (input.equalsIgnoreCase("leaderboard")) {
                sendLeaderboard();
            } else if (input.equalsIgnoreCase("Log out")) {
                //ShowMain.showMenu();
                logout();

                return;
            } else if (input.equalsIgnoreCase("chat")) {
                chat();
            } else if (input.equalsIgnoreCase("Exit")) {
                //Main.exit();
                return;
            } else {
                ShowAccount.showInvalidCommand();
            }
        }
    }

    private void sendCardsToClient() {
        Random random = new Random();
        String s;
        for (Card card : Shop.getInstance().getHeroes()) {
            s = card.getName() + "/" + random.nextInt(10) + "/" + card.getPrice();
            System.out.println(s);
            formatter.format(s + "\n");
            formatter.flush();
        }
        formatter.format("end\n");
        formatter.flush();
        for (Card card : Shop.getInstance().getSpells()) {
            s = card.getName() + "/" + random.nextInt(10) + "/" + card.getPrice();
            formatter.format(s + "\n");
            formatter.flush();
        }
        formatter.format("end\n");
        formatter.flush();
        for (Card card : Shop.getInstance().getMinions()) {
            s = card.getName() + "/" + random.nextInt(10) + "/" + card.getPrice();
            formatter.format(s + "\n");
            formatter.flush();
        }
        formatter.format("end\n");
        formatter.flush();
        for (Item item : Shop.getInstance().getItems()) {
            s = item.getName() + "/" + random.nextInt(10) + "/" + item.getPrice();
            formatter.format(s + "\n");
            formatter.flush();
        }
        formatter.format("end\n");
        formatter.flush();
    }

    private void shop() {
        formatter.format(this.money + "\n");
        formatter.flush();
        sendCardsToClient();
    }

    private void sendChatsToClient() {
        for (String message : messages) {
            formatter.format(message + "\n");
            formatter.flush();
        }
    }

    private void chat() {
        while (true) {
            String newMessage = scanner.nextLine();
            if (newMessage.equals("end"))
                break;
            if (newMessage.equals("update"))
                sendChatsToClient();
            messages.add(this.getUserName() + " : " + newMessage);
        }
    }

    public static void createAccount(String userName, String password, InputStream inputStream,
                                     OutputStream outputStream) {
        Account account = new Account();
        account.userName = userName;
        account.passWord = password;
        Shop.getInstance().addSomeCardToCollectionForBeginning(account);
        accounts.add(account);
        account.setScanner(inputStream);
        account.setFormatter(outputStream);
        //account.doOrderInAccount();
    }

    public void setScanner(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public void setFormatter(OutputStream outputStream) {
        formatter = new Formatter(outputStream);
    }

    public static Account findAccount(String username) {
        for (Account account : accounts)
            if (account.getUserName().equals(username))
                return account;
        return null;
    }

    public void signIn(InputStream inputStream, OutputStream outputStream) {
        setScanner(inputStream);
        setFormatter(outputStream);
        online = true;
        //this.doOrderInAccount();
    }

    public boolean checkPassword(String input) {
        return  this.passWord.equals(input);
    }

    public static boolean checkExistUserName(String userName) {
        for (Account account : accounts) {
            if (account.userName.equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
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

    public String getUserName() {
        return userName;
    }

    public void buyCard(Card card) {
        if (card.getPrice() > money) {
            ShowAccount.showNotEnoughMoney();
            return;
        }
        money -= card.getPrice();
        Card copyCard = card.getCopyCard();
        setIdCard(copyCard);
        collection.addCard(copyCard);
    }

    private void setIdCard(Card card) {
        ArrayList<Integer> idNumber = new ArrayList<>();
        String id;
        for (Card _card : collection.getCards()) {
            id = _card.getId();
            if (_card.getName().equals(card.getName())) {
                idNumber.add(Integer.parseInt(id.split("_")[2]));
            }
        }
        int i = 1;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Integer integer : idNumber) {
                flag = true;
                if (i == integer) {
                    i++;
                } else {
                    card.setId(userName + "_" + card.getName() + "_" + i);
                    return;
                }
            }
        }
        card.setId(userName + "_" + card.getName() + "_" + i);
    }

    public void sellCard(String id) {
        for (Card card : collection.getCards()) {
            if (card.getId().equalsIgnoreCase(id)) {
                collection.removeCard(card, true);
                money += card.getPrice() / 10;
                return;
            }
        }
        System.out.println("Card not find");
    }

    public void sellItem(String id) {
        for (Item item : collection.getItems()) {
            if (item.getItemId().equalsIgnoreCase(id)) {
                collection.removeItem(item, true);
                money += item.getPrice() / 10;
                return;
            }
        }
        System.out.println("Item not find");
    }

    public void winReward(int reward) {
        money += reward;
    }

    public void buyItem(Item item) {
        if (item.getPrice() > money) {
            ShowAccount.showNotEnoughMoney();
            return;
        }
        money -= item.getPrice();
        Item _item = item.getItemCopy();
        setItemId(_item);
        collection.addItem(_item);
    }

    private void setItemId(Item item) {
        ArrayList<Integer> idNumber = new ArrayList<>();
        String id;
        for (Item _item : collection.getItems()) {
            id = _item.getItemId();
            if (_item.getName().equals(item.getName())) {
                idNumber.add(Integer.parseInt(id.split("_")[2]));
            }
        }
        int i = 1;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Integer integer : idNumber) {
                flag = true;
                if (i == integer) {
                    i++;
                } else {
                    item.setItemId(userName + "_" + item.getName() + "_" + i);
                    return;
                }
            }
        }
        item.setItemId(userName + "_" + item.getName() + "_" + i);
    }

    public void saveGameData(GameData gameData) {
        matchHistory.add(gameData);
    }

    public Collection getCollection() {
        return collection;
    }

    public void sendLeaderboard() {
        sortAccounts();
        int i = 1;
        for (Account account : accounts) {
            if (account.getOnline())
                formatter.format(i + " " +  account.userName + " " + account.winCount + "on\n");
            else
                formatter.format(i + " " +  account.userName + " " + account.winCount + "off\n");
            formatter.flush();
            i++;
        }
        formatter.format("end\n");
        formatter.flush();
    }

    public static void showOpponents(String username) {
        int i = 1;
        for (Account account : accounts) {
            if (!account.getUserName().equals(username)) {
                ShowAccount.showOpponent(i, account.getUserName());
                i++;
            }
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void logout() {
        online = false;
    }

    public boolean getOnline() {
        return online;
    }
}
