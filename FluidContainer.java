import View.MessagePromptView;


/**
 * This abstract class is a fluid container. It holds a specific fluid defined by T.
 *
 * @param <T> A fluid object.
 */
public abstract class FluidContainer <T extends Fluid>{

    private String name;
    private double volume;
    private double capacity;
    private T fluid;

    /**
     * This is the constructor for a fluid container, it has a name, a type of fluid, and a capacity.
     *
     * @param name - name of the container
     * @param fluid - the fluid assigned specifically to this container
     * @param capacity - the capacity of the fluid container.
     */
    FluidContainer(String name,T fluid, double capacity){
        this.name = name;
        this.capacity = capacity;
        this.fluid = fluid;
    }

    /**
     * This method returns the fluid assigned to this container.
     * @return The fluid object.
     */

    public T getFluid(){
        return this.fluid;
    }

    /**
     * This method returns the name of the fluid container.
     *
     * @return The name of the fluid container
     */

    public String getName() {
        return name;
    }

    /**
     * This method sets the amount of fluid in the container.
     *
     * @param amount - amount to be set.
     * @return true if the container amount is successful, false if the amount set is negative, or will exceed the
     * capacity of the container.
     */
    public boolean setAmount(double amount){
        if(amount > capacity){
            MessagePromptView errorMessage = new MessagePromptView("Unable to set more than the capacity");
            return false;
        }
        else if(amount < 0){
            MessagePromptView errorMessage = new MessagePromptView("Unable to set a negative amount");
            return false;
        }
        else{
            this.volume = amount;
            return true;
        }
    }

    /**
     * This method refills the container to the brim, or sets the current volume equal to the capacity.
     */
    public void refillToBrim(){
        this.volume = capacity;
    }

    /**
     * This method returns the current volume of the container.
     *
     * @return Current volume level of the container.
     */
    public double getCurrentVolume(){
        return this.volume;
    }

    /**
     * This method decrements the current volume by the amount. It does not have a return value,
     * as its implementation is that it will never be called when the decrement amount exceeds the current amount.
     *
     * @param amount - amount to decrement.
     */
    public void reduceVolume(double amount){
        this.volume-=amount;
    }

    /**
     * This method returns the capacity of the fluid container.
     *
     * @return capacty - The capacity of the fluid container.
     */

    public double getCapacity(){
        return this.capacity;
    }

}
