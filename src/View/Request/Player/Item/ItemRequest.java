package View.Request.Player.Item;

import View.Request.MainRequest;
import View.Request.Player.Graveyard.GraveyardRequestType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemRequest extends MainRequest {
    private static final String EXIT = "Exit";
    private static final String SHOW_INFO = "Show info";
    private static final String USE = "Use";
    private static final String HELP = "Help";

    public ItemRequestType getType() {
        if (command == null || command.equals("")) {
            return null;
        }
        if (command.equals(EXIT)) {
            return ItemRequestType.EXIT;
        } else if (command.equals(SHOW_INFO)) {
            return ItemRequestType.SHOW_INFO;
        } else if (command.substring(0, 2).equals(USE)) {
            return ItemRequestType.USE;
        }
        return null;
    }

    public boolean isValid(){
        if (getType() == ItemRequestType.USE)
            return checkUse();
        else if (getType() != null)
            return true;
        return false;
    }

    private boolean checkUse(){
        if (command.matches("Use\\[(\\d+),(\\d+)\\]"))
            return true;
        return false;
    }

    public String returnCommand(){
        if (getType() == ItemRequestType.USE){
            Pattern patternCardInfo = Pattern.compile("Use\\[(\\d+),(\\d+)\\]");
            Matcher matcher = patternCardInfo.matcher(command);
            matcher.find();
            return matcher.group(1) + " " + matcher.group(2);
        }
        return null;
    }
}
