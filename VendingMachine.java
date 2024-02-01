import View.MessagePromptView;

import java.util.ArrayList;

/**
 * The VendingMachine is an abstract class that has the basic functions of a vending machine.
 */
public abstract class VendingMachine {

    private String name;
    protected Slot slotSel = null;
    private ArrayList<Slot> slotList;
    private ArrayList<Ingredient> ingredientList;
    private int[] initialInventory;
    private CoinBank coinBank;
    private CoinSlot coinSlot;
    private BillBank billBank;
    private BillSlot billSlot;
    private ItemDispenser itemDispenser;
    private CoinDispenser coinDispenser;

    /**
     * This is a constructor for a vending machine it requires a name, number of slots, and capacity.
     *
     * @param name - Name of the vending machine.
     * @param slotCount - The number of slots this machine has.
     * @param capacityCount - The capacity of each slot of this machine.
     */

    VendingMachine(String name,int slotCount, int capacityCount){
        this.name = name;
        slotList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        createSlot(slotCount);
        setSlotCapacity(capacityCount);
    }

    /**
     * This method creates n number of slots. If the slots is less than 8,
     * then it will not create and will return false, else true.
     *
     * @param n - number of slots
     * @return true if successful, false if not.
     */

    private boolean createSlot(int n) {
        if(n >= 8)
        {
            for(int i = 0; i < n; i++)
            {
                Slot s = new Slot();
                slotList.add(s);
            }
            System.out.println("Amount of slots registered to: " + n);
            return true;
        }
        System.out.println("Input a valid amount of slots (Minimum of 8)");
        return false;
    }

    /**
     * This method sets the capacity of each slot. The minimum capacity is set to 10.
     * The capacity of each slot is uniform for every slot.
     *
     * @param n - capacity of each slot.
     * @return true if n >=10, else return false
     */

    private boolean setSlotCapacity(int n){
        if(n >= 10){
            for(Slot s : slotList){
                s.setCapacity(n);
            }
            System.out.println("Capacity of slots registered to: " + n);
            return true;
        }
            System.out.println("Input a valid capacity (Minimum of 10)");
            return false;
    }

    /**
     * This method creates a new ingredient, and registers it into the database.
     * An ingredient requires a (1) name, (2) price, (3) calories, and (4) an isSellable status.
     * This method will not create a new ingredient if any of the price or calories is negative.
     * Furthermore, it will not create a new ingredient if there is a name duplicate in the database.
     *
     * @param name - Name of the ingredient
     * @param price - Price of the ingredient
     * @param calories - Calories of the ingredient
     * @param isSellable - Sellable status
     * @return true if creation is successful, false if not.
     */
    public boolean createNewIngredient(String name, double price, double calories, boolean isSellable){
        Ingredient newIngredient = new Ingredient(name);

        if(price >= 0){
            if(calories >= 0){
                if(registerItemToDB(newIngredient)){
                    newIngredient.setPrice(price);
                    newIngredient.setCalories(calories);
                    newIngredient.setSellable(isSellable);
                    return true;
                }
            }
            else{
                MessagePromptView errorMessage = new MessagePromptView("Unable to set a negative value for calories");
            }
        }
        else{
            MessagePromptView errorMessage = new MessagePromptView("Unable to set a negative value for price");
        }
        return false;
    }

    /**
     * This method registers a created ingredient into the database. It will return false if there
     * is an ingredient in the database that has a similar name with the to-be-registered ingredient.
     *
     * @param ingredient - Ingredient object to be registered in the database
     * @return true if registration is successful, else return false.
     */

    public boolean registerItemToDB(Ingredient ingredient){
        for(Ingredient i : ingredientList){
            if (i.getName().equals(ingredient.getName())){
                MessagePromptView errorMessage = new MessagePromptView("Duplicate item names detected");
                System.out.println("Duplicate item names detected.");
                return false;
            }
        }
        this.ingredientList.add(ingredient);
        System.out.println("Successfully registered Item name: " + ingredient.getName());
        return true;
    }

