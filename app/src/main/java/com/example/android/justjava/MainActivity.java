package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {

        if(quantity == 100) {
            //show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
        quantity = quantity + 1;
        display(quantity);

    }

    /**
     * This method is called when the _ button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 0){
            //show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            //exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity - 1;
        display(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.choclate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();



        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);


        displayMessage(priceMessage);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * @param addWhippedCream whether the user wants the whipped cream topping or not
     * @param addChocolate whether the user wants the chocolate topping or not
     * Calculates the price of the order.
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // price of one cup of coffee
        int basePrice = 5;
        //Add $1 if the user wants the whipped cream
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }
        //Add $2 if the user wants the chocolate
        if(addChocolate){
            basePrice = basePrice + 2;
        // Add $3 if the user wants both the chocolate and whipped cream
        }

        return quantity * basePrice;
    }

    /**
     * Create summary of order
     * @param name of the customer
     * @param price of the order
     * @param addWhippedCream  whether or not the user wants the whipped cream or not
     * @param addChocolate
     * @return text summary
     */

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: " + price;
        priceMessage += "\nThank you!";

        return priceMessage;

    }


}