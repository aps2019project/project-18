package Controller;

import Modules.GameBusiness.Player.Human;
import View.Request.PlayerRequest;
import View.View.ShowPlayer;

public class PlayerController {
    private Human human;

    public void handlePlayerCommands(Human haman){
        this.human = human;
        PlayerRequest request = new PlayerRequest();
        ShowPlayer showPlayer = new ShowPlayer();
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
                    break;
                case INSERT_CARD:
                    //todo
                case SHOW_CARD_INFO:
                    haman.showCardInfo();
                    break;
                case SHOW_NEXT_CARD:
                    haman.showNextCard();
                    break;
                case ENTER_GRAVEYRAD:
                    //todo
                    break;
                case SHOW_MY_MINIONS:
                    human.getGame().showMyMinions();
                    break;
                case SHOW_COLLECTABLES:
                    haman.showCollectables();
                    break;
                case SHOW_OPPONENT_MINIONS:
                    haman.getGame().showOpponentMinion();
            }
        }
    }
}