    /**
     * This method sets an item into a specific slot it returns true if the item is successfully set,
     * and returns false if there is already an item in the selected slot.
     *
     * @param slotIndex - index of the selected slot in the slotList.
     * @param ingredient - the ingredient to be placed in the slot.
     * @return true if item is set on the slot, false if not.
     */

    public boolean setSlotItem(int slotIndex, Ingredient ingredient){
        if(this.getSlotList().get(slotIndex).stockItem(ingredient)){
            return true;
        }
        return false;
    }

    /**
     * This method increases the number of ingredient stored in the slot. It returns true if the restocking
     * amount is less than or equal to the capacity of the slot, if the number to be restocked
     * is greater than 0, and if there is an ingredient in the selected slot. Otherwise, it will return false.
     *
     * @param slotSelectionIndex - slot to be restocked
     * @param count - number of stocks to increase
     * @return - true if restocking is successful, false otherwise.
     */

    public boolean restockSlot(int slotSelectionIndex, int count){

        //Check if the slot is empty.
        if(slotList.get(slotSelectionIndex).isEmpty()){
            MessagePromptView errorMessage = new MessagePromptView("Selected Slot Empty");
            System.out.println("Selected Slot Empty");
            return false;
        }
        //check if the count is greater than 0.
        if(count <= 0) {
            MessagePromptView errorMessage = new MessagePromptView("Input value is negative");
            System.out.println("Input value is negative");
            return false;
        }


        try {
            // get the item in the slot
            Ingredient ingredient = slotList.get(slotSelectionIndex).getItem();
            //Check if there will be overflow
            if(!slotList.get(slotSelectionIndex).addStock(count, ingredient)){
                MessagePromptView errorMessage = new MessagePromptView("Unable to add as it will exceed capacty");
                System.out.println("Unable to add as it will exceed capacty");
            }
            else{
                //if no overflow, then it means addStock() method was successful in adding the number of stocks.
                System.out.println("Successfully added " + count + " number of stocks");
                return true;
            }
        }
        catch (Exception e) {// Catches invalid slot selection
            System.out.println("Invalid slot selection");
        }
        return false;
    }

    /**
     * This method sets the total number of a certain "Denomination" in the bank into a specified amount.
     * It will return false if the amount is a negative value, or if the denomination selection index is invalid.
     *
     * @param denominationSelection - index of the denomination
     * @param amountSelection - Amount to be set
     *
     * @return true if successfully set, false otherwise.
     */

    public boolean setBankAmount(int denominationSelection, int amountSelection){

        // If index is below the size of the total denominations of the coin bank then
        if(denominationSelection <= coinBank.getDenominations().size() && denominationSelection > 0)
        {
            coinBank.setBank(coinBank.getDenominations().get(denominationSelection-1), amountSelection);
        }
        // if the index is greater than the size of the coin bank, but is less than the total index of the coin bank
        // added to the bill bank then set the selected index minus the size of the coin denomination's size
        else if(denominationSelection >= coinBank.getDenominations().size()
                && denominationSelection <= coinBank.getDenominations().size()+ billBank.getDenominations().size()) {
            billBank.setBank(billBank.getDenominations().get(denominationSelection-coinBank.getDenominations().size()-1), amountSelection);
        }
        else {
            //This indicates that the selected index is negative, or if the selected index does not exist.
            System.out.println("\nInvalid Selection \n");
            return false;
        }
        return true;
    }

    /**
     * This method allows the user to select a slot that he/she plans to purchase.
     * This will return true if the slotindex is valid, false otherwise.
     * If a slot selection is successful, the slotSel global variable is set to the selected slot.
     *
     * @param selection - index selected by the user.
     * @return true if slot selection successful, false otherwise.
     */

