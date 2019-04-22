package Modules.GameBusiness.Player;

import Controller.PlayerController;
import Modules.Account;
import View.View.Show;

public class Human extends Player {
    private Account account;

    @Override
    public void playTurn() {
        new PlayerController().handlePlayerCommands(this);
        //handleMana
    }

    public void showHand(){
        hand.showHand();
    }

    public void showNextCard(){
        hand.showNextCard();
    }

    public void insertCard(String id , int x , int y){
        //todo
    }

    public void showOptions(Show show){
        showCollectables(show);
//        hand.showInsertables(manaPoint);
            game.showMoveAbleCards();
//        game.attackableCards();
}
}
