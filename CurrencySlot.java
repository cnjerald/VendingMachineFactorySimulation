/**
 * The CurrencySlot is an abstract class where its purpose is to accept denominations inserted by the user.
 *
 * @param <T> - The denomination the slot accepts.
 */


public abstract class CurrencySlot<T>{
    protected Bank <T> bank;

    /**
     * This is the constructor for the CurrencySlot, it accepts a Bank with T datatype as its denomination.
     * @param bank - The bank of the vending machine
     */
    public CurrencySlot(Bank <T> bank) {
        this.bank = bank;
    }

    /**
     * This method increments the bank's temporary count of the given denomination.
     *
     * @param denom - The denomination to increment in the temp
     */
    public void addTemp(T denom) {
        this.bank.incrementTemp(denom);
    }

}