    public boolean selectSlot(int selection){

        // I will terminate instantly if the index is greater than the available choices, or if the selection is negative.
        if(selection > this.slotList.size() || selection < 0){
            MessagePromptView errorMessage = new MessagePromptView("Invalid slot input");
            return false;
        }

        // I will iterate each slot, checking if the item set on the slot is sellable and not empty.
        // with this, the count adjusts with the index, as the selection choices of non-sellable items are not shown
        // to the customer which could lead to an invalid index selection.
        int i = 0;
        for(Slot slot : getSlotList()){
            if(slot.isItemSellable()){
                if(i == selection){
                    if(!slot.getItemName().equals("Empty")){
                        MessagePromptView successMessage = new MessagePromptView("Selected: " + slot.getItemName());
                        System.out.println("Selected " + slot.getItemName());
                        this.slotSel = slot;
                        return true;
                    }
                    else{
                        MessagePromptView errorMessage = new MessagePromptView("Unable to select an empty slot.");
                        System.out.println("Unable to select an empty slot.");
                    }
                }
                i++;
            }
        }
        return false;
    }

    /**
     * This changes the price of a specific ingredient. It will return true if the price is a positive number
     * and if the index selection is valid.
     *
     * @param itemSelectionIndex - The index of the ingredient in the database
     * @param price - the price to be set
     * @return true if price change is successful, false if not.
     */

    public boolean setIngredientPrice(int itemSelectionIndex, double price){
        if(price >= 0){
            try{
                ingredientList.get(itemSelectionIndex-1).setPrice(price);
                return true;
            }
            catch (Exception e){
                MessagePromptView errorMessage = new MessagePromptView("Invalid slot input");
                return false;
            }
        }
        else{
            MessagePromptView errorMessage = new MessagePromptView("Unable to set a negative value for price");
        }
        return false;
    }

    /**
     This changes the calories of a specific ingredient. It will return true if the price is a positive number
     * and if the index selection is valid.
     *
     * @param itemSelectionIndex - The index of the ingredient in the database
     * @param calories - the calories to be set
     * @return true if calorie change is successful, false if not.
     */

    public boolean setIngredientCalories(int itemSelectionIndex, double calories){
        if(calories >= 0 ){
            try{
                ingredientList.get(itemSelectionIndex-1).setCalories(calories);
                return true;
            }
            catch (Exception e){
                MessagePromptView errorMessage = new MessagePromptView("Invalid slot input");
                return false;
            }
        }
        else{
            MessagePromptView errorMessage = new MessagePromptView("Unable to set a negative value for price");
        }
        return false;
    }

    /**
     * This method creates a history of the initial slot inventory record.
     * It stores in the initialInventory Array, where each index is the position of the slot in the slotList, and
     * each number inside this array is the initial inventory count.
     * It is used to get the sales summary in the future.
     */

    public void createHistory(){
        for(Slot slot : getSlotList()){
            slot.setRecord();
        }

        this.initialInventory = new int[slotList.size()];

        for(int i = 0; i < getSlotList().size(); i++) {
            this.initialInventory[i] = getSlotList().get(i).getCurrentCount();
        }
    }

    /**
     * This method is used to get how much money the user needs to insert into the machine.
     * If the user did not select an item then it will return how much money is inserted without the needed amount.
     *
     * @return - A string that contains how much money the user inserted into the machine, and if possible,
     * how much money does the user need to insert to purchase the item.
     */

    public String getSlotRequiredPayment(){
        try{ // this will run if I selected a slot.
            System.out.println("Current money inserted: " + getTotalTempValue() + " Item: " + slotSel.getItem().getName() + " Money needed " + slotSel.getSlotPrice());
            return "Current money inserted: " + getTotalTempValue() + " Item: " + slotSel.getItem().getName() + " Money needed " + slotSel.getItem().getPrice();
        }
        catch (Exception e){ // otherwise I run this meaning I did not select a slot.
            return "Current money inserted: " + getTotalTempValue();
        }
    }

