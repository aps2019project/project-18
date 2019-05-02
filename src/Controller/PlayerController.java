package Controller;

import Modules.GameBusiness.Player.Human;
import View.Request.Player.Graveyard.GraveyardRequest;
import View.Request.Player.Item.ItemRequest;
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
                    selectMenu(request.returnCommand());
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
                case SHOW_NEXT_CARD:
                    haman.showNextCard();
                    break;
                case INSERT_CARD:
                    String[] temp = request.returnCommand().split(" ");
                    haman.insertCard(temp[0] , Integer.parseInt(temp[1]) , Integer.parseInt(temp[2]));
                    break;
                case SHOW_CARD_INFO:
                    haman.getGame().showCardInfo(request.returnCommand());
                    break;
                case ENTER_GRAVEYARD:
                    graveYardMenu();
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

    public void selectMenu(String id2){
        int id = Integer.parseInt(id2);
        if (human.checkItem(id))
            selectMenuItem(id);
        else if (human.getGame().checkCard(id , human))
            selectMenuCard(id);
    }

    public void selectMenuCard(int id){

    }

    public void selectMenuItem(int id){
        ItemRequest request = new ItemRequest();
        boolean in = true;

        while (in){
            request.getCommand();
            if (!request.isValid())
                continue;
            switch (request.getType()){
                case EXIT:
                    return;
                case USE:
                    useItem(id ,request.returnCommand());
                    break;
                case SHOW_INFO:
                    human.showItem(id);
                    break;
            }
        }
    }
    private void useItem(int id , String place){
        String[] deminision = place.split(" ");
        human.getGame().useItem(human.getItem(id) , Integer.parseInt(deminision[0]) , Integer.parseInt(deminision[1]));
    }
}
