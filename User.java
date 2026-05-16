public abstract class User {        // Abstract base class for all users in the system (Customer, RestaurantOwner)

    private String userID;
    private String name;
    private String password;
    private int attempts;
    private boolean locked;

    protected String role;

    public User(String userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.attempts = 0;
        this.locked = false;
    }

    // Login method with 3-attempt lockout system
    public boolean login(String inputPassword) {
        if (locked) {
            System.out.println("Account is locked.");
            return false;
        }

        if (password.equals(inputPassword)) {
            attempts = 0; // reset attempts after successful login
            System.out.println("Login successful.");

            return true;
        }
        else {
            attempts= attempts + 1;
            System.out.println("Wrong password.");
            // Lock account after 3 failed attempts
            if (attempts >= 3) {
                locked = true;
                System.out.println("Account locked after 3 failed attempts.");
            }
            return false;
        }
    }
    // Logout method
    public void logout() {
        System.out.println("Logged out.");
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getUserID() {
        return userID;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    public String getRole() { return role; }


}
