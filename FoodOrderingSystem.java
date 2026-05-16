import java.util.ArrayList;

public class FoodOrderingSystem { // Main system that manages users, menu, and orders

    private ArrayList<User> users = new ArrayList<>();
    private Menu menu = new Menu();
    private ArrayList<Order> orders = new ArrayList<>();

    public FoodOrderingSystem() {     // Constructor loads data from files

        // Load everything from files
        users = FileHandler.loadUsers();
        menu = FileHandler.loadMenu();
        orders = FileHandler.loadOrders();

        // If files were empty, create new objects
        if (users == null) users = new ArrayList<>();
        if (menu == null) menu = new Menu();
        if (orders == null) orders = new ArrayList<>();
    }


    public User login(String userID, String password) {     // Login method
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getUserID().equals(userID)) {
                boolean correct = u.login(password);
                if (correct)
                    return u;
                else
                    return null;
            }
        }
        // If we reach here, no user had that ID
        System.out.println("User not found.");
        return null;
    }


    public void browseMenu() {    // Show menu
        menu.displayMenu();
    }

    // Process customer order
    public void processOrder(Customer customer, String orderID, String timestamp) {
        Order order = customer.placeOrder(orderID, timestamp);
        if (order != null) {
            orders.add(order);
            FileHandler.saveOrder(order);
            System.out.println("Order processed.");
        }
    }

    public void viewReports() {    // Show system reports
        System.out.println("Total Users: " + users.size());
        System.out.println("Total Orders: " + orders.size());
    }
    public void saveAll() {    // Save everything to files
        FileHandler.saveUsers(users);
        FileHandler.saveMenu(menu);
        for (Order o : orders) {
            FileHandler.saveOrder(o);
        }
    }


    public void addUser(User u) { users.add(u); }
    public Menu getMenu() { return menu; }
    public void addOrder(Order order) { orders.add(order); }
    public ArrayList<Order> getOrders() { return orders; }
    public ArrayList<User> getUsers() { return users; }
}
