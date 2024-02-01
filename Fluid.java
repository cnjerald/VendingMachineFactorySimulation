/**
 * This abstract class is a type of Ingredient, but has a property of a fluid where
 * it has a temperature and a targetTemperature when it is prepared.
 */



public abstract class Fluid extends Ingredient{
    private double temperature = 30;
    private double targetTemperature = 80.5;

    /**
     * This is the constructor for a fluid, it is similar to an Ingredient
     * @param name - name of the fluid
     * @param calories - calories of the fluid (per ml)
     * @param price - price of the fluid (per mL)
     * @param sellable - boolean if the fluid is sellable or not
     */
    Fluid(String name, double calories, double price,boolean sellable){
        super(name,calories,price,sellable);
    }

    /**
     * This returns the current temperature of the fluid.
     *
     * @return fluid temperature
     */
    public double getTemperature(){
        return this.temperature;
    }

    /**
     * This method increments the temperature by the amount.
     *
     * @param amount - amount to increment.
     */
    public void increaseTemperature(double amount){
        this.temperature += amount;
    }

    /**
     * This method returns the target temperature of the fluid when prepared.
     *
     * @return target temperature
     */

    public double getTargetTemperature(){
        return this.targetTemperature;
    }

    /**
     * This method resets the current temperature to its base temperature.
     */
    public void resetTemperature(){
        this.temperature = 30;
    }

}
