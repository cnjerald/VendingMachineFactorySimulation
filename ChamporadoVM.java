import View.MessagePromptView;
import View.MessageView;

import java.util.ArrayList;

/**
 * The ChamporadoVM is a type of SpecialVM that dispenses a specific compound "Champorado".
 */

public class ChamporadoVM extends SpecialVM {

    private BoiledRiceContainer boiledRiceContainer;
    private CoconutMilkContainer coconutMilkContainer;
    private HotChocoContainer hotChocoContainer;
    private FluidDispenser fluidDispenser;

    private ArrayList<Integer> itemReservation;

    private ArrayList<Integer> addItemReservation;

    private FluidHeater fluidHeater;


    /**
     * This is the constructor for the ChamporadoVM. It requires a name, slot count, and capacity count.
     *
     * @param name - name of the machine.
     * @param slotCount - slot count of the machine
     * @param capacityCount - capacity of each slots of the machine.
     */
    ChamporadoVM(String name, int slotCount, int capacityCount){
        super(name,slotCount,capacityCount);
        itemReservation = new ArrayList<>();
        addItemReservation = new ArrayList<>();

    }

    /**
     * This method gets the currenct champorado constructed by the user. It Contains the name, price, and calories
     * of the selected champorado, the additional ingredients with the individual price and calories, and
     * the total price and calories of the entire combination of the champorado and the additional ingredients.
     *
     * @return The details of the constructed champorado as a String array.
     */

    public ArrayList<String> getCurrentChamporadoDetails(){
        ArrayList<String> output = new ArrayList<>();
        output.clear();
        double additionalCalories = 0;
        if(compoundSelectionIndex != -1) {

            // This adds the details of the Champorado without any additional ingredients (Or base)
            output.add("Current Champorado: " + super.getCompoundDatabase().get(super.compoundSelectionIndex).getName() + " Price: " + super.getCompoundDatabase().get(super.compoundSelectionIndex).getBasePrice() + " Calories: " + super.getCompoundDatabase().get(super.compoundSelectionIndex).getBaseCalories());
            output.add("Free additional Ingredient");
            System.out.println("Current Champorado: " + super.getCompoundDatabase().get(super.compoundSelectionIndex).getName());

            // This shows the free additional ingredients of the champorado.
            System.out.println("Free additional Ingredient");
            for (int ing = 0; ing < super.getIngredientList().size(); ing++) {
                if (itemReservation.get(ing) > 0) {
                    output.add(super.getIngredientList().get(ing).getName() + " Amount: " + itemReservation.get(ing) + " Price: " + super.getIngredientList().get(ing).getPrice() + " Calories: " + super.getIngredientList().get(ing).getCalories());
                    additionalCalories += super.getIngredientList().get(ing).getCalories() * itemReservation.get(ing);

                    System.out.println(super.getIngredientList().get(ing).getName() + " Amount: " + itemReservation.get(ing) + " Price: " + super.getIngredientList().get(ing).getPrice());
                }
            }
            output.add("Nothing follows.\n");
            System.out.println("Nothing follows.");

            // This shows the paid additional ingredients of the champorado.
            output.add("Paid additional Ingredient");
            System.out.println("Paid additional Ingredient");
            for(int ing = 0; ing < super.getIngredientList().size(); ing++){
                if(addItemReservation.get(ing) > 0){
                    output.add(super.getIngredientList().get(ing).getName() + " Amount: " + addItemReservation.get(ing) + " Price: " + super.getIngredientList().get(ing).getPrice()+ " Calories: " + super.getIngredientList().get(ing).getCalories());
                    additionalCalories += super.getIngredientList().get(ing).getCalories() * addItemReservation.get(ing) ;
                    System.out.println(super.getIngredientList().get(ing).getName() + " Amount: " + addItemReservation.get(ing) + " Price: " + super.getIngredientList().get(ing).getPrice());
                }
            }
            // This shows the total cost and calories considering both the champorado and additional ingredients.
            output.add("Nothing follows.\n");
            output.add("Total cost: " + (super.getCompoundDatabase().get(super.compoundSelectionIndex).getActualPrice()));
            output.add("Total calories: " + (super.getCompoundDatabase().get(super.compoundSelectionIndex).getBaseCalories()+additionalCalories));

            System.out.println("Total cost: " + (super.getCompoundDatabase().get(super.compoundSelectionIndex).getActualPrice()));
            System.out.println("Nothing follows.\n");
        }
        else{
            output.add("Create a champorado first");
            System.out.println("Create a champorado first");
        }
        return output;
    }

