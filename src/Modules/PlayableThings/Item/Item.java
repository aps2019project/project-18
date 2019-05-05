package Modules.PlayableThings.Item;

public class Item {
    private String name;
    private String itemId;
    private int price;
    private String description;
    private int mana;

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

    public Item copyItem() {
        return new Item(this.name, this.price, this.description);
    }
}
