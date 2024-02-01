import View.SetSlotAddChoiceView;
import View.MessagePromptView;

import java.util.ArrayList;

public class VendingMachineModel {

    private ArrayList<RegularVM> rMachineList = new ArrayList<>();
    private ArrayList<SpecialVM> sMachineList = new ArrayList<>();
    private Double[] coinDenominations= {.01,0.05,0.10,0.25,0.5,1d,5d,10d,20d}; // coins
    private Integer[] billDenominations = {20,50,100,200,500,1000}; // bills
    private VendingMachine currentMachine = null;


    // Add Regular Vending Machine............
    public boolean addrMachine(String name, String slots, String capacity){
        int slotCount = 0;
        int capacityCount = 0;

        try{
            slotCount = Integer.parseInt(slots);
        }
        catch(Exception e){
            showErrorMessage("Input a number for slots");
            return false;
        }
        try{
            capacityCount = Integer.parseInt(capacity);
        }
        catch (Exception e){
            showErrorMessage("Input a number for capacity");
            return false;
        }

        for(RegularVM regularVM : rMachineList){
            if(name.equals(regularVM.getName())){
                showErrorMessage("Duplicate names detected");
                return false;
            }
        }
        try{
            if(slotCount < 8){
                showErrorMessage("Slot count must be greater than 8");
                return false;
            }
            else if(capacityCount < 10){
                showErrorMessage("Capacity count must be greater than 10");
                return false;
            }
            else{
                RegularVM rm = new RegularVM(name,slotCount,capacityCount);
                rm.installItemDispenser(new ItemDispenser());
                rm.installCoinBank(new CoinBank(coinDenominations));
                rm.installBillBank(new BillBank(billDenominations));
                rm.installCoinSlot(new CoinSlot(rm.getCoinBank()));
                rm.installCoinDispenser(new CoinDispenser(rm.getCoinBank()));
                rm.installBillSlot(new BillSlot(rm.getBillBank()));

                rMachineList.add(rm);
                currentMachine = rm;
                showErrorMessage(rm.getName() + " has been registered to the database");
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            showErrorMessage("Input a number");
            System.out.println("input a number");
            return false; // returns 0 if not number input
        }
    }

    public boolean addsMachine(String name, String slots, String capacity){
        int slotCount;
        int capacityCount;

        try{
            slotCount = Integer.parseInt(slots);
        }
        catch(Exception e){
            showErrorMessage("Input a number for slots");
            return false;
        }
        try{
            capacityCount = Integer.parseInt(capacity);
        }
        catch (Exception e){
            showErrorMessage("Input a number for capacity");
            return false;
        }


        for(SpecialVM specialVM : sMachineList){
            if(name.equals(specialVM.getName())){
                showErrorMessage("Duplicate machines detected");
                return false;
            }
        }
        try{
            if(slotCount < 8){
                showErrorMessage("Insufficient slots");
            }
            else if(capacityCount < 10){
                showErrorMessage("Insufficient capacity count");
            }
            else{
                ChamporadoVM cm = new ChamporadoVM(name,slotCount,capacityCount);

                BoiledRice boiledRice = new BoiledRice("Boiled Rice",30,25,false);
                HotChoco hotChoco = new HotChoco("Hot Choco",200,30,true);
                CoconutMilk coconutMilk = new CoconutMilk("Coconut Milk",220,15,false);

                cm.registerItemToDB(new Ingredient("Lanka",80,25,true)); // lanka
                cm.registerItemToDB(new Ingredient("Saging",89,18,true)); // saging
                cm.registerItemToDB(new Ingredient("Kamote",86,18,true)); // kamote
                cm.registerItemToDB(new Ingredient("Tuyo",371,25,true)); // tuyo
                cm.registerItemToDB(new Ingredient("Choco-Chips",470,25,true)); // chocochips
                cm.registerFluid(hotChoco);// isfluid

                // Register non-Sellables
                cm.registerItemToDB(new Ingredient("Sago",100,3,false));
                cm.registerFluid(coconutMilk); // isfluid
                cm.registerFluid(boiledRice); // isfluid

                // Found base Ingredients.
                ArrayList<Ingredient> base = new ArrayList<>();
                base.add(boiledRice);
                base.add(hotChoco);
                base.add(coconutMilk);

                //Register multiple sizes
                cm.registerCompound(new Champorado("Plain",base,50,15,35,0));
                cm.registerCompound(new Champorado("Regular",base,60,15,40,1));
                cm.registerCompound(new Champorado("Special",base,100,20,80,2));
                cm.registerCompound(new Champorado("Deluxe",base,140,25,120,3));

                // install other required parts of machine . . . .
                cm.installItemDispenser(new ItemDispenser());
                cm.installCoinBank(new CoinBank(coinDenominations));
                cm.installBillBank(new BillBank(billDenominations));
                cm.installCoinSlot(new CoinSlot( cm.getCoinBank()));
                cm.installCoinDispenser(new CoinDispenser( cm.getCoinBank()));
                cm.installBillSlot(new BillSlot( cm.getBillBank()));

                BoiledRiceContainer boiledRiceContainer = new BoiledRiceContainer("Boiled-Rice",boiledRice,1000);
                CoconutMilkContainer coconutMilkContainer = new CoconutMilkContainer("Coconut-Milk",coconutMilk,1000);
                HotChocoContainer hotChocoContainer = new HotChocoContainer("Hot-Choco",hotChoco,1000);

                cm.addToFluidContainerDB(boiledRiceContainer);
                cm.addToFluidContainerDB(coconutMilkContainer);
                cm.addToFluidContainerDB(hotChocoContainer);


                cm.installFluidContainers(boiledRiceContainer, coconutMilkContainer, hotChocoContainer,new FluidHeater("Heater"));

                cm.installFluidDispenser(new FluidDispenser(cm.getBoiledRiceContainer(),cm.getHotChocoContainer(),cm.getCoconutMilkContainer()));
                sMachineList.add(cm);
                showErrorMessage(cm.getName() + " has been created.");
                currentMachine = cm;
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            showErrorMessage("Input a number ");
            System.out.println("input a number");
            return false;
        }
        return false;
    }

    public ArrayList<RegularVM> getrMachineList(){
        return this.rMachineList;
    }

    public ArrayList<SpecialVM> getsMachineList(){
        return this.sMachineList;
    }


// -------------------------------- FEATURES OF MACHINE.



    public boolean registerItemToDatabase(String name, String priceInput, String caloriesInput){
        double price = 0;
        double calories = 0;

        try{
            price = Double.parseDouble(priceInput);
        }
        catch(Exception e){
            showErrorMessage("Input a number for price");
            System.out.println("Input a number");
            return false;
        }
        try{
            calories = Double.parseDouble(caloriesInput);
        }
        catch(Exception e){
            showErrorMessage("Input a number for the amount of calories");
            System.out.println("Input a number");
            return false;
        }

        if(currentMachine.createNewIngredient(name,price,calories,true)){
            return true;
        }
        return false;

    }

    public String[] getItemDetailsMaintenance(){
        ArrayList<Ingredient> registeredItems = currentMachine.getIngredientList();
        String[] list = new String[registeredItems.size()];
        int i = 0;

        for (Ingredient ing : registeredItems) {
            list[i] = i+1 + ". Name: " + ing.getName() + " Price: " + ing.getPrice() + " Calories: " + ing.getCalories() + " Is sellable: " + ing.getSellableStatus();
            i++;
        }
        return list;
    }

    public String[] getItemDetailsCustomer(){
        int[] ingredientStock = ((ChamporadoVM)currentMachine).determineIngredientStock();
        String[] list = new String[ingredientStock.length];
        for (int k = 0, l = 1; k < ingredientStock.length; k++) {


            list[k] = l + ". " + currentMachine.getIngredientList().get(k).getName() + " Calories: " + currentMachine.getIngredientList().get(k).getCalories() +
                    " Stock left: " + (ingredientStock[k] - ((ChamporadoVM)currentMachine).getItemReservation().get(k)
                    -((ChamporadoVM)currentMachine).getAddItemReservation().get(k));
            l++;
        }
        return list;

    }

    public String[] getSlotDetails(){
        ArrayList<Slot> registeredSlot = currentMachine.getSlotList();
        String[] list = new String[registeredSlot.size()];
        int i = 0;

        for(Slot slot : registeredSlot){
            list[i] = (i + 1) + ". " + slot.getItemName()+ " Stock " + slot.getCurrentCount() + "/" +
                    slot.getCapacity() + " Calories " +slot.getItemCalories()+" Price " + slot.getItemPrice() + " Sellable Status: " + slot.isItemSellable();
            i++;
        }
        return list;
    }

    public String[] getCoinDetailsCustomer(){
        ArrayList<Double> denominations = currentMachine.getCoinBank().getDenominations();
        String[] list = new String[denominations.size()+1];

        int i = 0;

        for(Double d : denominations){
            list[i+1] = (i +1 ) + ". " + d + " peso coin";
            i++;
        }
        list[0] = currentMachine.getSlotRequiredPayment();

        return list;

    }

    public String[] getBillDetailsCustomer(){
        ArrayList<Integer> denominations = currentMachine.getBillBank().getDenominations();
        String[] list = new String[denominations.size()+1];

        int i = 0;

        for(Integer d : denominations){
            list[i+1] = (i +1 ) + ". " + d + " peso bills";
            i++;
        }
        list[0] = currentMachine.getSlotRequiredPayment();

        return list;
    }

    public String[] getCoinDetailsMaintainance(){
        ArrayList<Double> denominations = currentMachine.getCoinBank().getDenominations();
        String[] list = new String[denominations.size()];

        int i = 0;
        for(Double d : denominations){
            list[i] = (i + 1) + ". " + d + " peso coin Amount: " + currentMachine.getCoinBank().getCount().get(i);
            i++;
        }
        return list;
    }

    public String[] getBillDetailsMaintainance(){
        ArrayList<Integer> denominations = currentMachine.getBillBank().getDenominations();
        String[] list = new String[denominations.size()];

        int i = 0;
        int count = currentMachine.getCoinBank().getDenominations().size();

        for(Integer d : denominations){
            list[i] = (count +1) + ". " + d + " peso bill Amount: " + currentMachine.getBillBank().getCount().get(i) ;
            i++;
            count++;
        }
        return list;
    }

    public String[] getSlotDetailsCustomer(){
        ArrayList<Slot> registeredSlot = currentMachine.getSlotList();
        String[] list = new String[registeredSlot.size()];

        int i = 0;
        for(Slot slot : currentMachine.getSlotList()) {
            if(slot.isItemSellable()){
                list[i] = (i + 1) + ". " + slot.getItemName()+ " Stock " + slot.getCurrentCount() + "/" +
                        slot.getCapacity() + " Calories " +slot.getItemCalories()+" Price " + slot.getItemPrice();
                i++;
            }
        }
        return list;
    }


    public String[] getFluidContainerDetails(){

        String[] list = new String[((ChamporadoVM)currentMachine).getFluidContainersDB().size()];

        int i = 0;

        for(FluidContainer fluidContainer : ((ChamporadoVM)currentMachine).getFluidContainersDB()){
            list[i] = (i+1) + ". " + fluidContainer.getName() + " Current Volume: " + fluidContainer.getCurrentVolume() + " / " + fluidContainer.getCapacity();
            i++;
        }
        return list;
    }

    public String[] getCompoundDetailsMaintenance(){
        String[] list = new String[((SpecialVM)currentMachine).getCompoundDatabase().size()];
        int i = 0;

        for(Compound compound : ((SpecialVM)currentMachine).getCompoundDatabase()){
            list[i] = (i+1) + ". " + compound.getName() + "Price: " + compound.getBasePrice() + " is overridden: " + compound.getIsPriceOverridden();
            i++;
        }
        return list;
    }

    public String[] getChamporadoDetailsCustomer(){
        int[] champoradoStock = ((ChamporadoVM)currentMachine).determineChamporadoStock();

        String[] list = new String[((SpecialVM)currentMachine).getCompoundDatabase().size()];
        int i = 1;



        for (Compound compound : ((SpecialVM)currentMachine).getCompoundDatabase()) {
            list[i-1] = i + ". " + compound.getName() + " Stock left: " + champoradoStock[i-1] + " Price: " + compound.getBasePrice() + " Calories: " + compound.getBaseCalories();
            i++;
        }
        return list;
    }

    public void registerFreeItem(int index){
        ((ChamporadoVM)currentMachine).addFreeItem(index);
    }

    public String getCurrentFreeSelectedIngredients(){
        int totalFreeItemReserved = 0;
        for(Integer integer : ((ChamporadoVM) currentMachine).getItemReservation()){
            System.out.println(integer);
            totalFreeItemReserved += integer;
        }

        String label = "Choose your free ingredient: " + totalFreeItemReserved + " / " +
                ((Champorado)((SpecialVM)currentMachine).getSelectedCompound()).getMaxFreeIngredients();
        return label;
    }

    public boolean checkCurrentFreeReservedItems(){
        int totalFreeItemReserved = 0;
        for(Integer integer : ((ChamporadoVM) currentMachine).getItemReservation()){
            totalFreeItemReserved += integer;
        }
        if(totalFreeItemReserved < ((Champorado)((SpecialVM)currentMachine).getSelectedCompound()).getMaxFreeIngredients()){
            return true;
        }
        return false;
    }


    public ArrayList<String> getCurrentChamporado(){
        return ((ChamporadoVM)currentMachine).getCurrentChamporadoDetails();
    }

    public boolean hasSelectedChamporado(){
        if(((ChamporadoVM)currentMachine).getCompoundSelectionIndex()==-1){
            return false;
        }
        return true;
    }

    public void clearChamporadoSelection(){
        ((ChamporadoVM)currentMachine).clearChamporadoSelection();
    }

    public boolean changeItemPrice(int index, String price){
        double priceSet;

        try{
            priceSet = Double.parseDouble(price);
        }
        catch(Exception e){
            showErrorMessage("Input a number for Price");
            System.out.println("Input a number");
            return false;
        }

        return currentMachine.setIngredientPrice(index + 1, priceSet);


    }

    public boolean changeItemCalories(int index, String calories){
        double caloriesSet;

        try{
            caloriesSet = Double.parseDouble(calories);
        }
        catch(Exception e){
            showErrorMessage("Set calories is not a number");
            System.out.println("Input a number");
            return false;
        }

        if(currentMachine.setIngredientCalories(index+1,caloriesSet)){
            return true;
        }
        return false;
    }

    public boolean setSlot(int itemIndex, int slotIndex){

        try{
            if(currentMachine.setSlotItem(slotIndex,currentMachine.getIngredientList().get(itemIndex))){
                return true;
            }
            else{
                SetSlotAddChoiceView setSlotAddChoiceView = new SetSlotAddChoiceView();
                if(setSlotAddChoiceView.getDecision()){
                    currentMachine.getSlotList().get(slotIndex).replaceItem(currentMachine.getIngredientList().get(itemIndex));
                    showErrorMessage("Successfully replaced the item.");
                    return true;
                }
                else{
                    showErrorMessage("Transaction cancelled");
                    return false;
                }
            }
        }

        catch (Exception e){
            showErrorMessage("Index/Slot selection is invalid.");
            return false;
        }
    }

    public boolean restockSlot(int slotIndex, String amount){

        int count = 0;

        try{
            count = Integer.parseInt(amount);
        }
        catch (Exception e){
            showErrorMessage("Input amount is not an integer");
            System.out.println("Not an integer");
            return false;
        }

        try{
            if(currentMachine.restockSlot(slotIndex,count)){
                return true;
            }
            return false;
        }
        catch (Exception e){
            showErrorMessage("Invalid slot selection");
            return false;
        }
    }

    public void viewSalesReport(){
        currentMachine.showSalesReport();
    }


    public void createHistory(){
        currentMachine.createHistory();
    }

    public void clearFreeAdditionList(){
        ((ChamporadoVM)currentMachine).clearFreeAdditionList();
    }

    public void clearPaidAdditionList(){
        ((ChamporadoVM)currentMachine).clearPaidAdditionList();
    }

    public void registerPaidItem(int index){
        ((ChamporadoVM)currentMachine).addPaidItem(index);
    }

    public boolean setSlotSelectionIndex(int index){

        if(currentMachine.selectSlot(index)){
            return true;
        }
        else{

            return false;
        }
    }


    public void addCoins(int index){

        try{
            currentMachine.getCoinSlot().addTemp(currentMachine.getCoinBank().getDenominations().get(index-1));
            System.out.println("Inserted " + currentMachine.getCoinBank().getDenominations().get(index-1) + " Peso Coins");
        }
        catch(Exception e){
            showErrorMessage("Select one of the options");
        }
    }

    public void addBills(int index){

        try{
            currentMachine.getBillSlot().addTemp(currentMachine.getBillBank().getDenominations().get(index-1));
            System.out.println("Inserted " + currentMachine.getBillBank().getDenominations().get(index-1) + " Peso Bills");
        }
        catch(Exception e){
            showErrorMessage("Select one of the options");
        }
    }

    public boolean isChamporadoMachine(){
        if(currentMachine instanceof ChamporadoVM){
            return true;
        }
        return false;
    }

    public void fillAllContainer(){
        ((ChamporadoVM)currentMachine).refillContainersToBrim();
        MessagePromptView successMessage = new MessagePromptView("All containers filled.");
    }

    public void resetCompoundPrice(int index){

        ((SpecialVM)currentMachine).resetCompoundPriceToDefault(index);

    }

    public boolean overrideCompoundPrice( int index,String amount){

        double amountSet;
        try{
            amountSet = Double.parseDouble(amount);
        }
        catch(Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Input a number for amount");
            return false;
        }
        ((SpecialVM)currentMachine).setOverrideCompoundPrice(index,amountSet);
        return true;
    }

    public void setItemSellableStatus(int index){
        try{
            if(currentMachine.getIngredientList().get(index).getSellableStatus()){
                currentMachine.getIngredientList().get(index).setSellable(false);
            }
            else{
                currentMachine.getIngredientList().get(index).setSellable(true);
            }
        }
        catch (Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Select an index");
        }

    }



    public boolean setContainerAmount(int indexInput, String amountInput){
        double amount;

        try{
            amount = Double.parseDouble(amountInput);
        }
        catch (Exception e){
            MessagePromptView errorMessage = new MessagePromptView("Input a number for amount");
            return false;
        }

        ((SpecialVM)currentMachine).setContainerAmount(indexInput,amount);
        return true;

    }

    public void dispenseItem(){
        currentMachine.dispenseItem();
    }

    public void showErrorMessage(String text){
        MessagePromptView messagePromptView = new MessagePromptView(text);
    }

    public void refundPayment(){
        currentMachine.refundInput();
    }

    public boolean setBank(String index, String amount){
        int indexSelection = 0;
        int amountSelection = 0;
        try{
            indexSelection = Integer.parseInt(index);
        }
        catch (Exception e){
            showErrorMessage("Input index is not an integer");
            return false;
        }

        try{
            amountSelection = Integer.parseInt(amount);
        }
        catch (Exception e){
            showErrorMessage("Input amount is not an integer");
            System.out.println("Non integer input");
            return false;
        }

        if(amountSelection < 0){
            showErrorMessage("Unable to set a negative amount");
            return false;
        }


        if(currentMachine.setBankAmount(indexSelection,amountSelection)){
            return true;
        }
        else{
            showErrorMessage("Invalid Selection");
            System.out.println("Invalid Selection.");
            return false;
        }
    }


    public boolean setChamporadoSelection(int index){
        return ((ChamporadoVM)currentMachine).selectChamporado(index);

    }




    public boolean isCurrentMachineNull(){
        if(currentMachine == null){
            return true;
        }
        return false;
    }







}
