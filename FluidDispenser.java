/**
 * This class is responsible for decrementing the volume of the fluid container.
 * This is specifically used by the Champroado Vending Machine.
 */

public class FluidDispenser {

    private BoiledRiceContainer br;
    private HotChocoContainer hc;
    private CoconutMilkContainer cm;
    private Fluid fluid;


    /**
     * The constructor of the Fluid Dispenser. It contains three parameters.
     *
     * @param br - The BoiledRiceContainer of the machine
     * @param hc - The HotChocoContainer of the machine
     * @param cm - The CoconutMilkContainer of the machine
     */
    FluidDispenser(BoiledRiceContainer br, HotChocoContainer hc, CoconutMilkContainer cm){
        this.br = br;
        this.hc = hc;
        this.cm = cm;
    }

    /**
     * This decrements the boiled rice container by the amount
     *
     * @param amount - The amount to decrement the boiled rice container volume by.
     */

    public void dispenseBoiledRice(double amount){
        this.br.reduceVolume(amount);
        this.fluid = this.br.getFluid();
    }
    /**
     * This decrements the Hot Choco container by the amount
     *
     * @param amount - The amount to decrement the Hot Choco container volume by.
     */
    public void dispenseHotChoco(double amount){
        this.hc.reduceVolume(amount);
        this.fluid = this.hc.getFluid();
    }

    /**
     * This decrements the Coconut Milk container by the amount
     *
     * @param amount - The amount to decrement the Coconut Milk container volume by.
     */
    public void dispenseCoconutMilk(double amount){
        this.cm.reduceVolume(amount);
        this.fluid = this.cm.getFluid();
    }

    /**
     * This returns the last fluid dispensed.
     *
     * @return last dispensed fluid Object.
     */

    public Fluid getDispensedFluid(){
        return this.fluid;
    }





}