    /**
     * This method prints out the sales report.
     */

    public void showSalesReport(){
        String message = "";

        if(initialInventory != null){ // this is a checker to test the first access of the user in the maintainance mode, as there is no inventory record yet.
            int i = 1;
            message += "============================== Summary of Sales ==============================\n";
            System.out.println("============================== Summary of Sales ==============================");
            for (Slot slot : slotList) {
                if (!slot.getSlotName().equals("Empty")) {
                    //This uses a special variable in slot called getSlotName(), and getSlotPrice(), as the slot is now empty
                    // and object-oriented principles converts the name back to "Empty" and price to 0, a separate record is formed in the slot
                    // that holds the name and price of the item.
                    message += "Item Name: " + slot.getSlotName() + " Initial Inventory " + initialInventory[i-1] +
                            " Final Inventory " + slot.getCurrentCount() + " Quantity Sold: " +
                            (initialInventory[i-1] - slot.getCurrentCount() + "Price: " + (slot.getSlotPrice()) + "\n");

                    System.out.println(i + ". Item Name: " + slot.getSlotName() + " \tInitial Inventory " + initialInventory[i-1] +
                            " \tFinal Inventory " + slot.getCurrentCount() + " \tQuantity Sold: " + (initialInventory[i-1] - slot.getCurrentCount()));
                }
                i++;
            }
            // The total sales method is used here to determine the total sales which will be discussed later.
            message += "============================== Total Sales: " + getTotalSales()+ " Pesos\n";
            System.out.println("============================== Total Sales: " + getTotalSales()+ " Pesos");

            // the record of the (slotName, and slotPrice is reset for reusabillity)
            for(Slot slot : slotList){
                slot.resetRecord();
            }

            MessagePromptView messagePromptView = new MessagePromptView(message);

        }
    }

    /**
     * This method dispenses the selected slot given that
     * (1). The user has selected a slot
     * (2). This slot is not empty
     * (3). The user has inserted enough money for the selected slot
     * (4). If the machine can give a change
     *
     * All These conditions must be met for the machine to dispense the item.
     *
     * @return true if the machine is able to dispense the item, false otherwise.
     */

    public boolean dispenseItem(){
        try{
            if(!slotSel.isEmpty() && slotSel.getCurrentCount() > 0) // if selected slot is not empty
            {
                if(isTempEnough(slotSel.getItemPrice())) { // if inserted money is sufficient
                    if(canGiveChange(slotSel.getItemPrice())){ // if this machine can give change

                        System.out.println("Dispensing Item...");
                        itemDispenser.dispenseItem(slotSel); // will destroy item on slot.
                        MessagePromptView successMessage = new MessagePromptView("Successfully Dispensed item: " + itemDispenser.getDispensedItem().getName() + " \n Please get your change.");
                        System.out.println("Successfully Dispensed item: " + itemDispenser.getDispensedItem().getName());
                        giveChange(slotSel.getSlotPrice());

                    }
                    else {
                        MessagePromptView errorMessage = new MessagePromptView("Machine is unable to provide change, Transaction cancelled.");
                        System.out.println("Machine is unable to provide change, Transaction cancelled.");
                    }
                    slotSel = null;
                    return true;
                }
                else {
                    MessagePromptView errorMessage = new MessagePromptView("Insufficient Balance to Complete Transaction.");
                    System.out.println("Insufficient Balance to Complete Transaction.");
                }
            }
            else{
                MessagePromptView errorMessage = new MessagePromptView("This slot is empty unable to dispense.");
                System.out.println("This slot is empty unable to dispense.");
            }
        }
        catch(NullPointerException e){
            MessagePromptView errorMessage = new MessagePromptView("Select a slot first.");
            System.out.println("Select a slot first.");
        }
        return false;
    }

    /**
     * This method returns the initial inventory record of this machine.
     *
     * @return int[] initialInventory - Record of stock of each slot.
     */

