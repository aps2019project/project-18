package Modules.GameBusiness.Player;

import Modules.Account;
import Modules.GameBusiness.Game.Game;
import Modules.Hand;
import Modules.PlayableThings.Item.Flag;
import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Force;
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
    private int numberOfTurnPlayeHaveFlag = 0;

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
        manaPoint = (turn+1)/2 + 2;
        if (manaPoint > 9)
            manaPoint = 9;
    }

    public void takeFlag(){
        numberOfFlag++;
    }

    public void looseFlag(){
        numberOfFlag--;
    }

    public void aging(){
        if (numberOfFlag == 1){
            numberOfTurnPlayeHaveFlag++;
        }else {
            numberOfTurnPlayeHaveFlag = 0;
        }
        game.buffAging();
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
        //card.insert
        Card card = hand.insertCard(id , manaPoint);
        if (card != null) {
            manaPoint -= card.getManaPoint();
            if (game.insertCard(card, x, y))
                hand.deleteCard(card);
        }
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

    public void move(Force force , String command){
        String[] spilletdCommand = command.split(" ");
        if (force.getCanMove()) {
            Item item = game.move(force, Integer.parseInt(spilletdCommand[0]), Integer.parseInt(spilletdCommand[1]));
            if (item instanceof Flag) {
                numberOfFlag++;
                force.takeFlag((Flag) item);
            }
            items.add(item);
        }
        else
            System.out.println("force have been moved");
    }

    public boolean checkCard(String id){
        String[] spillitedId = id.split("_");
        if (spillitedId[0] == account.getUserName())
            return true;
        return false;
    }
}
