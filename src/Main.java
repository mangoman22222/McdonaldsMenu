import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    public JPanel FoodPanel;
    private JPanel drinkPanel;
    private JPanel HappyMealPanel;
    private JPanel Desserts;
    private JPanel Bakery;
    private JTextArea textArea1;
    private final Map<JButton, Double> buttonPrices = new HashMap<>();
    private double totalPrice = 0.0;
    private double subtotal = 0.0;;
    private String selectedFood = null;
    private String selectedFriesSize = null;
    private String selectedDrink = null;
    private double friesPrice = 0.0;
    private double drinkPrice = 0.0;
    private double foodPrice = 0.0;
    private int orderNumber = 0;
    private ArrayList<String> orderItems = new ArrayList<>();
    private double price = 0.0;
    private String tempFood = null;
    private String tempFries = null;
    private String tempDrink = null;


    //Food Buttons
    private JButton bigMacButton;
    private JButton McChickenButton;
    private JButton QtrCheeseButton;
    private JButton nuggetsButton;
    private JButton mcwrapButton;
    private JButton filetButton;

    private final JButton[] foodButtons = {
        bigMacButton, McChickenButton, QtrCheeseButton, nuggetsButton, mcwrapButton, filetButton
    };
    //Drink Buttons
    private JButton cokeButton;
    private JButton rootBeerButton;
    private JButton iceTeaButton;
    private JButton spriteButton;
    private JButton waterButton;
    private JButton fruitopiaButton;

    private final JButton[] drinkButtons = {
        cokeButton, rootBeerButton, iceTeaButton, spriteButton, waterButton, fruitopiaButton
    };

    //Happy Meal Buttons
    private JButton nuggetsButton1;
    private JButton cheesebugerButton;
    private JButton hotCakesButton;
    private JButton hambugerButton;

    private final JButton[] happyMealButtons = {
        nuggetsButton1, cheesebugerButton, hotCakesButton, hambugerButton
    };

    //Dessert Buttons
    private JButton mcflurryButton;
    private JButton vanillaConeButton;
    private JButton sundaeButton;
    private JButton milkshakeButton;

    private final JButton[] dessertButtons = {
        mcflurryButton, vanillaConeButton, sundaeButton, milkshakeButton
    };

    private JButton smallButton;
    private JButton mediumButton;
    private JButton largeButton;
    //Bakery Buttons

    private final JButton[] friesButtons = {smallButton, mediumButton, largeButton};
    private JButton pieButton;
    private JButton muffinButton;
    private JButton cookieButton;
    private JButton mcPopButton;
    private JButton mealButton;

    private JButton TakeOutButton;
    private JButton EatInButton;
    private JButton newOrderButton;
    private JButton Logo;
    private JButton voidOrderButton;

    private final JButton[] bakeryButtons = {
        pieButton, muffinButton, cookieButton, mcPopButton
    };

    private final JButton[][] allButtons = {
        foodButtons,
        drinkButtons,
        happyMealButtons,
        dessertButtons,
        bakeryButtons,
            friesButtons
    };
    public Main() {


        setButtonPrices(); //sets all prices for buttons

        textArea1.setEditable(false);
        for (JButton[] buttons : allButtons) {
            for (JButton button : buttons) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        price = buttonPrices.getOrDefault(button, 0.0);
                        subtotal += price;
                        orderItems.add(button.getText() + " ($" + String.format("%.2f", price) + ") added to order.");
                        setOrderItems();
                        //if the button is pressed it will show in the order text area
                    }
                });
            }
        }

            for (JButton button : foodButtons) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == button) {
                            selectedFood = button.getText();
                            foodPrice = buttonPrices.getOrDefault(button, 0.0);
                            tempFood = button.getText() + " ($" + String.format("%.2f", foodPrice) + ") added to order.";
                        }
                    }
                });
            }

            for (JButton button : friesButtons) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == button) {
                            selectedFriesSize = button.getText();
                            friesPrice = buttonPrices.getOrDefault(button, 0.0);
                            tempFries = button.getText() + " ($" + String.format("%.2f", friesPrice) + ") added to order."; // Updates the temporary fries variable
                            // Set fries price based on the selected size
                        }
                    }
                });
            }

        for (JButton button : drinkButtons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button) {
                        selectedDrink = button.getText();
                        drinkPrice = buttonPrices.getOrDefault(button, 0.0);
                        tempDrink = button.getText() + " ($" + String.format("%.2f", drinkPrice) + ") added to order.";
                    }
                }
            });
        }
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mealButton) {
                    if (selectedFood == null || selectedFriesSize == null || selectedDrink == null) {
                        textArea1.append("Please select food, fries size, and drink before adding a meal.\n");
                        return;
                    }
                    textArea1.append("Meal added to order.\n");
                    Meal meal = new Meal(selectedFood, selectedFriesSize, selectedDrink);
                    meal.setPrice(foodPrice, friesPrice, drinkPrice);// 20% discount on meal price
                    subtotal+= meal.getPrice();
                    subtotal -= (foodPrice + friesPrice + drinkPrice);
                    findMealItems();
                    orderItems.add(meal.toString()); // Adds meal to the order items
                    setOrderItems();
                    selectedFood = null;
                    selectedFriesSize = null;
                    selectedDrink = null;
                    foodPrice = 0.0;
                    friesPrice = 0.0;
                    drinkPrice = 0.0;


                } else if (e.getSource() == TakeOutButton) {
                    if (subtotal == 0.0) {
                        textArea1.append("Please select items before taking out.\n"); // Checks if the subtotal is 0.0 to ensure items are selected
                        return;
                    }
                    textArea1.append("Take Out selected.\n");
                    textArea1.append("Subtotal: $" + String.format("%.2f", subtotal) + "\n");
                    textArea1.append("Total Price: $" + String.format("%.2f", subtotal * 1.13) + "\n");
                    setRandomOrderNumber();
                    textArea1.append("Your order number is: "  + orderNumber + "\n");
                } else if (e.getSource() == EatInButton) {
                    if (subtotal == 0.0) {
                        textArea1.append("Please select items before eating in.\n");
                        return;
                    }
                    textArea1.append("Eat In selected.\n");
                    textArea1.append("Subtotal: $" + String.format("%.2f", subtotal) + "\n");
                    setRandomOrderNumber();
                    textArea1.append("Total Price: $" + String.format("%.2f", subtotal * 1.13) + "\n");
                    textArea1.append("Your order number is: "  + orderNumber + "\n");
                } else if (e.getSource() == newOrderButton) {
                    resetOrder(); // Resets the order details
                    textArea1.append("New order started.\n");
                    //creates a new order
                } else if (e.getSource() == voidOrderButton) {
                    textArea1.setText("Order voided.\n");
                    resetOrder(); // Resets the order details
                }
            }
        };
        mealButton.addActionListener(listener1);
        smallButton.addActionListener(listener1);
        mediumButton.addActionListener(listener1);
        largeButton.addActionListener(listener1);
        TakeOutButton.addActionListener(listener1);
        EatInButton.addActionListener(listener1);
        newOrderButton.addActionListener(listener1);
        voidOrderButton.addActionListener(listener1);
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.

        Main menu = new Main();
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.white);
        FlatLightLaf.setup(); // Sets the FlatLaf theme

        menu.setSize(1280,720);
        menu.setTitle("Mcdonald's");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);
    }


    public void setButtonPrices()
    {
        //Dessert Prices
        buttonPrices.put(mcflurryButton, 3.99);
        buttonPrices.put(vanillaConeButton, 1.99);
        buttonPrices.put(sundaeButton, 2.49);
        buttonPrices.put(milkshakeButton, 4.99);

        //Food Prices
        buttonPrices.put(bigMacButton, 6.49);
        buttonPrices.put(McChickenButton, 5.99);
        buttonPrices.put(QtrCheeseButton, 7.39);
        buttonPrices.put(nuggetsButton, 9.99);
        buttonPrices.put(mcwrapButton, 7.99);
        buttonPrices.put(filetButton, 5.99);

        //Happy Meal Prices
        buttonPrices.put(nuggetsButton1, 4.99);
        buttonPrices.put(cheesebugerButton, 3.99);
        buttonPrices.put(hotCakesButton, 4.49);
        buttonPrices.put(hambugerButton, 5.49);

        //Bakery Prices
        buttonPrices.put(pieButton, 1.99);
        buttonPrices.put(muffinButton, 2.49);
        buttonPrices.put(cookieButton, 0.99);
        buttonPrices.put(mcPopButton, 1.49);

        buttonPrices.put(largeButton, 4.99);
        buttonPrices.put(mediumButton, 4.49);
        buttonPrices.put(smallButton, 3.99);


        //Drink Prices
        for(JButton button : drinkButtons) {
            buttonPrices.put(button, 1.99);
        }
    }

    public void setRandomOrderNumber(){
        orderNumber = (int)(Math.random() * 900 + 100); // Generates a random order number between 100 and 999
    }

    public void setOrderItems(){
            textArea1.setText("");
            for (String item : orderItems) {
                textArea1.append(item + "\n");
            }
    }

    public void resetOrder() {
        subtotal = 0.0;
        totalPrice = 0.0;
        selectedFood = null;
        selectedFriesSize = null;
        selectedDrink = null;
        foodPrice = 0.0;
        friesPrice = 0.0;
        drinkPrice = 0.0;
        orderItems.clear();
        textArea1.setText("Welcome to McDonald's!\n");
    }

    public void findMealItems() {

        boolean findFood = false;
        boolean findFries = false;
        boolean findDrink = false;
        for (int i = 0; i < orderItems.size();) {
            String item = orderItems.get(i);
            if (!findFood && item.equals(tempFood)) {
                orderItems.remove(i); // Remove the food item from the order items
                findFood = true;

            }else if (!findFries && item.equals(tempFries)) {
                orderItems.remove(i); // Remove the fries item from the order items
                findFries = true;
            }else if (!findDrink && item.equals(tempDrink)) {
                orderItems.remove(i); // Remove the drink item from the order items
                findDrink = true;
            }else {
                i++; // add the index to avoid skipping the next item after removal
            }

            if (findFood && findFries && findDrink) {
                tempFood = null; // Reset temporary variables after finding all items
                tempFries = null;
                tempDrink = null;
                break; // Exit the loop if all items are found
            }
        }
        setOrderItems();
    }

}