    public int[] getInitialInventory(){
        return this.initialInventory;
    }

    /**
     * This method is a helper method that simply subtracts the initial inventory array
     * to the current inventory array. With this the total number of items sold is calculated.
     *
     * @return int[] total number of sold items per slot.
     */

    private int[] getItemSoldQuantity() {
        int[] quantitySold = new int[slotList.size()];

        for (int i = 0; i < slotList.size(); i++) {
            quantitySold[i] = initialInventory[i] - slotList.get(i).getCurrentCount();
        }

        return quantitySold;
    }

    /**
     * This method calculates the total sales of the machine, it calculated by multiplying the item sold quantity
     * to the price of the item.
     *
     * @return totalSales- The total sales made by the machine.
     */

    public double getTotalSales() {

        double totalSales = 0;

        for(int i = 0; i < slotList.size();i++) {
            System.out.println("Debug Price: " + getSlotList().get(i).getSlotPrice());
            totalSales +=  (getItemSoldQuantity()[i] * getSlotList().get(i).getSlotPrice());
        }

        return totalSales;
    }

    /**
     * This method checks if the user has inserted enough money in the machine as compared to the given cost.
     *
     * @param cost - Cost of the item to be compared to.
     *
     * @return true if the inserted money is greater than the cost, false otherwise.
     */

    public boolean isTempEnough(double cost) {
        if(getTotalTempValue() >= cost) {
            return true;
        }
        return false;
    }

    /**
     * This method returns all the money inserted by the customer to the machine.
     *
     * @return true if there is money inside the machine, false otherwise.
     */

    public boolean refundInput(){
        System.out.println("Refunding Money...");

        String output ="";

        // This checks if there is a coin inserted, if yes then it will execute the function below.
        if(getTotalTempValue()!=0){
            boolean willExecute = false;
            for (int i = 0; i < coinBank.getDenominations().size(); i++) {
                if (coinBank.getCountTemp().get(i) > 0) {
                    willExecute = true;
                    break;
                }
            }
            // This dispenses the coins using the coin dispenser, and will keep dispensing until all input of the user is
            // dispensed.
            if (willExecute) {
                output += "Dispensed coins\n";
                System.out.println("\nDispensing Coins: ");
                for (int i = 0; i < coinBank.getDenominations().size(); i++) {
                    while (coinBank.getCountTemp().get(i) > 0) {
                        coinDispenser.dispenseTemp(coinBank.getDenominations().get(i));
                        output+= "Dispensed " + coinBank.getDenominations().get(i) + " peso coin\n";
                    }
                }
            }
        //    This works similar to how the coin is dispensed.

            willExecute = false;
            for (int i = 0; i < billBank.getDenominations().size(); i++) {
                if (billBank.getCountTemp().get(i) > 0) {
                    willExecute = true;
                    break;
                }
            }

            if (willExecute) {
                output += "Dispensed bills\n";
                System.out.println("\nDispensing Bills: ");
                for (int i = 0; i < billBank.getDenominations().size(); i++) {
                    while (billBank.getCountTemp().get(i) > 0) {
                        billSlot.dispenseTemp(billBank.getDenominations().get(i));
                        output += "Dispensed " + billBank.getDenominations().get(i) + " peso bill\n";
                    }
                }
            }
            System.out.println("\nRefund Complete\n");
            output += "Refund Complete\n";
            MessagePromptView messagePromptView = new MessagePromptView(output);
            return true;
        }

        MessagePromptView messagePromptView = new MessagePromptView("No money inserted in slot");
        return false;
    }


    /**
     * This method determines if the machine can give change to the user.
     * It returns true if it is able to, otherwise false.
     *
     * @param price - price of the item compared to the inserted amount and money in machine.
     *
     * @return true if able to give change, false otherwise.
     */

