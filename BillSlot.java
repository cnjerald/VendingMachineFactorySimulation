/**
 * The bill slot is where the customer inserts the bill. This is also used by the machine to dispense the bill change.
 *
 * The bill slot is a type of currency slot that accepts integer denominations. It also implements methods from
 * the currencyDispenser as it can also dispense coins in the slot.
 */

public class BillSlot extends CurrencySlot <Integer> implements CurrencyDispenser <Integer> {

    /**
     * This is the constructor for the bill slot. It requires an BillBank object as a parameter.
     *
     * @param bank - The bank connected to the billslot.
     */
    BillSlot(BillBank bank){
        super(bank);
    }

    /**
     * This method dispenses the count (Bank Stored Money) stored in the machine.
     *
     * @param denom - denomination to dispense.
     */

    public void dispenseCount(Integer denom){
        if(super.bank.decrementBank(denom)){
            System.out.println("Dispensing " + super.bank.getDenominations().get(bank.getDenominations().indexOf(denom)) + " Peso Bill");
        }
    }

    /**
     * This method dispenses the temp (User inserted money) stored in the machine.
     *
     * @param denom - denomination to dispense.
     */

    public void dispenseTemp(Integer denom){
        if(super.bank.decrementTemp(denom)){
            System.out.println("Dispensing " + super.bank.getDenominations().get(bank.getDenominations().indexOf(denom)) + " Peso Bill");
        }
    }

}
