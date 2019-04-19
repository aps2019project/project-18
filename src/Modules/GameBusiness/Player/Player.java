package Modules.GameBusiness.Player;

import Modules.Account;
import Modules.Game.Game;
import Modules.Hand;
import Modules.PlayableThings.Item.Item;

import java.util.ArrayList;

public abstract class Player {
    protected int numberOfFlag = 0;
    protected int manaPoint;
    protected Account account;
    protected Hand hand;
    protected Game game;
    protected ArrayList<Item> items = new ArrayList<>();

    public Account getAccount() {
        return account;
    }

    public abstract void playTurn();
}
