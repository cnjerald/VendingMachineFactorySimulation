/**
 * This class extends fluid, and is a major component in creating a champorado.
 *
 */


public class CoconutMilk extends Fluid {
    /**
     * This is the constructor for the CoconutMilk. It requires four parameters.
     *
     * @param name - Name of the Coconut Milk
     * @param calories - Calories of the coconut milk (per mL)
     * @param price - Price of the coconut milk (Per mL)
     * @param isSellable - Boolean value if the coconut milk is sellable or not.
     */
    CoconutMilk(String name, double calories, double price, boolean isSellable){
        super(name,calories,price,isSellable);}

}