    /**
     * This installs the required fluid containers, and heater to the machine.
     *
     * @param br - BoiledRice Container
     * @param cm - CoconutMilk Container
     * @param hc - HotChoco Container
     * @param fh - Fluid Heater.
     */
    public void installFluidContainers(BoiledRiceContainer br, CoconutMilkContainer cm, HotChocoContainer hc, FluidHeater fh){
        System.out.println("Installing fluid containers...");
        this.boiledRiceContainer = br;
        this.coconutMilkContainer = cm;
        this.hotChocoContainer = hc;
        this.fluidHeater = fh;
    }

    /**
     * This installs the fluid dispenser responsible for dispensing the fluid in the container.
     *
     * @param fluidDispenser - Fluid dispenser object.
     */

    public void installFluidDispenser(FluidDispenser fluidDispenser){
        this.fluidDispenser = fluidDispenser;
    }


    /**
     * This method selects a slot. Compared to its super class, it first clears out the selected champorado and the reserved items
     * This is to avoid conflict in dispensing the selected item
     *
     * @param index - index selected by the user.
     * @return true if the selected index is valid, false otherwise
     */
    public boolean selectSlot(int index){
        itemReservation.clear();
        this.addItemReservation.clear();
        super.compoundSelectionIndex = -1;

        super.selectSlot(index);

        return false;
    }

    /**
     * This method clears the selected champorado, the selected free ingredients, and the selected paid ingredients.
     */

    public void clearChamporadoSelection(){
        super.compoundSelectionIndex = -1;
        itemReservation.clear();
        addItemReservation.clear();
    }

    /**
     * This method clears the selected free ingredients.
     */

    public void clearFreeAdditionList() {
        int[] ingredientStock = determineIngredientStock();
        this.itemReservation.clear();

        for (int j = 0; j < ingredientStock.length; j++) {
            itemReservation.add(0);
        }
    }

    /**
     * This method clears the selected paid ingredients.
     */

    public void clearPaidAdditionList(){
        int[] ingredientStock = determineIngredientStock();
        this.addItemReservation.clear();
        super.getCompoundDatabase().get(compoundSelectionIndex).resetAdditionalPrice();
        for (int j = 0; j < ingredientStock.length; j++) {
            addItemReservation.add(0);
        }
    }

    /**
     * This method adds a free ingredient to the selected champorado.
     *
     * @param addSelectionIndex - The index of the ingredient to be added
     */

    public void addFreeItem(int addSelectionIndex){
        int[] ingredientStock = determineIngredientStock();
        try{
            if((ingredientStock[addSelectionIndex]-itemReservation.get(addSelectionIndex)-addItemReservation.get(addSelectionIndex)-1) >= 0){
                itemReservation.set(addSelectionIndex, itemReservation.get(addSelectionIndex) + 1);
            }else{
                MessagePromptView errorMessage = new MessagePromptView("This item is out of stock");
                System.out.println("There is no stock for this item");
            }
        }
        catch (Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Please select an item");
        }
    }

    /**
     * This method adds a paid ingredient to the selected champorado.
     *
     * @param addSelectionIndex - The index of the ingredient to be added.
     */

