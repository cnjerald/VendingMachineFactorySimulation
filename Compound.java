import View.MessagePromptView;
import java.util.ArrayList;

public abstract class Compound{

    private ArrayList<Ingredient> baseIngredients;
    private String name;
    protected double basePrice;
    protected double baseCalories;
    private double additionalPrice;
    private boolean isPriceOverridden = false;


    /**
     * A compound requires a name, and a list of ingredients.
     *
     * @param name - name of the compound
     * @param ingredients - An arrayList of ingredients.
     */
    Compound(String name,ArrayList<Ingredient> ingredients){
        this.name = name;
        this.baseIngredients = ingredients;
        for(Ingredient ing : this.baseIngredients){
            this.basePrice += ing.getPrice();
            this.baseCalories += ing.getCalories();
        }
        additionalPrice = 0;
    }

    /**
     * This returns the name of the compound.
     *
     * @return name of the compound.
     */

    public String getName(){
        return this.name;
    }

    /**
     * This increments the additional price of the compound.
     *
     * @param price - price to be incremented to the price of the compound.
     */
    public void incrementPrice(double price){
        this.additionalPrice += price;
    }

    /**
     * This method returns the actual price is the base price of the compound added to the price of the additional price
     *
     * @return The total price of the compound with its added ingredients.
     */
    public double getActualPrice() {
        return basePrice + additionalPrice;
    }


    /**
     * This method returns the base price of the compound.
     *
     * @return The base price of the compound.
     *
     */
    public double getBasePrice(){
        return basePrice;
    }

    /**
     * This clears out the incremented price on the additional price list.
     */
    public void resetAdditionalPrice(){
        this.additionalPrice = 0;
    }

    /**
     * This method returns the base ingredients of the compound.
     * @return The base ingredients of the compound.
     */
    public ArrayList<Ingredient> getBaseIngredients(){
        return this.baseIngredients;
    }

    /**
     * This method sets the condition if the price of the compound is overriden or not.
     *
     * @param b - True or false, if the user wants to override the price or not.
     */
    public void setisPriceOverridden(boolean b){
        this.isPriceOverridden = b;
    }


    /**
     * This method returns the base calories of the compound without the additional ingredients.
     *
     * @return base calories of the compound.
     */
    public double getBaseCalories(){
        return this.baseCalories;
    }

    /**
     * This method overrides the default price of the compound.
     *
     * @param price - The new price of the compound
     * @return true if the price is valid, and the compound price is set, false if the price is invalid (negative)
     */
    public boolean setPrice(double price){

        if(price >= 0) {
            this.basePrice = price;
            isPriceOverridden = true;
            return true;
        }
        else{
            MessagePromptView errorMessage = new MessagePromptView("Unable to set a negative value for price");
            return false;
        }
    }

    /**
     * This method resets the price of the compound to its defaults.
     */

    public void resetPriceToDefault(){
        this.basePrice = 0;
        for(Ingredient ing : this.baseIngredients){
            this.basePrice += ing.getPrice();
        }
    }

    /**
     * This method determines if the price of the Compound is overriden.
     *
     * @return true if the price is overriden, false otherwise.
     */

    public boolean getIsPriceOverridden(){
        return isPriceOverridden;
    }

}
