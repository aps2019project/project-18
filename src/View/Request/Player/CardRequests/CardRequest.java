package View.Request.Player.CardRequests;

import View.Request.MainRequest;
import View.Request.Player.Graveyard.GraveyardRequestType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardRequest extends MainRequest {
    private static final String EXIT = "Exit";
    private static final String HELP = "Help";
    private static final String MOVE = "Move to";
    private static final String COMBO = "Attack combo";
    private static final String ATTACK = "Attack";
    private static final String SPECIAL_POWER = "Use special power";

    public CardRequestType getType() {
        if (command == null || command.equals("")) {
            return null;
        }
        if (command.equals(EXIT)) {
            return CardRequestType.EXIT;
        } else if (command.substring(0, 6).equals(MOVE)) {
            return CardRequestType.MOVE;
        } else if (command.equals(HELP)) {
            return CardRequestType.HELP;
        }else if (command.substring(0, 11).equals(COMBO)) {
            return CardRequestType.COMBO;
        } else if (command.substring(0 , 5).equals(ATTACK)) {
            return CardRequestType.HELP;
        }else if (command.substring(0, 16).equals(SPECIAL_POWER)) {
            return CardRequestType.USE_SPECIAL_POWER;
        }
        return null;
    }

    public boolean isValid(){
        CardRequestType type = getType();
        if (type == CardRequestType.MOVE)
            return checkMove();
        else if (type == CardRequestType.COMBO)
            return checkCombo();
        else if (type == CardRequestType.ATTACK)
            return checkAttack();
        else if (type == CardRequestType.USE_SPECIAL_POWER)
            return checkSpecialPower();
        return false;
    }

    private boolean checkMove(){
        if (command.matches("Move to \\(\\d+, \\d+\\)"))
            return true;
        return false;
    }

    private boolean checkSpecialPower(){
        if (command.matches("Use special power \\(\\d+; \\d+\\)"))
            return true;
        return false;
    }

    private boolean checkAttack(){
        if (command.matches("Attack \\d+"))
            return true;
        return false;
    }

    private boolean checkCombo(){
        if (command.matches("Attack combo \\d+ (\\d+)+"))
            return true;
        return false;
    }

    public String returnCommand(){
        // TODO: 5/2/2019
    }
}
