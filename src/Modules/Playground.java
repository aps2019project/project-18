package Modules;

import Modules.PlayableThings.House;

public class Playground {
    private House[][] houses = new House[9][5];

    public void move(int homeX, int homeY, int destinationX, int destinationY) {
        houses[destinationX][destinationY].setCard(houses[homeX][homeY].getCard());
        houses[homeX][homeY].removeCard();
    }
}
