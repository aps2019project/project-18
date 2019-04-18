package Modules.PlayableThings.Item;

public class Item {
    private String name;
    private String itemId;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getItemid() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void excute(){
        //todo
    }
}
