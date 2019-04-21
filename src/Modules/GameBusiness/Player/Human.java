package Modules.GameBusiness.Player;

import Modules.Account;
import View.View.Show;

public class Human extends Player {
    private Account account;

    @Override
    public void playTurn() {
        //todo
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
//        game.showMoveableCards();
//        game.attackableCards();
}
}
