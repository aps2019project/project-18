package Server.View.Request.Player.CardRequests;

import Server.View.Request.MainRequest;

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
        } else if (command.length() >= 7 && command.substring(0, 7).equals(MOVE)) {
            return CardRequestType.MOVE;
        } else if (command.equals(HELP)) {
            return CardRequestType.HELP;
        } else if (command.length() >= 12 && command.substring(0, 12).equals(COMBO)) {
            return CardRequestType.COMBO;
        } else if (command.length() >= 6 && command.substring(0, 6).equals(ATTACK)) {
            return CardRequestType.ATTACK;
        } else if (command.length() >= 17 && command.substring(0, 17).equals(SPECIAL_POWER)) {
            return CardRequestType.USE_SPECIAL_POWER;
        }
        return null;
    }

    public boolean isValid() {
        CardRequestType type = getType();
        if (type == null)
            return false;
        if (type == CardRequestType.MOVE)
            return checkMove();
        else if (type == CardRequestType.COMBO)
            return checkCombo();
        else if (type == CardRequestType.ATTACK)
            return checkAttack();
        else if (type == CardRequestType.USE_SPECIAL_POWER)
            return checkSpecialPower();
        return true;
    }

    private boolean checkMove() {
        if (command.matches("Move to \\(\\d+, \\d+\\)"))
            return true;
        System.out.println("Invalid syntax of Move to");
        return false;
    }

    private boolean checkSpecialPower() {
        if (command.matches("Use special power \\(\\d+; \\d+\\)"))
            return true;
        System.out.println("Invalid syntax of use special power");
        return false;
    }

    private boolean checkAttack() {
        if (command.matches("Attack \\w+_\\w+_\\d+"))
            return true;
        System.out.println("Invalid syntax of attack");
        return false;
    }

    private boolean checkCombo(){
        if (command.matches("Attack combo (\\w+_\\w+_\\d+)(( \\w+_\\w+_\\d+)+)"))
            return true;
        System.out.println("Invalid syntax of combo");
        return false;
    }

    public String returnCommand(){
        Pattern patternAttack = Pattern.compile("Attack (\\w+_\\w+_\\d+)");
        Pattern patternComboAttack = Pattern.compile("(\\w+_\\w+_\\d+)");
        Pattern patternMove = Pattern.compile("Move to \\((\\d+), (\\d+)\\)");
        Pattern patternSpecialPower = Pattern.compile("Use special power \\((\\d+), (\\d+)\\)");
        Matcher matcher;
        switch (getType()) {
            case MOVE:
                matcher = patternMove.matcher(command);
                matcher.find();
                return matcher.group(1) + " " + matcher.group(2);
            case COMBO:
                matcher = patternComboAttack.matcher(command);
                StringBuilder result = new StringBuilder();
                while (matcher.find())
                    result.append(matcher.group(1)).append(" ");
                return result.toString();
            case ATTACK:
                matcher = patternAttack.matcher(command);
                matcher.find();
                return matcher.group(1);
            case USE_SPECIAL_POWER:
                matcher = patternSpecialPower.matcher(command);
                matcher.find();
                return matcher.group(1) + " " + matcher.group(2);
        }
        return null;
    }

    public void show(){
        System.out.println("commands:");
        System.out.println("Exit");
        System.out.println("Help");
        System.out.println("Move to (x, y)");
        System.out.println("Attack combo enemy_id other_combo_minion other_combo_minion ....");
        System.out.println("Attack enemy_id");
        System.out.println("Use special power (x, y)");
    }
}
