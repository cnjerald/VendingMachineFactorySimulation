import View.MessagePromptView;

import java.util.ArrayList;

/**
 * The special vending machine is a type of vending machine that allows dispensing of compounds, as compared to the
 * regular vending machine that is limited to dispensing ingredients.
 */

public class SpecialVM extends VendingMachine{

    protected int compoundSelectionIndex = -1;
    private ArrayList<Compound> compoundDatabase;
    private ArrayList<Fluid> fluidDatabase;
    private ArrayList<String> compoundSalesReport;
    private ArrayList<FluidContainer> fluidContainersDB;

    /**
     * This is a constructor for a SpecialVM. It requires 3 paramters.
     * @param name - name of the vending machine
     * @param slotCount - slot count of the vending machine
     * @param capacityCount - capacity count of the slot
     */

    SpecialVM(String name, int slotCount, int capacityCount){
        super(name,slotCount,capacityCount);
        fluidContainersDB = new ArrayList<>();
        compoundDatabase = new ArrayList<>();
        fluidDatabase = new ArrayList<>();
        compoundSalesReport = new ArrayList<>();
    }

    /**
     * This method registers a given compound into the compound database.
     *
     * @param c - the compound to be registered.
     */

    public void registerCompound(Compound c){
        this.compoundDatabase.add(c);
    }

    /**
     * This method returns a string with the details of the compound selection. If there is no compound selection it will
     * call the getSlotRequirementPayment() superclass.
     *
     * @return String information on the current money inserted and the money needed for the selected compound.
     */

    @Override
    public String getSlotRequiredPayment(){
        try{// I will try to determine if I have selected a compound.
            System.out.println("Current money inserted: " + getTotalTempValue() + " Item: " + compoundDatabase.get(compoundSelectionIndex -1).getName() + " Money needed " + compoundDatabase.get(compoundSelectionIndex -1).getActualPrice());
            return "Current money inserted: " + getTotalTempValue() + " Item: " + compoundDatabase.get(compoundSelectionIndex -1).getName() + " Money needed " + compoundDatabase.get(compoundSelectionIndex -1).getActualPrice();
        }
        catch(Exception e){ // This will execute if I did not select a compound. Therefore, a slot could be selected.
            return super.getSlotRequiredPayment();
        }
    }

    /**
     * This method registers a fluid into the database.
     *
     * @param fluid - Fluid to be registed in the database.
     */


    public void registerFluid(Fluid fluid){
        this.fluidDatabase.add(fluid);
    }

    /**
     * This method resets the price of the selected compound to default.
     *
     * @param compoundIndex - The selected index.
     *
     * @return true if index is valid, false otherwise.
     */

    public boolean resetCompoundPriceToDefault(int compoundIndex){
        try{
            this.compoundDatabase.get(compoundIndex).resetPriceToDefault();
            return true;
        }
        catch(Exception e){
            MessagePromptView messagePromptView = new MessagePromptView("Input a number within the range");
            System.out.println("Input a number within the range");
            return false;
        }
    }

    /**
     * This method overrides the price of the selected compound index into the new price.
     *
     * @param compoundIndex - Selected compound index
     * @param newPrice - new price of the selected compound
     */

    public void setOverrideCompoundPrice(int compoundIndex, double newPrice){
        this.compoundDatabase.get(compoundIndex).setPrice(newPrice);
    }

    /**
     * This method is a getter for the fluid containers of the special vending machine.
     *
     * @return list of fluidcontainer objects.
     */

    public ArrayList<FluidContainer> getFluidContainersDB(){
        return this.fluidContainersDB;
    }

    /**
     * This method adds a fluid container into the list of fluid containers.
     *
     * @param fluidContainer - Fluid container to be added.
     */
    public void addToFluidContainerDB(FluidContainer fluidContainer){
        this.fluidContainersDB.add(fluidContainer);
    }

    /**
     * This method sets the volume of the fluid container by supplying the index, and  the amount.
     *
     * @param containerIndex - index of the fluid container in the list.
     * @param amountSet - amount to be set
     */
    public void setContainerAmount(int containerIndex , double amountSet){

        try{
            this.fluidContainersDB.get(containerIndex).setAmount(amountSet);
        }
        catch (Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Invalid Index");
        }
    }

    /**
     * This method updates the entire sales record given that there is a compound that is successfully dispensed.
     *
     * @param compound The compound to be registered into the sales record for compound.
     */

    protected void updateSalesRecord(Compound compound){
        this.compoundSalesReport.add("Name: " + compound.getName() + " Compound Price: " + compound.getActualPrice());
    }


    /**
     * This method shows the sales report of the compound sales, and the slot sales. Along with this, the total sales.
     */

    @Override
    public void showSalesReport(){
        String message = "";

        if(getInitialInventory() != null){
            int i = 1;
            message += "============================== Summary of Sales ==============================\n";
            System.out.println("============================== Summary of Sales ==============================");
            for (Slot slot : super.getSlotList()) {
                if (!slot.getSlotName().equals("Empty")) {

                    // Get the slot sales. here...

                    message += "Item Name: " + slot.getSlotName() + " Initial Inventory " + super.getInitialInventory()[i-1] +
                            " Final Inventory " + slot.getCurrentCount() + " Quantity Sold: " +
                            (super.getInitialInventory()[i-1] - slot.getCurrentCount() + " Price: " + (slot.getSlotPrice()) + "\n");

                    System.out.println(i + ". Item Name: " + slot.getSlotName() + " \tInitial Inventory " + super.getInitialInventory()[i-1] +
                            " \tFinal Inventory " + slot.getCurrentCount() + " \t Quantity Sold: " + (super.getInitialInventory()[i-1] - slot.getCurrentCount()));
                }
                i++;
            }

            System.out.println("============================== Total Sales: " + getTotalSales()+ " Pesos");

            // Get the compound sales here....
            for(String s : compoundSalesReport){
                message += s + "\n";
            }
            // print the total sales.

            message += "============================== Total Sales: " + getTotalSales()+ " Pesos\n";
            // reset for usability.
            for(Slot slot : super.getSlotList()){
                slot.resetRecord();
            }

            MessagePromptView messagePromptView = new MessagePromptView(message);

            compoundSalesReport.clear();

        }
    }

    /**
     * This method is responsible for calculating the total sales made by the machine. It combines the compound sales and
     * the slot sales.
     *
     * @return The value of all sales made.
     */
    @Override
    public double getTotalSales() {
        double totalSales = 0;
        double totalCompoundSales = 0;

        for(String s : compoundSalesReport){
            String[] parts = s.split("Name:|Compound Price:");
            // Extract the sales price part (index 2) and remove any leading/trailing spaces
            String salesPriceString = parts[2].trim();
            totalCompoundSales = Double.parseDouble(salesPriceString);

        }
        totalSales += (super.getTotalSales());
        totalSales += totalCompoundSales;

        return totalSales;
    }


    /**
     * This is a getter for the compound database.
     *
     * @return CompoundDatabase ArrayList
     */
    public ArrayList<Compound> getCompoundDatabase(){
        return this.compoundDatabase;
    }

    /**
     * This is a getter for the selected compound.
     *
     * @return Compound selected by the user.
     */

    public Compound getSelectedCompound(){
        return this.compoundDatabase.get(compoundSelectionIndex);
    }

}