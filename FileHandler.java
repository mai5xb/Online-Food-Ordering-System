import java.io.FileWriter;
import java.io.IOException;

class FileHandler {

    // Save a single order to orders.txt
    static void saveOrder(Order order) {
        try {
            FileWriter fw = new FileWriter("orders.txt", true); // append mode
            fw.write("Order ID: " + order.getOrderID() + "\n");
            fw.write("Status: " + order.getStatus() + "\n");
            fw.write("Total: " + order.getTotalAmount() + "\n");
            fw.write("Time: " + order.getTimestamp() + "\n");
            fw.write("----------------------\n");
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error saving order!");
        }
    }

    // Empty methods so the system does not crash
    static java.util.ArrayList<User> loadUsers() {
        return new java.util.ArrayList<>();
    }

    static Menu loadMenu() {
        return new Menu();
    }

    static java.util.ArrayList<Order> loadOrders() {
        return new java.util.ArrayList<>();
    }

    static void saveUsers(java.util.ArrayList<User> users) {
    }

    static void saveMenu(Menu menu) {
    }
}