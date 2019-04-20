package Controller;

import Modules.GameBusiness.Player.Human;
import View.View.Show;
import View.Request.PlayerRequest;

public class PlayerController {
    private Human human;

    public void handlePlayerCommands(Human haman){
        this.human = human;
        PlayerRequest request = new PlayerRequest();
        boolean in = true;

        while (in){
            request.getNewCommand();
            if (!request.isValid()){
                continue;
            }
            switch (request.getType()){
                case HELP:
                    // TODO
                    break;
                case SELECT:
                    //todo
                    break;
                case END_TURN:
                    in = false;
                    break;
                case GAME_INFO:
                    human.getGame().showInfo();
                    break;
                case SHOW_HAND:
                    haman.showHand();
                case SHOW_NEXT_CARD:
                    haman.showNextCard();
                    break;
                case INSERT_CARD:
                    //todo
                    break;
                case SHOW_CARD_INFO:
                    //todo
                    break;
                case ENTER_GRAVEYRAD:
                    //todo
                    break;
                case SHOW_MY_MINIONS:
                    human.getGame().showMyMinions();
                    break;
                case SHOW_COLLECTABLES:
                    haman.showCollectables(Show.get());
                    break;
                case SHOW_OPPONENT_MINIONS:
                    haman.getGame().showOpponentMinion();
            }
        }
    }
}
