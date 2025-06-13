import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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


        setButtonPrices();
        textArea1.setEditable(false);
        for (JButton[] buttons : allButtons) {
            for (JButton button : buttons) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double price = buttonPrices.getOrDefault(button, 0.0);
                        subtotal += price;
                        textArea1.append(button.getText() + " ($" + String.format("%.2f", price) + ") added to order.\n");
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
                    }
                }
            });
        }
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mealButton) {
                    textArea1.append("Meal added to order.\n");
                    Meal meal = new Meal(selectedFood, selectedFriesSize, selectedDrink);
                    meal.setPrice(foodPrice, friesPrice, drinkPrice);// 20% discount on meal price
                    subtotal+= meal.getPrice();
                    subtotal -= (foodPrice + friesPrice + drinkPrice);

                    selectedFood = null;
                    selectedFriesSize = null;
                    selectedDrink = null;
                    foodPrice = 0.0;
                    friesPrice = 0.0;
                    drinkPrice = 0.0;
                    textArea1.append(meal + "\n");

                } else if (e.getSource() == TakeOutButton) {
                    textArea1.append("Take Out selected.\n");
                    textArea1.append("Subtotal: $" + String.format("%.2f", subtotal) + "\n");
                    textArea1.append("Total Price: $" + String.format("%.2f", subtotal * 1.13) + "\n");
                } else if (e.getSource() == EatInButton) {
                    textArea1.append("Eat In selected.\n");
                    textArea1.append("Subtotal: $" + String.format("%.2f", subtotal) + "\n");
                    textArea1.append("Total Price: $" + String.format("%.2f", subtotal * 1.13) + "\n");
                } else if (e.getSource() == newOrderButton) {
                    subtotal = 0.0;
                    totalPrice = 0.0;
                    textArea1.setText("Welcome to McDonald's!\n");
                    textArea1.append("New order started.\n");
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
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.

        Main menu = new Main();
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.black);

        menu.setSize(1920,1080);
        menu.setTitle("Mcdonalds");
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

}