    public void addPaidItem(int addSelectionIndex){
        int[] ingredientStock = determineIngredientStock();
        try{
            if((ingredientStock[addSelectionIndex]-itemReservation.get(addSelectionIndex)-addItemReservation.get(addSelectionIndex)-1) >= 0){

                addItemReservation.set(addSelectionIndex , addItemReservation.get(addSelectionIndex) + 1); // increase reserved

                super.getCompoundDatabase().get(compoundSelectionIndex).incrementPrice(super.getIngredientList().get(addSelectionIndex).getPrice()); // increase cost of item
            }else{
                MessagePromptView errorMessage = new MessagePromptView("This item is out of stock");
                System.out.println("There is no stock for this item");
            }
        }
        catch (Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Please select an item");
        }
    }

    /**
     * This method is where the user selects a champorado. The selected champorado is in its base form without any of
     * the additional ingredients. Furthermore, when selecting a champorado the selected slot index is reset to avoid
     * collision during dispensing.
     *
     * @param index - index of the champorado from the compound list.
     *
     * @return true if the selection is valid, false otherwise.
     */

    public boolean selectChamporado(int index){
        int[] champoradoStock = determineChamporadoStock();
        int[] ingredientStock = determineIngredientStock();

        try{
            if(champoradoStock[index] > 0) {
                    super.compoundSelectionIndex = index;
                    System.out.println("Selected " + getCompoundDatabase().get(super.compoundSelectionIndex).getName());
                    itemReservation.clear();
                    addItemReservation.clear();
                    for (int j = 0; j < ingredientStock.length; j++) {
                        itemReservation.add(0);
                        addItemReservation.add(0);
                    }
                    return true;
                }

                else{
                MessagePromptView errorMessage = new MessagePromptView("This selection is out of stock.");
                }
        }
        catch (Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Select a champorado first.");
        }

        return false;
    }

    /**
     * This method overrides the superClass getSlotRequiredPayment() method.
     * This method shows how much money the customer needs to input in order to pay for the constructed champorado.
     * This method goes to its superclass if a champorado is not selected.
     *
     * @return a String showing how much money is inserted, the selected item, and the amount of money needed.
     */
    @Override
    public String getSlotRequiredPayment(){
        try{
            System.out.println("Current money inserted: " + getTotalTempValue() + " Item: " + getCompoundDatabase().get(compoundSelectionIndex).getName() + " Money needed " + ((Champorado)getCompoundDatabase().get(compoundSelectionIndex)).getActualPrice());
            return "Current money inserted: " + getTotalTempValue() + " Item: " + getCompoundDatabase().get(compoundSelectionIndex).getName() + " Money needed " + getCompoundDatabase().get(compoundSelectionIndex).getActualPrice();
        }
        catch(Exception e){
            return super.getSlotRequiredPayment(); // if a compound is not selected, then maybe a slot is selected.
        }
    }

    /**
     * This method overrides the superClass dispenseItem() method.
     * As it dispenses a specific champorado, as compared to dispensing a slot ingredient.
     *
     * @return true if the machine is able to dispense, false if not
     */

