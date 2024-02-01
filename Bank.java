import java.util.ArrayList;

/**
 * This class is an abstract class of a bank. The bank can be instantiated as any type of object
 * as its denomination.
 *
 * The purpose of the bank is to store denomination information, and to store the total count of each denomination.
 *
 * @param <T> - Data type of the denomniation.
 */




public abstract class Bank<T> {

    private ArrayList<T> denominations;
    private ArrayList<Integer> count;
    private ArrayList <Integer> countTemp;


    /**
     * This is a constructor for the Bank, it requires an array as a parameter. The contents of the array
     * are stored into the denomination Arraylist, and the count and count temp are set to 0.
     * @param denom
     */
    public Bank(T[] denom){
        denominations = new ArrayList<>();
        count = new ArrayList<>();
        countTemp = new ArrayList<>();

        for(int i = 0; i < denom.length; i++){
            this.denominations.add(denom[i]);
            this.count.add(0);
            this.countTemp.add(0);
        }
    }

    /**
     * This is a helper function to find which position is the given denomination in the list of denominations.
     *
     * @param denomination - The denomination being found
     * @return - The index where the denomination is in the arrayList of denominations.
     */

    private int getIndex(T denomination){
        for(int i = 0 ; i < denominations.size(); i++){
            if(denomination.equals(denominations.get(i))){
                return i;
            }
        }
        return -1;
    }

    /**
     * This increments the bank by reducing the "count" array index by 1.
     * This variable is where the number of Count with respect to
     * the index of the denomination in the denomination list.
     *
     * @param denomination - Denomination being incremented.
     */

    public void incrementBank(T denomination){
        if(getIndex(denomination) != -1){
            int index = getIndex(denomination);
            int count = this.count.get(index);
            count ++;
            this.count.set(index,count);
        }
    }

    /**
     * This method increments the "countTemp" array index by 1. The countTemp serves as a buffer on what the user has
     * inserted into the machine. The values of the count temp will be absorbed by the count if the transaction is successful.
     *
     * @param denomination - Denomination being incremented.
     */

    public void incrementTemp(T denomination){
        if(getIndex(denomination) != -1){
            int index = getIndex(denomination);
            int count = this.countTemp.get(index);
            count ++;
            this.countTemp.set(index,count);
        }
    }

    /**
     * This method decrements the bank given a certain denomination. It will return false given that the bank is
     * empty for the given denomination.
     *
     * @param denomination - denomination being dispensed
     * @return true if dispensed a denomination successfully, false if the bank is empty.
     */

    public boolean decrementBank(T denomination){
        if(count.get(getIndex(denomination)) > 0){

            int index = getIndex(denomination);
            int count = this.count.get(index);
            count --;
            this.count.set(index,count);
            return true;
        }
        return false;
    }

    /**
     * This decrements the inserted denomination by the customer. This is used when the customer wants to refund the
     * initial input to the machine without completing a transaction. It is also used when absorbing the temp value into the
     * actual count value.
     *
     * @param denomination - denomination in the countTemp array to be decremented.
     *
     * @return true if decrement is successful, false if the inserted denomination stored in the temporary bank is empty.
     */

    public boolean decrementTemp(T denomination){
        if(countTemp.get(getIndex(denomination))>0){
            int index = getIndex(denomination);
            int count = this.countTemp.get(index);
            count --;
            this.countTemp.set(index,count);
            return true;
        }
        return false;
    }

    /**
     * This method is a setter for the specific denomination, given a specific amount.
     *
     * @param denomination - denomination selected.
     * @param amount - amount to be set.
     */

    public void setBank(T denomination, int amount){
        if(getIndex(denomination)!= -1 && amount >= 0){
            int index = getIndex(denomination);
            int count = amount;
            this.count.set(index,count);
            if(denomination instanceof Double){
                System.out.println("Set: " + amount + " As the Amount of "+ denominations.get(denominations.indexOf(denomination)) + " Peso Coins");
            }
            else{
                System.out.println("Set: " + amount + " As the Amount of "+ denominations.get(denominations.indexOf(denomination)) + " Peso Bills");
            }
        }
        else{
            System.out.println("\nUnable to set a negative amount. \n");
        }
    }

    /**
     * This is a getter for the temp, or the total value that the customer has inserted into the machine.
     *
     * @return total value inserted into the machine.
     */

    public double getTempValue() {
        double total = 0;
        for(int i = 0; i < denominations.size(); i++) {

            total += ( (int) countTemp.get(i) * Double.parseDouble(denominations.get(i).toString()));
        }
        return total;
    }

    /**
     * This gets the total count for the denomination array for both the one inserted into the machine, and
     * one already in the machine.
     *
     * @return an array with the count of each denomination with respect to its index.
     */

    public int[] getTotalCount() {
        int[] total = new int[denominations.size()];

        for(int i = 0 ; i < denominations.size(); i++) {
            total[i] = countTemp.get(i) + count.get(i);
        }

        return total;
    }

    /**
     * A getter for the denominations
     *
     * @return denominations array.
     */


    public ArrayList<T> getDenominations(){
        return this.denominations;
    }

    /**
     * A getter for the count denominations inside bank.
     *
     * @return count array with values aligned to the denominations array.
     */

    public ArrayList<Integer> getCount(){
        return this.count;
    }

    /**
     * A getter for the count denominations inserted by the user.
     *
     * @return temp count array with values aligned to the denominations array.
     */

    public ArrayList<Integer> getCountTemp(){
        return this.countTemp;
    }





}
