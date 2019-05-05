package Modules;

import Modules.PlayableThings.cards.Card;

public class Playground {
    private House[][] houses = new House[9][5];

    public void move(int homeX, int homeY, int destinationX, int destinationY) {
        houses[destinationX][destinationY].setCard(houses[homeX][homeY].getCard());
        houses[homeX][homeY].removeCard();
    }

    public int[] isCardOnGround(String cardId) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (houses[i][j].getCard().getId().equals(cardId)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public Card getCard(String id) {
        return houses[isCardOnGround(id)[0]][isCardOnGround(id)[1]].getCard();
    }

    public House[][] getGround() {
        return houses;
    }
}