    public boolean canGiveChange(double price) { // note on this function, it might still cause some arithmetic errors due to operation precision.
        double itemPriceDouble = price;
        // This is the required change, which is the total inserted money of the user subtracted to the price of the item.
        double requiredChange = getTotalTempValue() - itemPriceDouble;
        int[] countBill = billBank.getTotalCount();
        int[] countCoin = coinBank.getTotalCount();

        // This will keep reducing the required change, based on the amount of money in the bank. How it works is
        // It will start with the largest denomination, then check if how many is in the bank, and decrements the count
        // if the count of that denomination is 0, then it moves to the next highest denomination.

        for (int i = countBill.length - 1; i >= 0; i--) {
            while (requiredChange >= billBank.getDenominations().get(i) && countBill[i] > 0) {
                requiredChange -= billBank.getDenominations().get(i);
                countBill[i]--;
            }
        }
        // This has the same implementation above, but now checks for the coins.
        for (int i = countCoin.length - 1; i >= 0; i--) {
            while (requiredChange >= coinBank.getDenominations().get(i) && countCoin[i] > 0) {
                requiredChange -= coinBank.getDenominations().get(i);
                countCoin[i]--;
            }
        }
        // Since there is a possible tolerance issue, instead of comparing the required change to 0, it is compared to
        // a tolerance value of 0.01. As there is a
        // constraint by the computer architecture, and a tolerance is made
        // to account minor differences in the computations.

        double epsilon = 1e-2; // Set an epsilon value for tolerance
        if (requiredChange > epsilon) {
            return false; // indicating I can't give change.
        }
        return true; // can give change.
    }

    /**
     * This method is where the machine dispenses the change of the customer.
     * In the implementation of this in the dispenseItem() method, it is ensured that it will only
     * run if the machine is able to give change. Which is the reason it returns void.
     *
     * @param price - price of the item
     */

    public void giveChange(double price) {

        double itemPriceDouble = price;
        double requiredChange = getTotalTempValue()- itemPriceDouble;
        int[] countBill = billBank.getTotalCount();
        int[] countCoin = coinBank.getTotalCount();

        int[] changeBill = new int[billBank.getDenominations().size()];
        int[] changeCoin = new int[coinBank.getDenominations().size()];

        /* The inserted money of the user is now deposited into the entire pot of the bank. This ensures that
           even if the user inserts a lot of coins and bills that exceeds the price of the item, it will be able to give
           a change as long as the exact value is inserted first.
        */
        for(int i = 0; i < coinBank.getDenominations().size();i++) {
            while(this.coinBank.getCountTemp().get(i) > 0) {
                this.coinBank.incrementBank(coinBank.getDenominations().get(i));
                this.coinBank.decrementTemp(coinBank.getDenominations().get(i)); // reduce temp.
            }
        }
        for(int i = 0; i < billBank.getDenominations().size();i++) {
            while(this.billBank.getCountTemp().get(i) > 0) {
                this.billBank.incrementBank(billBank.getDenominations().get(i));
                this.billBank.decrementTemp(billBank.getDenominations().get(i));
            }
        }

        // Start allocating change for each denomination, by decreasing the bank, and increasing the countDenominations rray.
        for(int i = countBill.length - 1; i >= 0; i--) {
            while(requiredChange >= billBank.getDenominations().get(i) && countBill[i] > 0) {
                requiredChange -= billBank.getDenominations().get(i);
                countBill[i]--;
                changeBill[i]++;
            }
        }

        for(int i = countCoin.length - 1; i >= 0; i--) {
            while(requiredChange >= coinBank.getDenominations().get(i) && countCoin[i] > 0) {
                requiredChange -= coinBank.getDenominations().get(i);
                countCoin[i]--;
                changeCoin[i]++;
            }
        }

        // Now start dispensing the currency as long as the Start dispensing the allocated denominations is greater than 0
        // It moves to the next index if the allocation is 0.
        boolean willExecute = false;
        for (int i = 0; i < changeCoin.length; i++) {
            if (changeCoin[i] > 0) {
                willExecute = true;
                break;
            }
        }

        if (willExecute) {
            System.out.println("Dispensing Change (Coin): ");
            for (int i = 0; i < changeCoin.length; i++) {
                while (changeCoin[i] > 0) {
                    coinDispenser.dispenseCount(getCoinBank().getDenominations().get(i));
                    changeCoin[i]--;
                }
            }
        }

        willExecute = false;
        for (int i = 0; i < changeBill.length; i++) {
            if (changeBill[i] > 0) {
                willExecute = true;
                break;
            }
        }

        if (willExecute) {
            System.out.println("Dispensing Change (Bills): ");
            for (int i = 0; i < changeBill.length; i++) {
                while (changeBill[i] > 0) {
                    billSlot.dispenseCount(getBillBank().getDenominations().get(i));
                    changeBill[i]--;
                }
            }
        }
    }

