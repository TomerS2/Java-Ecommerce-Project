import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Order implements Cloneable {


    private Cart cart;
    private LocalDate date;

    public Order() {
        date = LocalDate.now();
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", date=" + date +
                '}';
    }

    public int calculatePrice() {
        int total = 0;
        for (int i = 0; i < cart.getNumOfProducts(); i++) {
            total += cart.getProducts()[i].getProductprice();
        }
        return total;
    }
}
