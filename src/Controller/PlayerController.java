package Controller;

import Modules.GameBusiness.Player.Human;
import View.Request.Player.Graveyard.GraveyardRequest;
import View.View.Show;
import View.Request.Player.PlayerRequest;

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
                    human.showOptions(Show.get());
                    break;
                case SELECT:
                    //todo
                    break;
                case END_TURN:
                    in = false;
                    break;
                case GAME_INFO:
                  //  human.getGame().showInfo();
                    break;
                case SHOW_HAND:
                    haman.showHand();
                case SHOW_NEXT_CARD:
                    haman.showNextCard();
                    break;
                case INSERT_CARD:
                    String[] temp = request.returnCommand().split(" ");
                    haman.insertCard(temp[0] , Integer.parseInt(temp[1]) , Integer.parseInt(temp[2]));
                    break;
                case SHOW_CARD_INFO:
                 //   haman.getGame().showCardInfo(request.returnCommand());
                    break;
                case ENTER_GRAVEYARD:
                    graveYardMenu();
                  //  break;
                case SHOW_MY_MINIONS:
                  //  human.getGame().showMyMinions();
                    break;
                case SHOW_COLLECTABLES:
                    haman.showCollectables(Show.get());
                    break;
                case SHOW_OPPONENT_MINIONS:
                   // haman.getGame().showOpponentMinion();
            }
        }
    }

    private void graveYardMenu(){
        GraveyardRequest request = new GraveyardRequest();
        boolean in = true;

        while (in){
            request.getCommand();
            if (!request.isValid())
                continue;
            switch (request.getType()){
                case EXIT:
                    return;
                case SHOW_CARD:
                    human.showGraveyard(false , request.returnCommand());
                    break;
                case SHOW_CARDS:
                    human.showGraveyard(true , "");
                    break;
            }
        }
    }
}
