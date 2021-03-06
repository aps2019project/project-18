package Server.Modules.PlayableThings.cards;

public abstract class Card {
    protected String name;
    protected String id;
    protected String description;
    protected int price;
    protected int manaPoint = 0;
    protected int number;

    public Card(String name, String description, int price, int manaPoint) {
        this.manaPoint = manaPoint;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    synchronized public void buy() {
        this.number--;
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

    abstract public void showCard();
}