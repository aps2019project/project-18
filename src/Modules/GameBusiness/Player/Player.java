package Modules.GameBusiness.Player;

import Modules.Hand;
import Modules.PlayableThings.Item.Item;

import java.util.ArrayList;

public abstract class Player {
    protected int numberOfFlag = 0;
    protected int manaPoint;
    protected Hand hand;
    protected Game game;
    protected ArrayList<Item> items = new ArrayList<>();

    public abstract void playTurn();
}
