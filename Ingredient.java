
public class Ingredient extends Food{
    /**
     * This is the constructor for the ingredient that only requires a single parameter.
     * @param name - Name of the ingredient
     */
    Ingredient(String name){
        super(name);
    }

    /**
     * This is the constructor for the ingredient that requires four paramters.
     *
     * @param name - name of the Ingredient
     * @param calories - calories of the Ingredient
     * @param price - price of the Ingredient
     * @param sellable - isSellable status of the ingredient.
     */

    Ingredient(String name, double calories, double price,boolean sellable){
        super(name,calories,price,sellable);
    }
}