    @Override
    public boolean dispenseItem(){
        if(compoundSelectionIndex!=-1){ // if I selected a champorado
            if(super.canGiveChange(super.getCompoundDatabase().get(compoundSelectionIndex).getActualPrice())){ // if I can give change
                if(super.isTempEnough(super.getCompoundDatabase().get(compoundSelectionIndex).getActualPrice())){ // if I have enough money...
                    BoiledRice br;
                    HotChoco hc;
                    CoconutMilk cm;
                    ArrayList<Ingredient> ingredients = new ArrayList<>();

                    // I transfer all my selected additional ingredients regardless if free or paid.
                    int[] amount = new int[super.getIngredientList().size()];

                    for(int i = 0 ; i < super.getIngredientList().size(); i++){
                        amount[i] = itemReservation.get(i) + addItemReservation.get(i);
                    }

                    // Dispense a container
                    MessageView dispenseMessage = new MessageView("Dispensing Container. . .",2000);
                    System.out.println("Dispensing Container. . .");

                    //Heat and dispense boiled rice.
                    dispenseMessage = new MessageView("Heating boiled rice to heating pot. . .",2000);
                    System.out.println("Heating boiled rice to heating pot. . .");
                    this.fluidDispenser.dispenseBoiledRice(((Champorado)super.getCompoundDatabase().get(super.compoundSelectionIndex)).getAmountBoiledRice());
                    br = ((BoiledRice) this.fluidDispenser.getDispensedFluid());

                    fluidHeater.heatFluid(br);
                    dispenseMessage = new MessageView("Pouring boiled rice. . .",2000);
                    System.out.println("Pouring boiled rice. . .");

                    //Heat and dispense Hot Choco.
                    dispenseMessage = new MessageView("Heating hot choco in heating pot. . .",2000);
                    System.out.println("Heating hot choco in heating pot. . .");
                    this.fluidDispenser.dispenseHotChoco(((Champorado)super.getCompoundDatabase().get(super.compoundSelectionIndex)).getAmountHotChoco());

                    hc = (HotChoco) this.fluidDispenser.getDispensedFluid();
                    fluidHeater.heatFluid(hc);

                    dispenseMessage = new MessageView("Pouring hot choco. . .",2000);
                    System.out.println("Pouring hot choco. . .");
                    this.fluidDispenser.dispenseCoconutMilk(((Champorado)super.getCompoundDatabase().get(super.compoundSelectionIndex)).getAmountCoconutMilk());

                    // Dispense Coconut Milk.
                    cm = (CoconutMilk) this.fluidDispenser.getDispensedFluid();
                    dispenseMessage = new MessageView("Drizzling with coconut milk",2000);
                    System.out.println("Drizzling with coconut milk");

                    //dispense selected toppings.
                    System.out.print("Topping with");

                    for(int i = 0 ; i < super.getIngredientList().size(); i++){
                        while(amount[i] > 0){
                            for(Slot s : super.getSlotList()){
                                if(s.getSlotName().equals(super.getIngredientList().get(i).getName())){
                                    super.getItemDispenser().dispenseItem(s);
                                    ingredients.add(super.getItemDispenser().getDispensedItem());
                                    amount[i]--;
                                    System.out.print(" " + super.getItemDispenser().getDispensedItem().getName());
                                    dispenseMessage = new MessageView("Topping with:" + super.getItemDispenser().getDispensedItem().getName() ,3000);

                                }
                            }
                        }
                    }

                    System.out.println("\n");
                    //record and reset parameters for repeatability.

                    super.updateSalesRecord(super.getCompoundDatabase().get(compoundSelectionIndex));
                    MessagePromptView successMessage = new MessagePromptView("Successfully dispensed champorado" + "\n Please get your change");
                    super.giveChange(super.getCompoundDatabase().get(compoundSelectionIndex).getActualPrice());
                    super.getCompoundDatabase().get(compoundSelectionIndex).resetAdditionalPrice();

                    br.resetTemperature();
                    hc.resetTemperature();

                    compoundSelectionIndex = -1; // reset compound selection
                    itemReservation.clear(); // clear reserved slot
                    addItemReservation.clear(); // clear reserved slot
                    return true;
                }
                else{

                    System.out.println("Inserted money is insufficient.");
                }
            }
            else{
                System.out.println("Machine unable to dispense due to it being unable to give change.");
            }

        }
        else{// else I might be selecting a slot, and not a compound therefore I go to the superclass.
            super.dispenseItem();
        }
        return false;
    }

    /**
     * This method determines the stock count of the ingredient and returns the count in a form of an array
     * with respect to the index of the ingredientList().
     *
     * @return Array with count stock of each index of the ingredientList()
     */

    public int[] determineIngredientStock(){
        int[] count = new int[super.getIngredientList().size()];

        for(Slot s : super.getSlotList()){
            int i = 0;
            for(Ingredient ingredient : getIngredientList()){
                if(!s.isEmpty()){
                    if(s.getItem().equals(ingredient)){
                        count[i] += s.getCurrentCount();
                    }
                }
                i++;
            }
        }
        return count;
    }

    /**
     * This method determines the champorado Stock with respect to the compound database index where the type of champorado
     * is stored.
     *
     * @return an integer array with count of stock of each stock with respect to its index.
     */


