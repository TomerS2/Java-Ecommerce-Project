import java.util.Comparator;

public class CompareSellersByNumOfProducts implements Comparator<Seller> {


    @Override
    public int compare(Seller o1, Seller o2) {
        if (o1.getNumOfProducts() <= o2.getNumOfProducts()) {
            return -1;
        } else if (o1.getNumOfProducts() > o2.getNumOfProducts()) {
            return 1;
        } else {
            return 0;
        }
    }
}
