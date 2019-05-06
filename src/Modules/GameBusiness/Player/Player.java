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
    protected Account account;
    protected int numberOfFlag = 0;
    protected int manaPoint;
    protected Hand hand;
    protected Game game;
    protected ArrayList<Item> items = new ArrayList<>();
    private int numberOfTurnPlayerHaveFlag = 0;

    public int getNumberOfFlag() {
        return numberOfFlag;
    }

    public int getNumberOfTurnPlayerHaveFlag() {
        return numberOfTurnPlayerHaveFlag;
    }

    public void playTurn(int turn) {
        manaPoint = (turn+1)/2 + 2;
        if (manaPoint > 9)
            manaPoint = 9;
    }

    public void takeFlag(){
        numberOfFlag++;
    }

    public void aging(){
        if (numberOfFlag == 1){
            numberOfTurnPlayerHaveFlag++;
        }else {
            numberOfTurnPlayerHaveFlag = 0;
        }
    }

    public Account getAccount() {
        return account;
    }

    public Game getGame() {
        return game;
    }

    public void showCollectables(Show show) {
        show.showItems(items, null);
    }

    public Hero getHeroCard() {
        return hand.getHero();
    }
    public Item getItem(String id){
        for (Item item : items) {
            if (item.getItemId() == id)
                return item;
        }
        return null;
    }
    public void insertCard(String id, int x, int y) {
        Card card = hand.insertCard(id , manaPoint);
        Item[] items;
        if (card != null) {
            manaPoint -= card.getManaPoint();
            if (game.insertCard(card, x, y)) {
                hand.deleteCard(card);
                items = game.getPlayground().getGround()[x - 1][y - 1].getItem();
                for (Item item : items) {
                    if (card instanceof Force) {
                        if (item instanceof Flag) {
                            ((Force) card).insert((Flag) item);
                            takeFlag();
                        } else
                            this.items.add(item);
                        game.getPlayground().getGround()[x - 1][y - 1].removeItems();
                    }
                }
                System.out.format("%s is inserted in (%d , %d)\n" , id , x ,y);
            }
            else
                System.out.println("Invalid destination");
        }
        else
            System.out.println("This card is not in your hand");
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
        String[] splittedCommand = command.split(" ");
        if (force.canMove()) {
            Item[] items = game.move(force, Integer.parseInt(splittedCommand[0]), Integer.parseInt(splittedCommand[1]));
            for (Item item : items) {
                if (item instanceof Flag) {
                    numberOfFlag++;
                    force.takeFlag((Flag) item);
                } else if (item != null)
                    this.items.add(item);
            }
        }
        else
            System.out.println("force have been moved before");
    }

    public void loseFlag(int count){
        numberOfFlag -= count;
    }

    public void die(Card card){
        hand.getGraveyard().addCard(card);
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public boolean checkCard(String id){
        String[] splittedId = id.split("_");
        if (splittedId[0] == account.getUserName())
            return true;
        return false;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
