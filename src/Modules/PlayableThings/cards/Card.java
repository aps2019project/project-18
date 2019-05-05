package Modules.PlayableThings.cards;

public abstract class Card {
    protected String name;
    protected String id;
    protected String description;
    protected int price;
    protected int manaPoint = 0;

    public Card(String name, String description, int price, int manaPoint) {
        this.manaPoint = manaPoint;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Card() {

    }

    public String getPlayerUserNameWhoHaveThisCard() {
        return id.split("_")[0];
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public abstract Card getCopyCard();

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