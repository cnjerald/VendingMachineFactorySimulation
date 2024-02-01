/**
 * This class is a type of Bank that holds Double datatype denominations.
 *
 */

public class CoinBank extends Bank<Double>{

    /**
     * This is the constructor for the coin Bank.
     * It requires an array of double denominations to be innitialized.
     *
     * @param denomination - List of denominations in Double datatype.
     */
    public CoinBank(Double[] denomination){
        super(denomination);
    }
}
