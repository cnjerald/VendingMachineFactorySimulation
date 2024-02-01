/**
 * This class is a type of CurrencyDispenser that dispenses Double datatype denominations.
 *
 * The coin dispenser is responsible for reducing the amount of currency inside the bank, and also for
 * refunding the user input in the coinSlot.
 */

public class CoinDispenser implements CurrencyDispenser<Double> {

    private CoinBank coinBank;

    /**
     * This is the constructor for the coin dispenser, it requires a CoinBank as a parameter.
     * The coinBank here is where the CoinDispenser will get the denomination to dispense.
     * @param cb
     */
    public CoinDispenser(CoinBank cb) {
        this.coinBank = cb;
    }

    /**
     * This method dispenses a specific coin denomination from the coin bank.
     * @param coinDen - denomination to dispense
     */

    public void dispenseCount(Double coinDen) {
        System.out.println("Dispensing " + coinBank.getDenominations().get(coinBank.getDenominations().indexOf(coinDen)) + " Peso Coins");
        coinBank.decrementBank(coinDen);
    }

    /**
     * This method dispenses a "temp" coin, or the user inserted coin in the bank without completing a transaction.
     *
     * @param coinDen - denomination to dispense.
     */

    public void dispenseTemp(Double coinDen) {
        System.out.println("Dispensing " + coinBank.getDenominations().get(coinBank.getDenominations().indexOf(coinDen)) + " Peso Coins");
        coinBank.decrementTemp(coinDen);
    }

}
