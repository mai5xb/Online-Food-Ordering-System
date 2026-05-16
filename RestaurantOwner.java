import java.util.ArrayList;

public class RestaurantOwner extends User {

    public RestaurantOwner(String userID, String name, String password) {
        super(userID, name, password);
    }

    public void manageMenu(Menu menu) {    // Display the menu for management purposes
        menu.displayMenu();
    }

    public void viewOrders(ArrayList<Order> orders) {    // View all orders in the system
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (Order o : orders) {
            o.displayOrder();
        }
    }
    // Update the status of a specific order
    public void updateOrderStatus(Order order, String newStatus) {
        order.setStatus(newStatus);
        System.out.println("Order status updated.");
    }
}
