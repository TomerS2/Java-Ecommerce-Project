import java.time.LocalDate;
import java.util.Arrays;

public class Customer extends User {


    private Address address;
    private Cart cart;
    private Order[] history;
    private int numOfOrders = 0;


    public Customer(Address address) {
        super();
        this.address = address;
        this.cart = new Cart();
        this.history = new Order[0];
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Cart getCart() {
        return cart;
    }

    public Order[] getHistory() {
        return history;
    }

    public void addToHistory(Order order) {
        validateHistorySize();

        this.history[numOfOrders] = order;
        numOfOrders++;
    }

    private void validateHistorySize() {
        if (numOfOrders < history.length) {
            return;
        }

        // double the size
        int newSize = history.length * 2;
        newSize = Math.max(2, newSize);
        Order[] temp = new Order[newSize];
        for (int i = 0; i < history.length; i++) {
            temp[i] = history[i];
        }
        history = temp;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Order closeOrder() {
        Order order = new Order();
        for (int i = 0; i < cart.getNumOfProducts(); i++) {
            Product product = cart.getProducts()[i];
            order.getCart().addProductToCart(product);
        }
        addToHistory(order);

        cart = new Cart();

        return order;
    }

    public void printHistory() {
        for (int i = 0; i < numOfOrders; i++) {
            System.out.println(1 + i + ") " + history[i]);
        }
    }

    public void createNewCart(int userSelection) throws CloneNotSupportedException {
        Order duplicatedOrder = (Order) history[userSelection - 1].clone();
        Cart duplicatedCart = duplicatedOrder.getCart();
        Cart previousCart = cart;
        this.setCart(duplicatedCart);
        combineCarts(duplicatedCart, previousCart);
        System.out.println("your cart has been created successfully.");
        System.out.println(this.getCart());
    }

    public Product[] combineCarts(Cart cart, Cart previousCart) {
        int newLength = cart.getProducts().length + previousCart.getProducts().length;

        Product[] result = new Product[newLength];

        System.arraycopy(cart.getProducts(), 0, result, 0, cart.getProducts().length);
        System.arraycopy(previousCart.getProducts(), 0, result, cart.getProducts().length, cart.getProducts().length);

        return result;
    }


    public boolean isEmpty(Cart cart) {
        if (this.getCart().getProducts() == null) {
            return true;
        }
        return false;
    }

    public void emptyCart() {
        this.cart = new Cart();
    }


    @Override
    public String toString() {
        return "Customer{" +
                "address=" + address +
                ", cart=" + cart +
                ", history=" + Arrays.toString(history) +
                ", numOfOrders=" + numOfOrders +
                '}';
    }
}







