/**
 * The boiledRiceContainer is designed to hold a specific fluid which is the BoiledRice object.
 */


public class BoiledRiceContainer extends FluidContainer <BoiledRice>{
    /**
     * A constructor to hold the boiled rice.
     * @param name - Name of the container
     * @param br - The boiled rice object
     * @param capacity - capacity of the container.
     */
    BoiledRiceContainer(String name,BoiledRice br,double capacity){
        super(name,br,capacity);
    }

}
