package View.Request;

public class PlayerRequest extends MainRequest{
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

    public PlayerRequestType getType() {
        if (command == null || command.equals("")) {
            return null;
        }
        if (command.equals(END_TURN)) {
            return PlayerRequestType.END_TURN;
        } else if (command.substring(0, 3).equals(SHOW)) {
            return showType();
        } else if (command.equals(GAME_INFO)) {
            return PlayerRequestType.GAME_INFO;
        } else if (command.substring(0, 5).equals(SELECT)) {
            return PlayerRequestType.SELECT;
        } else if (command.equals(ENTER_GRAVEYARD)) {
            return PlayerRequestType.ENTER_GRAVEYRAD;
        } else if (command.equals(HELP)) {
            return PlayerRequestType.HELP;
        } else if (command.equals(INSERT_CARD)) {
            return PlayerRequestType.INSERT_CARD;
        }
        return null;
    }

    private PlayerRequestType showType(){
        if (command.equals(SHOW_CARD_INFO)) {
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
}
