
import java.util.ArrayList;

/**
 * The champroado is a type of compound that is dispensed by the ChamporadoVendingMachine object.
 */
public class Champorado extends Compound{

    private double amountBoiledRice;
    private double amountHotChoco;
    private double amountCoconutMilk;
    private int maxFreeIngredients;

    /**
     * The constructor of the Champorado requires a name, the ingredients, the amount of boiled rice, coconut milk,
     * hotChoco, and as a special feature of the ChamporadoVendingMachine, the total number of free ingredients.
     *
     * @param name - Name of the champorado object
     * @param ingredients - The ingredients needed to form this object
     * @param amountBoiledRice - Amount of boiled rice needed
     * @param amountCoconutMilk - Amount of Coconut milk needed.
     * @param amountHotChoco - Amount of Hot Choco needed.
     * @param maxFreeIngredients - Total number of free ingredients.
     */

    Champorado(String name, ArrayList<Ingredient> ingredients, double amountBoiledRice, double amountCoconutMilk, double amountHotChoco, int maxFreeIngredients){
        super(name,ingredients);
        this.amountBoiledRice = amountBoiledRice;
        this.amountCoconutMilk = amountCoconutMilk;
        this.amountHotChoco = amountHotChoco;

        this.maxFreeIngredients = maxFreeIngredients;
        setBasePrice();
        setBaseCalories();
    }

    /**
     * A getter for the maximum free ingredients.
     *
     * @return total number of free incredients.
     */

    public int getMaxFreeIngredients() {
        return maxFreeIngredients;
    }


    /**
     * Getter for the amount of boiled rice.
     *
     * @return amount of boiled rice.
     */

    public double getAmountBoiledRice() {
        return amountBoiledRice;
    }

    /**
     * Getter for the amount of coconut milk
     *
     * @return total number of coconut milk.
     */

    public double getAmountCoconutMilk() {
        return amountCoconutMilk;
    }

    /**
     * Getter for the amount of hot choco
     *
     * @return total number of hot choco.
     */

    public double getAmountHotChoco() {
        return amountHotChoco;
    }

    /**
     * The setter for the base price of the champorado.
     * This setter is a standard, where the price is derived by multiplying the amount of fluid to the
     * price of the fluid.
     */

    private void setBasePrice(){
        double price = 0;

        if(!super.getIsPriceOverridden()){
            for(Ingredient ingredient : super.getBaseIngredients()){
                if(ingredient instanceof BoiledRice){
                    price += ingredient.getPrice() * amountBoiledRice;
                }
                else if(ingredient instanceof HotChoco){
                    price += ingredient.getPrice() * amountHotChoco;
                }
                else if(ingredient instanceof  CoconutMilk){
                    price += ingredient.getPrice() * amountBoiledRice;
                }
            }
            this.basePrice = price*0.01;
        }
    }

    /**
     * This method sets the calories of the champorado.
     * It is derived by multiplying the amount of fluid to the calories of the fluid.
     */

    private void setBaseCalories(){
        double calories = 0;
        for(Ingredient ingredient : super.getBaseIngredients()){
            if(ingredient instanceof BoiledRice){
                calories += ingredient.getCalories() * amountBoiledRice;
            }
            else if(ingredient instanceof HotChoco){
                calories += ingredient.getCalories() * amountHotChoco;
            }
            else if(ingredient instanceof  CoconutMilk){
                calories += ingredient.getCalories() * amountBoiledRice;
            }
        }
        this.baseCalories = (calories * 0.01);
    }

    /**
     * This method resets the price of the object to the initial calculation, given that it is overriden.
     *
     */
    public void resetPriceToDefault(){
        super.setisPriceOverridden(false);
        this.setBasePrice();
    }


}
