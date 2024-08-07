package com.example.orderpad;

import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private List<Product> productList;
    private TextView tvTableId, tvCost, tvPaid, tvBalance;
    private EditText etAmount;
    private ImageButton btnConfirmPayment;
    private String tableId;
    private double currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTableId = findViewById(R.id.tv_table_id);
        tvCost = findViewById(R.id.tv_cost);
        tvPaid = findViewById(R.id.tv_paid);
        tvBalance = findViewById(R.id.tv_balance);
        etAmount = findViewById(R.id.et_amount); // Initialize EditText
        btnConfirmPayment = findViewById(R.id.btn_confirm_payment); // Initialize Button
        recyclerView = findViewById(R.id.rv_product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        adapter = new ProductListAdapter(this, productList, this::updateTotal, false); // Pass false to hide buttons
        recyclerView.setAdapter(adapter);

        // Get the table ID passed from TableActivity
        tableId = getIntent().getStringExtra("table_id");
        if (tableId == null || tableId.isEmpty()) {
            Toast.makeText(this, "Error: No table ID provided", Toast.LENGTH_SHORT).show();
            finish();
            return; // Exit the method if no table ID is provided
        }

        fetchOrderDetails();

        // Set up button click listener
        btnConfirmPayment.setOnClickListener(v -> submitPayment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();  // This will close the current activity and go back to the previous one.
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchOrderDetails() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            String url = "http://mad.mywork.gr/get_order.php?t=2973&tid=" + tableId;
            Log.d("PaymentActivity", "Fetching order details from URL: " + url); // Log the URL
            WebRequest webRequest = new WebRequest(url);
            String response = webRequest.GetResponse();
            Log.d("PaymentActivity", "Server Response: " + response); // Log the response

            runOnUiThread(() -> parseXMLAndPopulate(response));
        });
    }

    private void parseXMLAndPopulate(String xml) {
        List<Product> products = new ArrayList<>();
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(xml));
            int eventType = parser.getEventType();

            String id = null;
            String name = null;
            double price = 0.0;
            int quantity = 0;

            String cost = null;
            String payment = null;
            String balance = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equals("cost")) {
                            cost = parser.nextText();
                        } else if (tagName.equals("payment")) {
                            payment = parser.nextText();
                        } else if (tagName.equals("balance")) {
                            balance = parser.nextText();
                        } else if (tagName.equals("product")) {
                            id = name = null;  // Reset for new product
                            price = 0.0;
                            quantity = 0;
                        } else if (tagName.equals("id")) {
                            id = parser.nextText();
                        } else if (tagName.equals("title")) {
                            name = parser.nextText();
                        } else if (tagName.equals("price")) {
                            price = Double.parseDouble(parser.nextText());
                        } else if (tagName.equals("quantity")) {
                            quantity = Integer.parseInt(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equals("product") && id != null && name != null) {
                            products.add(new Product(id, name, price, quantity));
                        }
                        break;
                }
                eventType = parser.next();
            }

            final String finalCost = cost;
            final String finalPayment = payment;
            final String finalBalance = balance;

            runOnUiThread(() -> {
                adapter.updateData(products);
                tvCost.setText("Cost: $" + finalCost);
                tvPaid.setText("Paid: $" + finalPayment);
                tvBalance.setText("Balance: $" + finalBalance);
                currentBalance = Double.parseDouble(finalBalance);
                etAmount.setHint("Enter amount <= " + finalBalance);
                updateTotal();
            });
        } catch (Exception e) {
            Log.e("XMLParsingError", "Error parsing order details", e);
            Toast.makeText(this, "Error parsing order details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (Product product : productList) {
            total += product.getTotalPrice();
        }
        tvBalance.setText(String.format("$%.2f", total));
    }

    private void submitPayment() {
        String amountStr = etAmount.getText().toString().trim();
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
            return;
        }

        if (amount <= 0 || amount > currentBalance) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://mad.mywork.gr/send_payment.php?t=2973&tid=" + tableId + "&a=" + amount;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            WebRequest webRequest = new WebRequest(url);
            String response = webRequest.GetResponse();

            runOnUiThread(() -> {
                if (response.contains("<status>6-OK</status>")) {
                    Toast.makeText(PaymentActivity.this, "Payment successful", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and return to TableActivity
                } else {
                    Toast.makeText(PaymentActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
