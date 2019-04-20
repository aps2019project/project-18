package Modules.GameBusiness.Player;

import Modules.Account;

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
}
