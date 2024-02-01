/**
 * This class is a container designed to hold Coconut milk.
 */

public class CoconutMilkContainer extends FluidContainer <CoconutMilk>{

    /**
     * This is a constructor for the CoconutMilkContainer
     *
     * @param name - Name of the container
     * @param cm - The coconut milk
     * @param capacity - The capacity
     */
    CoconutMilkContainer(String name,CoconutMilk cm,double capacity){
        super(name,cm,capacity);
    }
}
