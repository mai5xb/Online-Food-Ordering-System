import java.util.ArrayList;

// Represents a customer's order containing multiple food items
public class Order {

    private String orderID;
    private ArrayList<FoodItem> items;
    private double totalAmount;
    private String status;
    private String timestamp;

    public Order(String orderID, ArrayList<FoodItem> items, String timestamp) {
        this.orderID = orderID;
        this.items = items;
        this.timestamp = timestamp;
        this.status = "Placed";   // default status when order is created
        calculateTotal();
    }

    public void calculateTotal() {    // Calculate the total price of all items in the order
        totalAmount = 0;
        for (FoodItem f : items) {
            totalAmount += f.getPrice();
        }
    }

    public void displayOrder() {    // Display full order details
        System.out.println("\nOrder ID: " + orderID);
        System.out.println("Status: " + status);
        System.out.println("Time: " + timestamp);
        System.out.println("Items:");

        for (FoodItem f : items) {
            System.out.println("- " + f.getName() + " (" + f.getPrice() + ")");
        }

        System.out.println("Total Amount: " + totalAmount);
    }

    public void setStatus(String newStatus) {    // Update the order status
        this.status = newStatus;
    }

    // Cancel the order only if it is still in 'Placed' status
    public void cancelOrder() {
        if (status.equals("Placed")) {
            status = "Cancelled";
            System.out.println("Order cancelled successfully!");
        }
        else {
            System.out.println("Order cannot be cancelled. Current status: " + status);
        }
    }

    // Set total amount manually (rarely needed)
    public void setTotalAmount(double amount) {
        this.totalAmount = amount;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }
}
