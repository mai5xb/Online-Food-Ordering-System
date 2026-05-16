import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<FoodItem> cart;     // List of items the customer wants to order
    private ArrayList<Order> orderHistory;     // List of all past orders

    public Customer(String userID, String name, String password) {
        super(userID, name, password);
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    // Add a food item to the cart
    public void addToCart(FoodItem item) {
        if (!item.isAvailable()) {
            System.out.println("Item not available.");
            return;
        }
        cart.add(item);
        System.out.println("Added to cart: " + item.getName());
    }

    // Place an order using the items in the cart
    public Order placeOrder(String orderID, String timestamp) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return null;
        }

        Order order = new Order(orderID, new ArrayList<>(cart), timestamp);
        orderHistory.add(order);
        cart.clear();
        return order;
    }

    public void viewOrders() {     // View all past orders
        if (orderHistory.isEmpty()) {
            System.out.println("No past orders.");
            return;
        }
        for (Order o : orderHistory) {
            o.displayOrder();
        }
    }

    public void cancelOrder(String orderID) {    // Cancel an order by ID
        for (Order o : orderHistory) {
            if (o.getOrderID().equals(orderID)) {
                o.cancelOrder();
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public void viewCart() {     // Display all items currently in the cart
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double total = 0;
        System.out.println("\n--- Your Cart ---");

        for (FoodItem item : cart) {
            System.out.println(item.getItemID() + " - " + item.getName() + " - " + item.getPrice());
            total += item.getPrice();
        }

        System.out.println("Total: " + total);
    }

    public double getCartTotal() {    // Calculate total price of items in the cart
        double total = 0;
        for (FoodItem f : cart) {
            total += f.getPrice();
        }
        return total;
    }

    public ArrayList<FoodItem> getCart() {
        return cart;
    }
}
