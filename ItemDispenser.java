/**
 * The ItemDispenser class allows the Vending Machine to give out (dispense) items.
 * There is only one (1) distinct attribute, and it is an Item object.
 */
public class ItemDispenser {

    private Ingredient ingredient;

    /**
     * This method dispenses the item in the given slot.
     * @param slot - Selected slot by the user.
     */
    public void dispenseItem (Slot slot) {
        this.ingredient = slot.getItem();
        slot.reduceStock();
    }

    /**
     * This is a getter for the Dispensed Item
     * @return this.item - Item
     */
    public Ingredient getDispensedItem() {

        return this.ingredient;
    }

}
