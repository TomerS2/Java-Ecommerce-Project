import java.util.Arrays;

public class Cart {

    private Product[] products;
    private int numOfProducts;

    public Cart() {
        this.products = new Product[1];
        this.numOfProducts = 0;
    }

    public Cart(Cart other){
        this.products = new Product[other.getNumOfProducts()];
        this.numOfProducts = other.getNumOfProducts();
    }

    public Product[] getProducts() {
        return products;
    }

    public void addProductToCart(Product product) {
        validateCustomerCartSize();

        this.products[numOfProducts] = product;
        numOfProducts++;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void validateCustomerCartSize() {
        if (numOfProducts < products.length) {
            return;
        }

        // double the size
        int newSize = products.length * 2;
        newSize = Math.max(2, newSize);
        Product[] temp = new Product[newSize];
        for (int i = 0; i < products.length; i++) {
            temp[i] = products[i];
        }
        products = temp;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + Arrays.toString(products) +
                ", num of products=" + numOfProducts +
                '}';
    }

    public void setProducts(Product[] p) {
        this.products = p;
    }
}
// X