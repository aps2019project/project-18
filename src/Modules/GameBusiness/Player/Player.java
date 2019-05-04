package Modules.GameBusiness.Player;

import Modules.Account;
import Modules.GameBusiness.Game.Game;
import Modules.Hand;
import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Hero;
import View.View.Show;

import java.util.ArrayList;

public abstract class Player {
    protected int numberOfFlag = 0;
    protected int manaPoint;
    protected Account account;
    protected Hand hand;
    protected Game game;
    protected ArrayList<Item> items = new ArrayList<>();
    private int numberOfTurnPlayeHaveFlag;

    public Account getAccount() {
        return account;
    }

    public int getNumberOfFlag() {
        return numberOfFlag;
    }

    public int getNumberOfTurnPlayerHaveFlag() {
        return numberOfTurnPlayeHaveFlag;
    }

    public void playTurn(int turn) {
        manaPoint = (turn+1) + 2;
        if (manaPoint > 9)
            manaPoint = 9;
    }

    public Game getGame() {
        return game;
    }

    public void showCollectables(Show show) {
        show.showItems(items, null);
    }

    public Hero getHeroCard() {
        return account.getCollection().getMainDeck().getHero();
    }
    public Item getItem(String id){
        for (Item item : items) {
            if (item.getItemId() == id)
                return item;
        }
        return null;
    }
    public void insertCard(String id, int x, int y) {

    }
    public void showItem(String id){
        Show.get().showItem(getItem(id) , null);
    }
    public boolean checkItem(String id){
        for (Item item : items){
            if (item.getItemId() == id){
                return true;
            }
        }
        return false;
    }

    public void handleNextCard(){
        hand.handleNextCard();
    }
}
