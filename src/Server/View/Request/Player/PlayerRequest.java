package Server.View.Request.Player;

import Server.View.Request.MainRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PlayerRequest extends MainRequest {
    private static final String GAME_INFO = "Game info";
    private static final String SHOW_MY_MINIONS = "Show my minions";
    private static final String SHOW_OPPONENT_MINIONS = "Show opponent minions";
    private static final String SHOW_CARD_INFO = "Show card info";
    private static final String SHOW = "Show";
    private static final String SELECT = "Select";
    private static final String SHOW_HAND = "Show hand";
    private static final String INSERT_CARD = "Insert";
    private static final String END_TURN = "End turn";
    private static final String SHOW_ITEMS = "Show collectables";
    private static final String SHOW_NEXT_CARD = "Show Next Card";
    private static final String ENTER_GRAVEYARD = "Enter graveyard";
    private static final String HELP = "Help";
    private static final String CANCEL = "Cancel game";

    public PlayerRequestType getType() {
        if (command == null || command.equals("")) {
            return null;
        }
        if (command.equals(END_TURN)) {
            return PlayerRequestType.END_TURN;
        } else if (command.length() >= 4 && command.substring(0, 4).equals(SHOW)) {
            return showType();
        } else if (command.equals(GAME_INFO)) {
            return PlayerRequestType.GAME_INFO;
        } else if (command.length() >= 6 && command.substring(0, 6).equals(SELECT)) {
            return PlayerRequestType.SELECT;
        } else if (command.equals(ENTER_GRAVEYARD)) {
            return PlayerRequestType.ENTER_GRAVEYARD;
        } else if (command.equals(HELP)) {
            return PlayerRequestType.HELP;
        } else if (command.length() >= 6 && command.substring(0, 6).equals(INSERT_CARD)) {
            return PlayerRequestType.INSERT_CARD;
        }else if (command.equals(CANCEL))
            return PlayerRequestType.CANCEL_GAME;
        return null;
    }

    private PlayerRequestType showType() {
        if (command.length() >= 15 && command.substring(0, 15).equals(SHOW_CARD_INFO)) {
            return PlayerRequestType.SHOW_CARD_INFO;
        } else if (command.equals(SHOW_MY_MINIONS)) {
            return PlayerRequestType.SHOW_MY_MINIONS;
        } else if (command.equals(SHOW_OPPONENT_MINIONS)) {
            return PlayerRequestType.SHOW_OPPONENT_MINIONS;
        } else if (command.equals(SHOW_HAND)) {
            return PlayerRequestType.SHOW_HAND;
        } else if (command.equals(SHOW_ITEMS)) {
            return PlayerRequestType.SHOW_COLLECTABLES;
        } else if (command.equals(SHOW_NEXT_CARD)) {
            return PlayerRequestType.SHOW_NEXT_CARD;
        }
        return null;
    }

    public boolean isValid() {
        if (getType() == null)
            return false;
        switch (getType()) {
            case SHOW_CARD_INFO:
                return checkCardInfoCommand();
            case INSERT_CARD:
                return checkInsertCommand();
            case SELECT:
                return checkSelectCommand();
        }
        return true;
    }

    private boolean checkCardInfoCommand() {
        if (command.matches("Show card info .+_\\w+_\\d+"))
            return true;
        System.out.println("Invalid syntax of show card info");
        return false;
    }

    private boolean checkInsertCommand() {
        if (command.matches("Insert .+_\\w+_\\d+ in \\(\\d+,\\d+\\)"))
            return true;
        System.out.println("Invalid syntax of insert");
        return false;
    }

    private boolean checkSelectCommand(){
        if (command.matches("Select .+_\\w+_\\d+"))
            return true;
        System.out.println("Invalid syntax of select");
        return false;
    }

    public String returnCommand() {
        Pattern patternInsert = Pattern.compile("Insert (.+_\\w+_\\d+) in \\((\\d),(\\d)\\)");
        Pattern patternCardInfo = Pattern.compile("Show card info (\\w+_\\w+_\\d+)");
        Pattern patternSelect = Pattern.compile("Select (.+_\\w+_\\d+)");
        Matcher matcher;
        switch (getType()) {
            case INSERT_CARD:
                matcher = patternInsert.matcher(command);
                matcher.find();
                return matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3);
            case SHOW_CARD_INFO:
                matcher = patternCardInfo.matcher(command);
                matcher.find();
                return matcher.group(1);
            case SELECT:
                matcher = patternSelect.matcher(command);
                matcher.find();
                return matcher.group(1);
        }
        return null;
    }

    public void show(){
        System.out.println("commands:");
        System.out.println(GAME_INFO);
        System.out.println(SHOW_MY_MINIONS);
        System.out.println(SHOW_OPPONENT_MINIONS);
        System.out.println("Show card info id");
        System.out.println("Select id");
        System.out.println("Insert id");
        System.out.println(SHOW_HAND);
        System.out.println(END_TURN);
        System.out.println(SHOW_ITEMS);
        System.out.println(SHOW_NEXT_CARD);
        System.out.println(END_TURN);
        System.out.println(HELP);
        System.out.println(CANCEL);
    }
}
