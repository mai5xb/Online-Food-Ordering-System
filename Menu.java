import java.util.ArrayList;

public class Menu { // Represents the restaurant menu containing multiple food items

    private ArrayList<FoodItem> items;     // List of all food items in the menu

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {    // Add a new food item to the menu
        if (item == null) {
            System.out.println("Invalid item.");
            return;
        }
        items.add(item);
    }

    public void removeItem(String itemID) {     // Remove a food item using its ID
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemID().equals(itemID)) {
                items.remove(i);
                System.out.println("Item removed.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    // Update an existing food item by replacing it with a new one
    public void updateItem(String itemID, String newName, double newPrice, String newCategory, boolean newAvailability) {
        for (FoodItem f : items) {
            if (f.getItemID().equals(itemID)) {

                // Update fields directly
                f.setName(newName);
                f.setPrice(newPrice);
                f.setCategory(newCategory);
                f.setAvailable(newAvailability);

                System.out.println("Item updated.");
                return;
            }
        }
        System.out.println("Item not found.");
    }


    public void displayMenu() {    // Display all items in the menu
        if (items.isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }
        for (FoodItem f : items) {
            f.displayItem();
        }
    }

    public void updateAvailability(String itemID, boolean newAvailability) {
        for (FoodItem f : items) {
            if (f.getItemID().equals(itemID)) {
                f.setAvailable(newAvailability);
                System.out.println("Availability updated successfully!");
                return;
            }
        }
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }
}
