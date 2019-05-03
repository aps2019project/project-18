package Modules.GameBusiness.Player;

import Modules.Deck;

public class AI extends Player {
    private Deck deck = new Deck("AI");

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
