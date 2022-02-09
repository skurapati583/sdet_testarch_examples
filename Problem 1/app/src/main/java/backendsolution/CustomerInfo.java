package backendsolution;

public class CustomerInfo {

    private String courseName;
    private String purchaseDate;
    private double amount;
    private String location;

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format(" Course Name: %s %n Purchase Date: %s %n Amount: %f %n Location: %s %n",
                this.getCourseName(), this.getPurchaseDate(), this.getAmount(), this.getLocation());
    } 

}
