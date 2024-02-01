/**
 * This abstract class is an extension of the Item, as Food contains more parameters such as
 * the calories, price, and isSellable status.
 */


public abstract class Food extends Item {

    private double calories;
    private double price;
    private boolean isSellable = true;

    /**
     * This constructs a Food object by using the name as the only parameter.
     *
     * @param name - Name of the food item.
     */
    Food(String name){
        super(name);
    }


    /**
     * This constructs a food object by supplying three parameters.
     *
     * @param name - name of the food item
     * @param calories - Calorie amount of the food item
     * @param price - Price of the food item
     */
    Food(String name,double calories,double price){
        super(name);
        this.calories = calories;
        this.price = price;
    }

    /**
     * This constructor constructs the food object with an additional parameter, which is the sellable status.
     *
     * @param name - Name of the food item
     * @param calories - Calories of the food item
     * @param price - Price of the food item
     * @param sellable - Is sellable status of the item.
     */

    Food(String name,double calories,double price,boolean sellable){
        this(name,calories,price);
        isSellable = sellable;
    }

    /**
     * This method is a setter for the calories.
     *
     * @param calories - The new amount of calories.
     * @return true if the given calories is positive, false otherwise.
     */

    public boolean setCalories(double calories) {
        if(calories >= 0)
        {
            this.calories = calories;
            return true;
        }
        return false;
    }

    /**
     * This method sets the price of the Food.
     *
     * @param price - The new price
     * @return true if the given price is positive, false otherwise.
     */

    public boolean setPrice(double price){
        if(price >= 0){
            this.price = price;
            return true;
        }
        return false;
    }

    /**
     * This method sets the sellable status of the Food.
     *
     * @param bool - true or false input of the user that sets the isSellable status.
     */
    public void setSellable(boolean bool){
        isSellable = bool;
    }

    /**
     * This method returns the sellable status of the Food.
     *
     * @return sellable status
     */

    public boolean getSellableStatus(){
        return isSellable;
    }

    /**
     * This method returns the calories of the Food.
     *
     * @return the calorie amount.
     */

    public double getCalories() {
        return this.calories;
    }

    /**
     * This method returns the price of the Food.
     *
     * @return the price.
     */

    public double getPrice(){
        return this.price;
    }




}
