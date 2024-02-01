/**
 * A regular vending machine has all the functionalities of the vending machine.
 */

public class RegularVM extends VendingMachine{
    /**
     * This is the constructor of a RegularVM it requires three parameters
     *
     * @param name - name of the vending machine
     * @param slotCount - slot count of the vending machine
     * @param capacity - capacity of the vending machine
     */
    RegularVM(String name, int slotCount, int capacity){
        super(name,slotCount,capacity);
    }

}
