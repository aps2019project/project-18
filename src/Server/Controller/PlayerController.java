package Server.Controller;

import Server.Modules.GameBusiness.Player.Human;
import Server.Modules.PlayableThings.cards.Force;
import Server.Modules.PlayableThings.cards.Hero;
import Server.View.Request.Player.CardRequests.CardRequest;
import Server.View.Request.Player.Graveyard.GraveyardRequest;
import Server.View.Request.Player.Item.ItemRequest;
import Server.View.Request.Player.PlayerRequest;
import Server.View.View.Show;

public class PlayerController {
    private Human human;
    private final static PlayerController playerController = new PlayerController();

    private PlayerController() {

    }

    public static PlayerController get() {
        return playerController;
    }

    public void handlePlayerCommands(Human human) {
        this.human = human;
        PlayerRequest request = new PlayerRequest();
        boolean in = true;

        while (in) {
            human.getGame().checkEnd();
            if (human.getGame().isEnd())
                return;
            request.getNewCommand();
            if (!request.isValid() && request.getType() == null) {
                System.out.println("Invalid command");
                continue;
            } else if (!request.isValid()) {
                continue;
            }
            switch (request.getType()) {
                case HELP:
                    request.show();
                    human.showOptions(Show.get());
                    break;
                case SELECT:
                    selectMenu(request.returnCommand());
                    break;
                case END_TURN:
                    in = false;
                    human.handleNextCard();
                    break;
                case GAME_INFO:
                    human.getGame().showInfo();
                    break;
                case SHOW_HAND:
                    human.showHand();
                    break;
                case SHOW_NEXT_CARD:
                    human.showNextCard();
                    break;
                case INSERT_CARD:
                    String[] temp = request.returnCommand().split(" ");
                    human.insertCard(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                    break;
                case SHOW_CARD_INFO:
                    human.getGame().showCardInfo(request.returnCommand());
                    break;
                case ENTER_GRAVEYARD:
                    graveYardMenu();
                    break;
                case SHOW_MY_MINIONS:
                    human.getGame().showMyMinions();
                    break;
                case SHOW_COLLECTABLES:
                    human.showCollectables(Show.get());
                    break;
                case SHOW_OPPONENT_MINIONS:
                    human.getGame().showOpponentMinion();
                    break;
                case CANCEL_GAME:
                    human.getGame().cancelGame();
                    in = false;
            }
        }
    }

    private void graveYardMenu() {
        GraveyardRequest request = new GraveyardRequest();
        boolean in = true;

        while (in) {
            request.getCommand();
            if (!request.isValid() && request.getType() == null) {
                System.out.println("Invalid command");
                continue;
            } else if (!request.isValid())
                continue;
            switch (request.getType()) {
                case EXIT:
                    return;
                case SHOW_CARD:
                    human.showGraveyard(false, request.returnCommand());
                    break;
                case SHOW_CARDS:
                    human.showGraveyard(true, "");
                    break;
                case HELP:
                    request.show();
            }
        }
    }

    public void selectMenu(String id) {
        if (human.checkItem(id))
            selectMenuItem(id);
        else if (human.getGame().getForce(id) != null && human.checkCard(id))
            selectMenuCard(id);
        else
            System.out.println("There is not such a item or card");
    }

    public void selectMenuCard(String id) {
        CardRequest request = new CardRequest();
        Force force = human.getGame().getForce(id);
        if (force == null) {
            System.out.println("The card is not on ground");
            return;
        }
        boolean in = true;

        while (in) {
            human.getGame().checkEnd();
            if (human.getGame().isEnd())
                return;
            request.getNewCommand();
            if (!request.isValid() && request.getType() == null) {
                System.out.println("Invalid command");
                continue;
            } else if (!request.isValid()) {
                continue;
            }
            switch (request.getType()) {
                case EXIT:
                    return;
                case ATTACK:
                    human.getGame().attack(force, request.returnCommand());
                    break;
                case COMBO:
                    human.getGame().comboAttack(force, request.returnCommand());
                    break;
                case HELP:
                    cardHelp(id);
                    break;
                case MOVE:
                    human.move(force, request.returnCommand());
                    break;
                case USE_SPECIAL_POWER:
                    useSpecialPower(id , request.returnCommand());
                    break;
            }
        }
    }

    private void useSpecialPower(String id , String command) {
        String[] spillitedCommand = command.split(" ");
        if (human.getGame().getForce(id) instanceof Hero) {
            human.useSpecialPower(Integer.parseInt(spillitedCommand[0]) , Integer.parseInt(spillitedCommand[1]));
        }
        else
            System.out.println("this card is not hero and dont have special power");
    }

    private void cardHelp(String id) {
        new CardRequest().show();
        System.out.println("Others:");
        Force force = human.getGame().getForce(id);
        if (force.canMove())
            human.getGame().showMovablePlaces(id);
        if (force.canAttack())
            human.getGame().showAttackAbleCards();
        if (force instanceof Hero) {
            Hero hero = (Hero) force;
            System.out.println("Can use spell:" + hero.canUseSpell());
        }
    }

    public void selectMenuItem(String id) {
        ItemRequest request = new ItemRequest();
        boolean in = true;

        while (in) {
            request.getCommand();
            if (!request.isValid() && request.getType() == null) {
                System.out.println("Invalid command");
                continue;
            } else if (!request.isValid())
                continue;
            switch (request.getType()) {
                case EXIT:
                    return;
                case USE:
                    useItem(id, request.returnCommand());
                    break;
                case SHOW_INFO:
                    human.showItem(id);
                    break;
                case HELP:
                    request.show();
            }
        }
    }

    private void useItem(String id, String place) {
        String[] dimension = place.split(" ");
        human.getGame().useItem(human.getItem(id), Integer.parseInt(dimension[0]), Integer.parseInt(dimension[1]));
    }
}
