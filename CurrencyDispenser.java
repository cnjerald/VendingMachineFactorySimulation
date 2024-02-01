/**
 * This interface contains two methods, which are dispenseCount and dispenseTemp which
 * is two of the required methods of the currencyDispensers.
 *
 * @param <T> - The datatype of the denomination.
 */


public interface CurrencyDispenser <T> {
    void dispenseCount(T denom);
    void dispenseTemp(T denom);
}
