import java.util.Arrays;

public class Seller extends CompareSellersByNumOfProducts {

    private User user;
    private Product[] sellerProducts;
    private int numOfProducts;


    public Seller(User user) {
        this.user = user;
        sellerProducts = new Product[0];
    }


    public Product[] getSellerProducts() {
        return sellerProducts;
    }


    public void addProductForSeller(Product p) {
        validateSellerOfProductsArraySize();

        if (numOfProducts < sellerProducts.length) {
            sellerProducts[numOfProducts] = p;
        }

    }

    private void validateSellerOfProductsArraySize() {
        if (numOfProducts < sellerProducts.length) {
            return;
        }

        // double the size
        int newSize = sellerProducts.length * 2;
        newSize = Math.max(2, newSize);
        Product[] temp = new Product[newSize];
        for (int i = 0; i < sellerProducts.length; i++) {
            temp[i] = sellerProducts[i];
        }
        sellerProducts = temp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }


    @Override
    public String toString() {
        return "Seller{" +
                "user=" + user +
                ", sellerProducts=" + Arrays.toString(sellerProducts) +
                '}';
    }
}
// V