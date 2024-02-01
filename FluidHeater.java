/**
 * The fluid heater is used by the champorado vending machine to heat fluids that require heating.
 */


public class FluidHeater {

    private String name;

    /**
     * The constructor of the fluid heater only requires a name.
     * @param name - name of the fluid heater.
     */
    FluidHeater(String name){
        this.name = name;
    }

    /**
     * The heatFluid method heats the fluid as long as it is below the target temperature.
     * It increments the heat of the fluid by 3 degrees every 50 millis.
     * @param fluid - Fluid object to be heated.
     */
    public void heatFluid(Fluid fluid){

        while((fluid.getTemperature() < fluid.getTargetTemperature())){
            fluid.increaseTemperature(3);

            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }


}
