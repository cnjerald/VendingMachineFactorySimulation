/**
 * This class is a container that is designed to specifically hold the HotChoco and instances of it.
 */


public class HotChocoContainer extends FluidContainer<HotChoco>{
    /**
     * This is the constructor for the HotChocoContainer\
     *
     * @param name- name of the hot choco container
     * @param hc - the hot choco object
     * @param capacity - capacity of this container
     */
    HotChocoContainer(String name,HotChoco hc,double capacity){
        super(name,hc,capacity);
    }

}
