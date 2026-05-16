import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class main {

    // Safe integer input (prevents crashes)
    public static int safeIntInput(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FoodOrderingSystem system = new FoodOrderingSystem();

        // Sample users
        system.addUser(new Customer("C1", "Mais", "1234"));
        system.addUser(new RestaurantOwner("A1", "Admin", "admin"));

        // Sample menu items
        Menu menu = system.getMenu();
        menu.addItem(new FoodItem("F1", "Burger", 15.0, "Fast Food", true));
        menu.addItem(new FoodItem("F2", "Pizza", 22.0, "Fast Food", true));
        menu.addItem(new FoodItem("F3", "Pasta", 18.0, "Italian", false));

        System.out.println("===== FOOD ORDERING SYSTEM =====");


        while (true) {  // LOGIN
            System.out.print("\nEnter User ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            User user = system.login(id, pass);
            if (user == null) continue;
            System.out.println("Hello " + user.getName() + "! Welcome back.\n");


            // ---------------- CUSTOMER MENU ----------------
            if (user instanceof Customer) {
                Customer c = (Customer) user;

                while (true) {
                    System.out.println("\n--- CUSTOMER MENU ---");
                    System.out.println("1. Browse Menu");
                    System.out.println("2. Add to Cart");
                    System.out.println("3. View Cart");
                    System.out.println("4. Place Order");
                    System.out.println("5. Cancel Order");
                    System.out.println("6. View Order History");
                    System.out.println("7. Logout");

                    System.out.print("Choose: ");
                    int choice = safeIntInput(sc);

                    if (choice == 1)
                        system.browseMenu();

                    else if (choice == 2) {
                        System.out.print("Enter Item ID: ");
                        String itemID = sc.nextLine();

                        FoodItem item = null;
                        for (FoodItem f : menu.getItems()) {
                            if (f.getItemID().equals(itemID)) {
                                item = f;
                                break;
                            }
                        }
                        if (item == null) {
                            System.out.println("Item not found.");
                        }
                        else if (!item.isAvailable()) {
                            System.out.println("Sorry, this item is not available.");
                        }
                        else {
                            c.addToCart(item);
                            System.out.println("Item added to cart.");
                        }

                    }


                    else if (choice == 3)
                        c.viewCart();

                    else if (choice == 4) {
                        if (c.getCart().isEmpty()) {
                            System.out.println("Your cart is empty. Add items before placing an order.");
                            continue;
                        }

                        // PAYMENT SIMULATION
                        System.out.println("\nChoose Payment Method:");
                        System.out.println("1. Cash");
                        System.out.println("2. Card");
                        System.out.print("Enter choice: ");

                        int payChoice = safeIntInput(sc);
                        String method = (payChoice == 1) ? "Cash" : "Card";

                        // Create payment
                        String oid = "O" + System.currentTimeMillis();
                        double amount = c.getCartTotal();

                        Payment payment = new Payment("P" + oid, amount, method);
                        payment.processPayment();   // correct method


                        // Generate real timestamp
                        String timestamp = java.time.LocalDateTime.now()
                                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                        // Place order
                        Order order = c.placeOrder(oid, timestamp);

                        if (order != null) {
                            system.addOrder(order);
                            FileHandler.saveOrder(order);
                            System.out.println("Order placed successfully!");
                            System.out.println("Your Order ID is: " + order.getOrderID());

                        }
                    }

                    else if (choice == 5) {
                        System.out.print("Enter Order ID to cancel: ");
                        String oid = sc.nextLine();
                        c.cancelOrder(oid);
                    }
                    else if (choice == 6)
                        c.viewOrders();

                    else if (choice == 7) {
                        System.out.println("Logging out...");
                        System.out.println("Goodbye " + user.getName() + "! See you next time.");
                        break;
                    }
                }
            }

            // ---------------- ADMIN MENU ----------------
            else if (user instanceof RestaurantOwner) {
                RestaurantOwner admin = (RestaurantOwner) user;

                while (true) {
                    System.out.println("\n--- ADMIN MENU ---");
                    System.out.println("1. View Menu");
                    System.out.println("2. Change Food Availability");
                    System.out.println("3. Add Food Item");
                    System.out.println("4. Remove Food Item");
                    System.out.println("5. View All Orders");
                    System.out.println("6. Update Order Status");
                    System.out.println("7. Logout");
                    System.out.print("Choose: ");

                    int choice = safeIntInput(sc);

                    if (choice == 1)
                        menu.displayMenu();

                    else if (choice == 2) {
                        System.out.print("Enter Food Item ID to update availability: ");
                        String idd = sc.nextLine();

                        System.out.print("Available (true/false): ");
                        boolean avail = Boolean.parseBoolean(sc.nextLine());

                        menu.updateAvailability(idd, avail);
                    }

                    else if (choice == 3) {
                        System.out.print("Item ID: ");
                        String idd = sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = Double.parseDouble(sc.nextLine());
                        System.out.print("Category: ");
                        String cat = sc.nextLine();
                        System.out.print("Available (true/false): ");
                        boolean avail = Boolean.parseBoolean(sc.nextLine());

                        menu.addItem(new FoodItem(idd, name, price, cat, avail));
                    }
                    else if (choice == 4) {
                        System.out.print("Enter Item ID to remove: ");
                        String idd = sc.nextLine();
                        menu.removeItem(idd);
                    }
                    else if (choice == 5)
                        admin.viewOrders(system.getOrders());

                    else if (choice == 6) {
                        System.out.print("Enter Order ID: ");
                        String oid = sc.nextLine();

                        Order found = null;
                        for (Order o : system.getOrders()) {
                            if (o.getOrderID().equals(oid)) {
                                found = o;
                                break;
                            }
                        }

                        if (found != null) {
                            System.out.print("New Status: ");
                            String st = sc.nextLine();
                            admin.updateOrderStatus(found, st);
                            FileHandler.saveOrder(found);
                        } else {
                            System.out.println("Order not found.");
                        }
                    }
                    else if (choice == 7) {
                        System.out.println("Logging out...");
                        System.out.println("Goodbye Admin!");
                        break;
                    }
                }
            }
        }
    }
}
