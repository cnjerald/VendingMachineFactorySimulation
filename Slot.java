import java.util.ArrayList;

/**
 * The Slot class allows the Regular Vending Machine "RMachine" Class to
 * store an Item. It has four (4) distinct variables: the slot's capacity,
 * the name of the item being stored, the current count of the indicated item in the slot,
 * and the Item object instance itself.
 */
public class Slot {

    private String slotName;
    private int capacity;
    private ArrayList<Ingredient> item;

    private double slotPrice;

    /**
     * This is the constructor of the slot. It will automatically instantiate the itemList, slotname, and slot price.
     */


    public Slot(){
        this.item = new ArrayList<>();
        this.slotName = "Empty";
        this.slotPrice = -1;
    }

    /**
     * This is the setter for the Slot's capacity.
     * @param n - the indicated capacity the user would like to set
     * @return true/false - boolean value from condition checked if (n >= 10)
     */
    public boolean setCapacity(int n) {
        if (n >= 10)
        {
            capacity = n;
            return true;
        }
        return false;
    }

    /**
     * This is the getter for the Slot's capacity.
     * @return capacity - integer
     */
    public int getCapacity() {

        return capacity;
    }

    /**
     * This is the getter for the Slot item's current count.
     * @return this.currentCount - integer
     */
    public int getCurrentCount() {

        return this.item.size();
    }


    /**
     * This is the getter for the Slot's designated Item.
     * @return this.item - Item
     */
    public Ingredient getItem() {
        return this.item.get(0);
    }

    /**
     * This method returns the name of the item.
     *
     * @return the name of the item in the slot.
     */

    public String getItemName(){
        if(this.item.size() == 0){
            return "Empty";
        }
        return this.item.get(0).getName();
    }

    /**
     * This method returns the calories of the item.
     *
     * @return the calories of the item in the slot.
     */

    public double getItemCalories(){
        if(this.item.size() == 0){
            return 0;
        }
        else{
            return this.item.get(0).getCalories();
        }
    }

    /**
     * This method returns the price of the item.
     *
     * @return the price of the item in the slot.
     */

    public double getItemPrice(){
        if(this.item.size() == 0){
            return 0;
        }
        else{
            return this.item.get(0).getPrice();
        }
    }

    /**
     * This method adds n amount of stock into the slot, given that there is an item stored in the slot.
     *
     * @param n - number of items to add.
     * @param item - The item to add.
     * @return
     */

    public boolean addStock(int n, Ingredient item) {
        if(this.item.size() + n > capacity)
        {
            return false;
        }
        else {
            for(int i = 0 ; i < n ; i++){
                this.item.add(item);
            }
            return true;
        }
    }


    /**
     * This acts as a history for the slot. As the item is dispensed, there will be no traces left in the slot for the details
     * of the item.
     *
     * @return the name last stored item in this slot.
     */

    public String getSlotName(){
        return this.slotName;
    }

    /**
     * This acts as a history for the slot. As the item is dispensed, there will be no traces left in the slot for the details
     * of the item.
     *
     * @return the price last stored item in this slot.
     */

    public double getSlotPrice(){
        return this.slotPrice;
    }

    /**
     * When this method is called, it reduces the stock of the slot by 1.
     *
     * @return true if the slot is greater than 0, false otherwise.
     */

    public boolean reduceStock() {
        if(this.getCurrentCount()>0){
            this.item.remove(0);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method allows the user [Vending Machine Factory] to check if the Slot's itemName attribute is
     * empty by validating if its name is equal to "Empty".
     * @return true/false - boolean value after checking if Slot itemName is "Empty"
     */
    public boolean isEmpty() {
        if(this.item.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to set a specific item into this slot.
     *
     * @param item - Item to be placed on the slot.
     *
     * @return true if the slot is previously empty, false otherwise.
     */

    public boolean stockItem(Ingredient item) {

        if(this.item.size() == 0){
            this.item.add(item);
            return true;
        }
        return false;
    }

    /**
     * This method is a continuation of the stockItem() method. If there is an item present in the slot,
     * then this method overrides it by removing the item, and replacing it with this item.
     *
     * @param item - item to be replaced in the slot.
     */

    public void replaceItem(Ingredient item){
        this.item.clear();
        this.item.add(item);
    }


    /**
     * This resets history record of the slot. Usually called after displaying the sales history.
     */
    public void resetRecord(){
        this.slotName = "Empty";
        this.slotPrice = -1;
    }

    /**
     * This method creates a history record of the slot. Usually called after leaving maintainance mode.
     */
    public void setRecord() {
        if(this.item.size()!=0){
            this.slotName = this.getItem().getName();
            this.slotPrice = this.getItem().getPrice();
        }
    }

    /**
     * This method determines if the current item in the slot is sellable or not.
     * @returnr true if the item is sellable, false otherwise.
     */

    public boolean isItemSellable(){
        try{
            return (item.get(0).getSellableStatus());
        }
        catch(Exception e){
            return true;
        }

    }


}
