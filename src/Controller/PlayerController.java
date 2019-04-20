package Controller;

import Modules.GameBusiness.Player.Human;
import View.Request.PlayerRequest;

public class PlayerController {
    private Human human;

    public void hadlePlayerCommands(Human haman){
        this.human = human;
        PlayerRequest request = new PlayerRequest();

        while (true){
            request.getNewCommand();
            if (!request.isValid()){
                continue;
            }
            switch (request.getType()){
                case HELP:

                case SELECT:

                case END_TURN:

                case GAME_INFO:

                case SHOW_HAND:

                case INSERT_CARD:

                case SHOW_CARD_INFO:

                case SHOW_NEXT_CARD:

                case ENTER_GRAVEYRAD:

                case SHOW_MY_MINIONS:

                case SHOW_COLLECTABLES:

                case SHOW_OPPONENT_MINIONS:
            }
        }
    }
}
