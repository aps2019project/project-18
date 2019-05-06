package View.Request.Player.Graveyard;

import View.Request.MainRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraveyardRequest extends MainRequest {
    private static final String EXIT = "Exit";
    private static final String SHOW_CARD = "Show info";
    private static final String SHOW_CARDS = "Show cards";

    public GraveyardRequestType getType() {
        if (command == null || command.equals("")) {
            return null;
        }
        if (command.equals(EXIT)) {
            return GraveyardRequestType.EXIT;
        } else if (command.substring(0, 8).equals(SHOW_CARD)) {
            return GraveyardRequestType.SHOW_CARD;
        } else if (command.equals(SHOW_CARDS)) {
            return GraveyardRequestType.SHOW_CARDS;
        }
        return null;
    }

    public boolean isValid(){
        if (getType() == GraveyardRequestType.SHOW_CARD)
            return checkShowCard();
        else if (getType() != null)
            return true;
        return false;
    }

    private boolean checkShowCard(){
        if (command.matches("Show info (\\w+_\\w+_\\d+)"))
            return true;
        System.out.println("Invalid syntax of Show info");
        return false;
    }

    public String returnCommand(){
        if (getType() == GraveyardRequestType.SHOW_CARD){
            Pattern patternCardInfo = Pattern.compile("Show card (\\w+_\\w+_\\d+)");
            Matcher matcher = patternCardInfo.matcher(command);
            matcher.find();
            return matcher.group(1);
        }
        return null;
    }
}
