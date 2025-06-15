public class HappyMeal {

    private String food;
    private String drink;
    private double price;
    private String friesSize = "Small"; // Default fries size for Happy Meal

    // Constructor to initialize the HappyMeal object with food, drink, and toy
    public HappyMeal(String food, String drink) {
        this.food = food;
        this.drink = drink;
        if (drink == null)
        {
            this.drink = "Water"; // Default drink if none is provided
        }
    }

    public void setPrice(double foodPrice, double drinkPrice) {
        price = (foodPrice + drinkPrice) * 0.9; // 10% discount for Happy Meal
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return food + " Happy Meal" + " (" + friesSize + " fry, " + drink + ", Toy) - $" + String.format("%.2f", price);
    }
}
