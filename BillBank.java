/**
 * The bill bank creates an instance of Bank with Integer set as its datatype for the denominations.
 */

public class BillBank extends  Bank<Integer> {
    public BillBank(Integer[] denomination){
        super(denomination);
    }
}
