package Server.Modules.PlayableThings.Item;

import Server.Modules.PlayableThings.cards.Spell.Spell;

public class Item {
    protected String name;
    private String itemId;
    private int price;
    private String description;
    private int mana;
    private int turns;
    private boolean infinitive;
    private boolean used = false;
    private Spell spell;
    private int number;

    public Item(String name, int price, String description , int mana , int turns , boolean infinitive , Spell spell) {
        this.mana = mana;
        this.turns = turns;
        this.infinitive = infinitive;
        this.spell = spell;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item(String name, int price, String description ) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item() {

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

    public Item getItemCopy() {
        Item item;
        if (spell != null)
            item = new Item( name,  price,  description ,  mana ,  turns ,  infinitive ,  (Spell)spell.getCopyCard());
        else
            item = new Item( name,  price,  description ,  mana ,  turns ,  infinitive ,  null);
        item.setItemId(this.itemId);
        return item;
    }
    public void use() {
        used = true;
    }
    public int getMana() {
        return mana;
    }

    public int getTurns() {
        return turns;
    }

    public boolean isInfitive() {
        return infinitive;
    }

    public boolean isUsed() {
        return used;
    }

    public Spell getSpell() {
        return spell;
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
}
