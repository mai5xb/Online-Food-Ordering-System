public class Payment {

    private String paymentID;
    private double amount;
    private String method;   // Cash or Card
    private String status;   // Pending or Success

    public Payment(String paymentID, double amount, String method) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.method = method;
        this.status = "Pending"; // default status
    }

    public boolean processPayment() {     // Process the payment
        if (method.equals("Cash") || method.equals("Card")) { //both Cash and Card always succeed
            status = "Success";
            System.out.println("Payment successful via " + method);
            return true;
        }
        return false;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }
}
