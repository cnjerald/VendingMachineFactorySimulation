public abstract class Item {
    private String name;

    /**
     * This is the constructor for an item. It only requires a single parameter.
     * @param name- name of the item.
     */
    Item(String name){
        this.name = name;
    }

    /**
     * This is a getter for the name of the Item.
     *
     * @return Item name.
     */

    public String getName(){
        return this.name;
    }
}
