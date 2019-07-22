package com.example.ordersapp;
// This Code has been made by: Mohammed Zaid
// for further question/inquiries get in touch with me at : +218945798487 or Mohammed.r.zaid20@Gmail.com
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView counterTextView;
    TextView summaryText;
    Button orderButton;
    EditText NameText;
    int coffeePrice = 5;
    CheckBox caramel;
    CheckBox creme;
    int cremePrice = 1;
    int caramelPrice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        counterTextView = findViewById(R.id.counter);
        summaryText = findViewById(R.id.summaryText);
        orderButton = findViewById(R.id.orderButtonView);
        NameText = findViewById(R.id.NameText);
        caramel = findViewById(R.id.caramelView);
        creme = findViewById(R.id.cremeView);

        orderButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        String name = NameText.getText().toString();
                        String counterString = counterTextView.getText().toString();
                        boolean hasCreme = creme.isChecked();
                        boolean hasCaramel = caramel.isChecked();
                        int currentCoffeePrice = coffeePrice;
                        String extraOrders = "";

                        if (hasCreme) {
                            currentCoffeePrice = currentCoffeePrice + cremePrice;
                            extraOrders = extraOrders + "with creme";
                        }
                        if (hasCaramel) {
                            currentCoffeePrice = currentCoffeePrice + caramelPrice;
                            if(hasCreme){
                                extraOrders = extraOrders + " and";
                            }
                            extraOrders = extraOrders + " with caramel";
                        }

                        int totalPrice = Integer.valueOf(counterString) * currentCoffeePrice;
                        String orderSummary = name + "\n"
                                + "You have ordered " + counterString + " Coffee cups" + "\n"
                                + extraOrders + "\n"
                                + "Total price is " + totalPrice + "Dollars";

                        summaryText.setText(orderSummary);

                        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
                        sendEmail.setType("text/plain");
                        if (sendEmail.resolveActivity(getPackageManager()) != null) {
                            startActivity(sendEmail);
                        } else {
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }


        public void incerement (View view){

            String orderString = counterTextView.getText().toString();
            int counter = Integer.parseInt(orderString);
            counter++;
            counterTextView.setText(String.valueOf(counter));

        }

        public void decrement (View view){
            String orderString = counterTextView.getText().toString();
            int counter = Integer.parseInt(orderString);
            counter--;
            counterTextView.setText(String.valueOf(counter));
        }
    }