    /**
     * This is a installation method of the coinBank.
     * @param cb - CoinBank Object
     */

    public void installCoinBank(CoinBank cb){
        System.out.println("Installing coin bank...");
        this.coinBank = cb;
    }

    /**
     * This is an installation method of the bilLBank.
     * @param bb - BillBank Object
     */

    public void installBillBank(BillBank bb){
        System.out.println("Installing bill bank...");
        this.billBank = bb;
    }

    /**
     * This is an installation method of the item dispenser.
     *
     * @param id - Item Dispenser Object.
     */
    public void installItemDispenser(ItemDispenser id){
        System.out.println("Installing item dispenser...");
        this.itemDispenser = id;
    }

    /**
     * This is an installation method of the coin slot.
     *
     * @param cs - CoinSlot object.
     */

    public void installCoinSlot(CoinSlot cs){
        System.out.println("Installing coin slot...");
        this.coinSlot = cs;
    }

    /**
     * This is an installation method of the billSlot.
     *
     * @param bs - BillSlot object.
     */

    public void installBillSlot(BillSlot bs){
        System.out.println("Installing bill slot...");
        this.billSlot = bs;
    }

    /**
     * This is an installation method of the coinDispenser.
     *
     * @param cd - coinDispenser object.
     */

    public void installCoinDispenser(CoinDispenser cd){
        System.out.println("Installing coin dispenser...");
        this.coinDispenser = cd;
    }

    /**
     * This method returns the totalTempValue, which is the total number of money inserted by the customer into
     * the machine.
     *
     * @return total value inserted into both coin and bill slot.
     */

    public double getTotalTempValue() {
        return this.getCoinBank().getTempValue() + this.getBillBank().getTempValue();
    }

    /**
     * This method returns the slot list.
     *
     * @return slotList of the machine
     */
    public ArrayList<Slot> getSlotList(){
        return this.slotList;
    }

    /**
     * This is the getter for the coinBank
     *
     * @return coinBank of the machine.
     */
    public CoinBank getCoinBank(){
        return this.coinBank;
    }

    /**
     * This is the getter for the billBank
     *
     * @return billBank of the machine.
     */

    public BillBank getBillBank(){
        return this.billBank;
    }

    /**
     * This is the getter for the billSlot
     *
     * @return billSlot of the machine.
     */

    public BillSlot getBillSlot(){
        return this.billSlot;
    }

    /**
     * This is the getter for the name of the machine
     *
     * @return name of the machine
     */


    public String getName(){
        return this.name;
    }

    /**
     * This is the getter for the coin slot
     *
     * @return coin slot of the machine.
     */


    public CoinSlot getCoinSlot(){return this.coinSlot;}

    /**
     * This is the getter for the ingredient list.
     *
     * @return ingredientList of the machine
     */


    public ArrayList<Ingredient> getIngredientList(){
        return this.ingredientList;
    }

    /**
     * This is the getter for the itemDispenser
     *
     * @return itemDispenser of the Machine
     */

    public ItemDispenser getItemDispenser() {
        return itemDispenser;
    }

}
