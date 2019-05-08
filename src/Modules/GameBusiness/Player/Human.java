package Modules.GameBusiness.Player;

import Controller.PlayerController;
import Modules.Account;
import Modules.GameBusiness.Game.Game;
import Modules.Hand;
import View.View.Show;

public class Human extends Player {


    public Human(Account account) {
        this.account = account;
        hand = new Hand(account.getCollection().getMainDeck());
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        PlayerController.get().handlePlayerCommands(this);
        aging();
    }

    public void showHand() {
        hand.showHand();
    }

    public void endTurn() {
        game.turn();
    }

    public void showNextCard() {
        hand.showNextCard();
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
