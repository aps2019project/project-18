package Modules.GameBusiness.Player;

import Modules.Account;
import Modules.PlayableThings.cards.Card;
import View.View.ShowPlayer;

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
}