    public int[] determineChamporadoStock(){
        int boiledRiceCount; // Core
        int hotChocoCount; // Core
        int coconutMilkCount; // Core
        int ingredientCount = 0; // AnyRand

        // Get the volumes of the containers.
        boiledRiceCount = (int) boiledRiceContainer.getCurrentVolume();
        hotChocoCount = (int) hotChocoContainer.getCurrentVolume();
        coconutMilkCount = (int) hotChocoContainer.getCurrentVolume();

        // I only want to count the ingredients here, regardless of which since I only want to determine how much
        // free ingredients I can give.
            for(Slot s : super.getSlotList()){
                // get minima on all........
                try{
                    ingredientCount += s.getCurrentCount();
                }
                catch(Exception e){
                    System.out.println("Debug: Tested slot empty...");
                }
            }
        int[] count = new int[getCompoundDatabase().size()];
            for(int i = 0 ; i < getCompoundDatabase().size(); i++){

                /*In here, if the selected champorado does not have a free ingredients, it has a separate function, as it will violate
                 integer division.  The way this works is to get the minima of all components, and whichever is the lowest, it is the number
                 of champorado I can supply.
                 */
                if(((Champorado)getCompoundDatabase().get(i)).getMaxFreeIngredients() == 0){
                    count[i] = Math.min(
                                Math.min(
                                        (int) (boiledRiceCount / ((Champorado) getCompoundDatabase().get(i)).getAmountBoiledRice()),
                                        (int) (hotChocoCount / ((Champorado) getCompoundDatabase().get(i)).getAmountHotChoco())),
                                        (int) (coconutMilkCount / ((Champorado) getCompoundDatabase().get(i)).getAmountCoconutMilk()));

                }

                /*In here, the total ingredient count is divided by the maximum free ingredients. After this, a similar minima of the
                  ingredients is determined to get the amount of stock available for the champorado.
                */
                else{
                    count[i] = Math.min(
                            Math.min(
                                    Math.min((int) (boiledRiceCount / ((Champorado) getCompoundDatabase().get(i)).getAmountBoiledRice()),
                                            (int) (hotChocoCount / ((Champorado) getCompoundDatabase().get(i)).getAmountHotChoco())),
                                    (int) (coconutMilkCount / ((Champorado) getCompoundDatabase().get(i)).getAmountCoconutMilk())),
                            ingredientCount/((Champorado)getCompoundDatabase().get(i)).getMaxFreeIngredients());
                }
            }

        return count;
    }

    /**
     * This method refills all containers to its maximum capacity.
     */
    public void refillContainersToBrim(){
        this.boiledRiceContainer.refillToBrim();
        this.hotChocoContainer.refillToBrim();
        this.coconutMilkContainer.refillToBrim();
    }

    /**
     * This method gets the current compound selection of the customer with respect to the compound database.
     * @return
     */

    public int getCompoundSelectionIndex(){
        return this.compoundSelectionIndex;
    }

    /**
     * This method gets all the paid reserved items selected by the user.
     *
     * @return Array containing the count of each paid item.
     */

    public ArrayList<Integer> getItemReservation(){
        return this.itemReservation;
    }

    /**
     * This method gets all the additional free items selected by the user.
     *
     * @return Array containing the count of each free item.
     */
    public ArrayList<Integer> getAddItemReservation(){
        return this.addItemReservation;
    }

    /**
     * This method returns the boiled rice container object of the Champorado vending machine.
     *
     * @return boiledRiceContainer
     */

    public BoiledRiceContainer getBoiledRiceContainer(){
        return this.boiledRiceContainer;
    }

    /**
     * This method returns the Coconut Milk container object of the Champorado vending machine.
     *
     * @return CoconutMilkContainer
     */

    public CoconutMilkContainer getCoconutMilkContainer(){
        return this.coconutMilkContainer;
    }

    /**
     * This method returns the HotChoco container object of the Champorado vending machine.
     *
     * @return hotChocoContainer
     */

    public HotChocoContainer getHotChocoContainer(){
        return this.hotChocoContainer;
    }

}
