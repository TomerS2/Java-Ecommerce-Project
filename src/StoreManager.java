import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StoreManager {

    Scanner sc = new Scanner(System.in);
    private Seller[] all_sellers;
    private Customer[] all_customers;
    private int sellerArraySize = 0;
    private int customerArraySize = 0;

    public StoreManager() {
        all_sellers = new Seller[0];
        all_customers = new Customer[0];
    }

    //##########################################Start#####################################################################
    public static void Start() {

        Scanner sc = new Scanner(System.in);

        final int exit = 0;
        StoreManager sm = new StoreManager();
        int choice;
        do {
            System.out.println();
            System.out.println("Welcome Menu");
            System.out.println();
            System.out.println("1.Add a seller");
            System.out.println("2.Add a customer");
            System.out.println("3.Add a product to a specific seller");
            System.out.println("4.Add a product to a specific customer");
            System.out.println("5.Checkout");
            System.out.println("6.show all sellers");
            System.out.println("7.show all customers");
            System.out.println("8.show all Products By Category");
            System.out.println("9.Duplicate cart from my history");
            System.out.println("0.Exit");
            System.out.println();
            System.out.println("Enter your choice number: ");
            choice = sc.nextInt();


            switch (choice) {
                case 1:
                    sm.addSeller();
                    break;
                case 2:
                    sm.addCustomer();
                    break;
                case 3:
                    sm.addProductForSeller();
                    break;
                case 4:
                    sm.addProductForCustomer();
                    break;
                case 5:
                    sm.orderPayment();
                    break;
                case 6:
                    sm.showAllSellers();
                    break;
                case 7:
                    sm.showAllCustomers();
                    break;
                case 8:
                    sm.showAllProductsByCategory();
                    break;
                case 9:
                    sm.copyCartFromHistory();
                    break;


            }


        } while (choice != exit);

        System.out.println("Goodbye!!");


    }

    //##########################################section1#####################################################################
    public void addSeller() {
        validateSellerArraySize();
        System.out.println("Enter Seller's username: ");
        String username = sc.nextLine();
        System.out.println("Enter Seller's password: ");
        String password = sc.nextLine();
        User Selleruser = new User(username, password);
        if (validateName(username) && isCustomerExist(username)) {
            Seller newSeller = new Seller(Selleruser);
            all_sellers[sellerArraySize] = newSeller;
            System.out.println("Seller added successfully.");
            sellerArraySize++;
        } else {
            System.out.println("invalid name.");
            addSeller();

        }

    }

    //##########################################section2#####################################################################
    public void addCustomer() {
        validateCustomerArraySize();
        System.out.println("Enter Customer's username: ");
        String username = sc.nextLine();

        if (validateName(username) && isCustomerExist(username)) {
            System.out.println("Enter Customer's password: ");
            String password = sc.nextLine();
            User CustomerUser = new User(username, password);
            System.out.println("Enter Customer's city: ");
            String city = sc.nextLine();
            System.out.println("Enter Customer's street: ");
            String street = sc.nextLine();
            System.out.println("Enter Customer's building number: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid building number:");
                sc.next();
            }
            int bdnum = sc.nextInt();
            sc.nextLine();

            Address a1 = new Address(city, street, bdnum);
            Customer newCustomer = new Customer(a1);
            all_customers[customerArraySize] = newCustomer;
            System.out.println("Customer added successfully.");
            customerArraySize++;
        } else {
            System.out.println("Invalid Customer's username");
            addCustomer();
        }

    }

    //##########################################section3#####################################################################
    public void addProductForSeller() {
        if (sellerArraySize == 0) {
            System.out.println("There are no sellers in the database. ");

        } else {
            System.out.println("Enter Seller Name to Add Product to: ");
            String sellerName = sc.nextLine();
            Seller seller = findSellerByName(sellerName);
            if (seller != null) {
                System.out.println("Enter Product Name: ");
                String productName = sc.nextLine();
                System.out.println("Enter Product Price: ");
                try {
                    int productPrice = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Product Category (KIDS,ELECTRICITY,OFFICE,CLOTHES):");
                    String typeInput = sc.nextLine().toUpperCase();
                    Category category = Category.valueOf(typeInput);
                    System.out.println("Do you want to add special package to your product? (y/n)");
                    String choice = sc.next().toLowerCase();
                    if (choice.equals("y")) {
                        SpecialPackage sp = new SpecialPackage(productName, productPrice, category);
                        seller.addProductForSeller(sp);
                        seller.setNumOfProducts(seller.getNumOfProducts() + 1);
                        System.out.println("Product added successfully.");
                    } else if (choice.equals("n")) {
                        Product p1 = new Product(productName, productPrice, category);
                        seller.addProductForSeller(p1);
                        seller.setNumOfProducts(seller.getNumOfProducts() + 1);
                        System.out.println("Product added successfully.");
                    } else {
                        System.out.println("Invalid input provided. answer with y/n");
                        sc.nextLine();
                        addProductForSeller();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input provided. Please enter valid Category values.");
                    sc.nextLine();
                    addProductForSeller();

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for product price. Please enter a valid integer value.");
                    sc.nextLine(); // Clear the buffer
                    addProductForSeller();
                }
            } else {
                System.out.println("Invalid Seller's username , Try again.");
                addProductForSeller();
            }
        }
        sc.nextLine();

    }

    //##########################################section4#####################################################################
    public void addProductForCustomer() {
        try {
            if (customerArraySize == 0) {
                System.out.println("There are no customers in the database. ");
            } else {
                System.out.println("Please select a Customer by number: ");
                showAllCustomersNames();
                int customerChoice = sc.nextInt();
                if (sellerArraySize == 0) {
                    System.out.println("There are no sellers in the database. ");
                }
                System.out.println("Please select a Seller by number: ");
                showAllSellersName();
                int sellerChoice = sc.nextInt();
                Seller seller = all_sellers[sellerChoice - 1];


                for (int i = 0; i < seller.getNumOfProducts(); i++) {
                    System.out.println(i + 1 + ")" + seller.getSellerProducts()[i]);
                }
                System.out.println("enter product number to add product to customer: ");
                int productNumber = sc.nextInt();
                Product chosenProduct = new Product(seller.getSellerProducts()[productNumber - 1]);
                all_customers[customerChoice - 1].getCart().addProductToCart(chosenProduct);
                System.out.println("Product added successfully.");


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("invalid customer or seller number entered");
            addProductForCustomer();
        } catch (Exception e) {
            System.out.println("Wrong input provided. Please enter a valid integer value.");
            addProductForCustomer();
        }
    }


    //##########################################section5#####################################################################
    public void orderPayment() {
        if (customerArraySize == 0) {
            System.out.println("There are no customers in the database. ");
        } else {
            for (int i = 0; i < customerArraySize; i++) {
                System.out.println(i + 1 + ")" + all_customers[i].getUsername());
            }
            try {
                System.out.println("select a customer number: ");
                int customerSelection = sc.nextInt();
                Customer customer = all_customers[customerSelection - 1];
                Order newOrder = customer.closeOrder();
                if (newOrder.getCart().getNumOfProducts() == 0) {
                    throw new Exception("Cart is empty! Add product first!");
                }
                System.out.println("Date: " + newOrder.getDate());
                System.out.println("Cart for customer: " + customer.getUsername());
                System.out.println("user's History:");
                customer.printHistory();
                System.out.println("Total price: " + newOrder.calculatePrice());
                System.out.println("Order payment successfully!");
                System.out.println("Cart moved to Orders.");

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("invalid customer or seller number entered");
                orderPayment();
            } catch (Exception e) {
                System.out.println("Cart is empty! Add product first!");
                sc.nextLine();

            }
        }
        sc.nextLine();
    }

    //##########################################section6#####################################################################
    public void showAllSellers() {
        bubbleSort(all_sellers, new CompareSellersByNumOfProducts());
        for (int i = 0; i < sellerArraySize; i++) {
            System.out.print("User's Name: ");
            System.out.println(all_sellers[i].getUser().getUsername());
            System.out.print("User's Products: ");
            System.out.println(Arrays.toString(all_sellers[i].getSellerProducts()));
        }
    }

    //##########################################section7#####################################################################
    public void showAllCustomers() {
        sortByUsername(all_customers);
        for (int i = 0; i < customerArraySize; i++) {
            System.out.print("User's Name: ");
            System.out.println(all_customers[i].getUsername());
            System.out.print("User's Current Cart: ");
            System.out.print(all_customers[i].getCart());
            System.out.print("User's OrderHistory: ");
            System.out.println(Arrays.toString(all_customers[i].getHistory()));
        }

    }

    //##########################################section8#####################################################################
    private void showAllProductsByCategory() {
        for (Category c : Category.values()) {
            System.out.println();
            System.out.println("Products in " + c + " Category");
            int index = 1;
            for (int i = 0; i < sellerArraySize; i++) {
                for (int j = 0; j < all_sellers[i].getNumOfProducts(); j++) {
                    if (all_sellers[i].getSellerProducts()[j].getCategory() == c) {
                        System.out.println(index + ") " + all_sellers[i].getSellerProducts()[j]);
                        index++;
                    }
                }
            }
        }
    }

    //##########################################section9#####################################################################
    private void copyCartFromHistory() {
        showAllCustomersNames();
        try {
            System.out.println("Choose Customer number to show his history: ");
            int customerSelection = sc.nextInt();
            Customer customer = all_customers[customerSelection - 1];
            customer.printHistory();
            System.out.println("Choose the number of cart you want to put in your cart");
            int cartSelection = sc.nextInt();
            if (!customer.isEmpty(customer.getCart())) {
                System.out.println("Do you want to change your cart to one from your history or to add to your cart ? (new/add)");
                String choice = sc.next().toLowerCase();
                if (customer.getHistory() == null) {
                    System.out.println("Your history is empty! make an order to update her ");
                }
                if (choice.equals("new")) {
                    customer.emptyCart();
                    try {
                        customer.createNewCart(cartSelection);
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (choice.equals("add")) {
                    Cart currentCart = customer.getCart();
                    try {
                        customer.createNewCart(cartSelection);
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    Product[] newCart = customer.combineCarts(currentCart, customer.getCart());
                    customer.getCart().setProducts(newCart);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("invalid number of Order in your history!!try again");
            copyCartFromHistory();
        } catch (Exception e) {
            System.out.println("Wrong input provided. Please enter a valid integer value.");
            copyCartFromHistory();
        }
    }

    //##########################################Functions#####################################################################
    public boolean isCustomerExist(String userName) {
        for (int i = 0; i < customerArraySize; i++) {
            if (userName.equals(all_customers[i].getUsername())) {
                return false;
            }

        }
        return true;
        // V
    }

    private void validateSellerArraySize() {
        if (sellerArraySize < all_sellers.length) {
            return;
        }
        // double the size
        int newSize = all_sellers.length * 2;
        newSize = Math.max(2, newSize);
        Seller[] temp = new Seller[newSize];
        System.arraycopy(all_sellers, 0, temp, 0, all_sellers.length);
        all_sellers = temp;

    }

    private void validateCustomerArraySize() {
        if (customerArraySize < all_customers.length) {
            return;
        }
        // double the size
        int newSize = all_customers.length * 2;
        newSize = Math.max(2, newSize);
        Customer[] temp = new Customer[newSize];
        System.arraycopy(all_customers, 0, temp, 0, all_customers.length);
        all_customers = temp;
        // V
    }

    private boolean validateName(String name) {
        if (name != null && name.length() > 0) {
            return true;
        } else {
            return false;
        }
        // V

    }

    public void bubbleSort(Object[] array, Comparator c) {
        for (int i = sellerArraySize - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (c.compare(array[j], array[j + 1]) > 0) {
                    Object temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private void sortByUsername(Customer[] array) {
        for (int i = customerArraySize - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j].getUsername().compareTo(array[j + 1].getUsername()) > 0) {
                    Customer temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                }
            }
        }

    }

    private void showAllSellersName() {
        for (int i = 0; i < sellerArraySize; i++) {
            System.out.println(i + 1 + ")" + all_sellers[i].getUser().getUsername());
        }
    }

    private void showAllCustomersNames() {
        for (int i = 0; i < customerArraySize; i++) {
            System.out.println(i + 1 + ")" + all_customers[i].getUsername());
        }
    }

    private Seller findSellerByName(String sellerName) {
        for (int i = 0; i < sellerArraySize; i++) {
            if (all_sellers[i].getUser().getUsername().equals(sellerName)) {
                return all_sellers[i];
            }
        }
        return null;
    }

}
