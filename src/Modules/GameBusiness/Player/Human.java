package Modules.GameBusiness.Player;

import Controller.PlayerController;
import Modules.Account;
import Modules.PlayableThings.Item.Item;
import View.View.Show;

public class Human extends Player {
    private Account account;

    public Human(Account account) {
        this.account = account;
    }

    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        new PlayerController().handlePlayerCommands(this);
    }

    public void showHand() {
        hand.showHand();
    }

    public void showNextCard() {
        hand.showNextCard();
    }

    public void insertCard(String id, int x, int y) {
        //todo
    }

    public void showOptions(Show show) {
        showCollectables(show);
        hand.showInsertables(manaPoint);
        game.showMoveAbleCards();
        game.showAttackAbleCards();
    }

    public void showGraveyard(boolean all , String id){
        if (all)
            hand.getGraveyard().showGraveyard();
        else
            hand.getGraveyard().showCard(id);
    }
}
