/**
 * The CoinSlot is a type of currency slot that accepts double denominations.
 */

public class CoinSlot extends CurrencySlot<Double> {


    /**
     * The Constructor for the coin slot requires a Bank with double denominations.
     *
     * @param bank - A bank where the CoinSlot will add temp and count into.
     */
    CoinSlot(Bank<Double> bank){
        super(bank);
    }



}
