package Modules.PlayableThings.cards;

public abstract class Card {
    protected String name;
    protected String id;
    protected String description;
    protected int price;

    public Card(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Card() {

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}