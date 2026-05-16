public class FoodItem {

    private String itemID;
    private String name;
    private double price;
    private String category;
    private boolean available;

    public FoodItem(String itemID, String name, double price, String category, boolean available) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }


    public String getItemID() {
        return itemID;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setAvailable(boolean available) {     // Update availability
        this.available = available;
    }

    public void displayItem() {    // Display item details
        System.out.println(itemID + " - " + name + " - " + price + " SAR - " + category +
                " - " + (available ? "Available" : "Not Available"));
    }
}
