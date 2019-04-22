package View.Request.Player.Graveyard;

import View.Request.MainRequest;

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
}
