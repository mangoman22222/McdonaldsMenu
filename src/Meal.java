public class Meal {

    private String food;
    private String drink;
    private double price;
    private String friesSize;

    // Constructor to initialize the Meal object with food, fries size, and drink

    public Meal(String food, String friesSize, String drink) {
        this.food = food;
        this.drink = drink;
        this.friesSize = friesSize;
    }

    public void setPrice(double foodPrice, double drinkPrice, double friesPrice) {
        price = (foodPrice + drinkPrice + friesPrice) * 0.8;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return food + " Meal" + " (" + friesSize + ", " + drink + ") - $" + String.format("%.2f", price);
    }
}
