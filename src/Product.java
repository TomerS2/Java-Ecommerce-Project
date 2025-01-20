public class Product {

    protected String Productname;
    protected int Productprice;
    protected Category category;
    protected static int nextSerialNumber = 1;
    protected int serialNumber;

    public Product(String productname, int productprice, Category category) {
        this.Productname = productname;
        this.Productprice = productprice;
        this.category = category;
        this.serialNumber = nextSerialNumber++;
    }


    public Product(Product other) {
        this.Productname = other.Productname;
        this.Productprice = other.Productprice;
        this.category = other.category;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public int getProductprice() {
        return Productprice;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + " serial Number=" + serialNumber +
                " ,Product name='" + Productname + '\'' +
                ", Product price=" + Productprice +
                ", category=" + category +
                '}';
    }
}
