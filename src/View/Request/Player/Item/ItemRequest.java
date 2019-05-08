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
        } else if (command.length() >= 3 && command.substring(0, 3).equals(USE)) {
            return ItemRequestType.USE;
        }else if(command.equals(HELP)){
            return ItemRequestType.HELP;
        }
        return null;
    }

    public boolean isValid(){
        if (getType() == null)
            return false;
        else if (getType() == ItemRequestType.USE)
            return checkUse();
        return true;
    }

    private boolean checkUse(){
        if (command.matches("Use\\((\\d+),(\\d+)\\)"))
            return true;
        System.out.println("Invalid syntax of use");
        return false;
    }

    public String returnCommand(){
        if (getType() == ItemRequestType.USE){
            Pattern patternCardInfo = Pattern.compile("Use\\((\\d+),(\\d+)\\)");
            Matcher matcher = patternCardInfo.matcher(command);
            matcher.find();
            return matcher.group(1) + " " + matcher.group(2);
        }
        return null;
    }

    public void show(){
        System.out.println("commands:");
        System.out.println("Exit");
        System.out.println("Help");
        System.out.println("Show info");
        System.out.println("Use (x, y)");
    }
}
