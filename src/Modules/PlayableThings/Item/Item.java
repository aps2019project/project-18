package Modules.PlayableThings.Item;

import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Spell;

public class Item {
    private String name;
    private String itemId;
    private int price;
    private String description;

    public Item(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item() {

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void execute() {
        //todo
    }

    public Item copyItem(Item item) {
        return new Item(item.name, item.price, item.description);
    }
}
