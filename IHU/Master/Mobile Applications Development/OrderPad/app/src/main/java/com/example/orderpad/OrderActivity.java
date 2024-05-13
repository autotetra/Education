package com.example.orderpad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Xml;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private TextView tvTableId, tvOrderValue;
    private ImageButton btnSendOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Setup the ActionBar to enable the Up (back) option.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        tvTableId = findViewById(R.id.tvTableId);
        tvOrderValue = findViewById(R.id.tvOrderValue);
        btnSendOrder = findViewById(R.id.btnSendOrder);
        recyclerView = findViewById(R.id.rvProducts);

        // Adapter setup
        adapter = new ProductListAdapter(this, new ArrayList<>(), this::updateTotalOrderValue, true); // Pass true to show buttons
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get table ID from the previous activity
        String tableId = getIntent().getStringExtra("table_id");
        tvTableId.setText("Table: " + tableId);

        // Fetch products (you will need to implement or adjust fetchProducts method based on your server)
        fetchProducts(tableId);

        // Setup the send order button
        btnSendOrder.setOnClickListener(v -> sendOrder());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();  // This will close the current activity and go back to the previous one.
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendOrder() {
        //Logs
        Log.d("OrderDebug", "sendOrder: Starting to send order.");

        if (isAllQuantitiesZero()) {
            Log.d("OrderDebug", "sendOrder: No products selected.");
            Toast.makeText(this, "No products selected. Please select at least one item.", Toast.LENGTH_LONG).show();
            return;
        }

        String orderContents = getOrderContents();
        if (orderContents.isEmpty()) {
            Toast.makeText(this, "No products with quantities to send.", Toast.LENGTH_LONG).show();
            return;
        }

        String tableId = tvTableId.getText().toString().split(" ")[1];  // Assuming "Table: [ID]" format
        String url = Constants.SEND_ORDER_URL + "&tid=" + tableId + "&oc=" + orderContents;
        Log.d("OrderDebug", "sendOrder: URL formed - " + url);

        Toast.makeText(this, "Sending order...", Toast.LENGTH_SHORT).show(); // Feedback before sending

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                URL serverUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = streamToString(urlConnection.getInputStream());
                    handler.post(() -> {
                        processServerResponse(response, tableId);
                        //finish(); // Finish the activity after processing server response
                    });
                } else {
                    throw new IOException("HTTP error code: " + responseCode);
                }
            } catch (Exception e) {
                handler.post(() -> {
                    Toast.makeText(OrderActivity.this, "Error sending order: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    finish(); // Finish the activity in case of error
                });
            }
        });
    }



    private void processServerResponse(String response, String tableId) {
        Log.d("OrderDebug", "processServerResponse: Response received - " + response);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("table_id", tableId);

        if (response.contains("4-OK")) {
            resultIntent.putExtra("table_status", "1"); // Set table status to occupied
            setResult(RESULT_OK, resultIntent);
            Log.d("OrderDebug", "processServerResponse: Order sent successfully. Table status set to occupied.");

            // Update table status here
            updateTableStatus(tableId, "1");

            // Display a toast message indicating successful order submission
            Toast.makeText(this, "Order sent successfully!", Toast.LENGTH_SHORT).show();
        } else {
            setResult(RESULT_CANCELED);
            Log.d("OrderDebug", "processServerResponse: Failed - " + response);

            // Display a toast message indicating order submission failure
            Toast.makeText(this, "Failed to send order. Please try again.", Toast.LENGTH_SHORT).show();
        }

        // Delay finishing the activity to ensure the table status update completes
        new Handler().postDelayed(() -> finish(), 1000); // Adjust delay time as needed
    }





    private void updateTableStatus(String tableId, String status) {
        int buttonId = getResources().getIdentifier("btnTable" + tableId, "id", getPackageName());
        Button tableButton = findViewById(buttonId);
        if (tableButton != null) {
            tableButton.setBackgroundColor("1".equals(status) ? Color.RED : Color.GREEN); // Change color dynamically
            Log.d("TableActivity", "UI updated for table " + tableId + " to status " + status);
        } else {
            Log.d("TableActivity", "Failed to find button for table " + tableId);
        }
    }




    private boolean isAllQuantitiesZero() {
        for (Product product : adapter.getProductList()) {
            if (product.getQuantity() > 0) return false;
        }
        return true;
    }

    private String getOrderContents() {
        StringBuilder contents = new StringBuilder();
        for (Product product : adapter.getProductList()) {
            if (product.getQuantity() > 0) {
                if (contents.length() > 0) contents.append(";");
                contents.append(product.getId()).append(",").append(product.getQuantity());
            }
        }
        return contents.toString();
    }

    private String streamToString(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

    private String extractMessageFromResponse(String response) {
        // Parse response to extract message
        return "Failed due to server restrictions.";
    }


    private void fetchProducts(String tableId) {
        // Example URL; you should adjust it according to your actual API
        String url = Constants.GET_COFFEE_DATA_URL;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Simulate network access
            WebRequest webRequest = new WebRequest(url);
            String result = webRequest.GetResponse();
            handler.post(() -> {
                if (result != null && !result.isEmpty()) {
                    parseProducts(result);
                } else {
                    Toast.makeText(OrderActivity.this, "Failed to fetch products!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void parseProducts(String xmlData) {
        List<Product> products = new ArrayList<>();
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int eventType = parser.getEventType();
            String id = null, name = null;
            double price = 0;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                if (eventType == XmlPullParser.START_TAG && "product".equals(tagName)) {
                    id = name = null;  // Reset for new product
                    price = 0.0;
                } else if ("id".equals(tagName)) {
                    id = parser.nextText().trim();
                } else if ("title".equals(tagName)) {
                    name = parser.nextText().trim();
                } else if ("price".equals(tagName)) {
                    price = Double.parseDouble(parser.nextText().trim());
                    if (id != null && name != null) {
                        products.add(new Product(id, name, price));
                        Log.d("OrderActivity", "Parsed Product - ID: " + id + ", Name: " + name + ", Price: " + price);
                    }
                }
                eventType = parser.next();
            }
            Log.d("OrderActivity", "Total Products Parsed: " + products.size());
            runOnUiThread(() -> {
                adapter.updateData(products);
                updateTotalOrderValue();
            });
        } catch (Exception e) {
            Log.e("OrderActivity", "Error parsing products XML", e);
        }
    }


    public void updateTotalOrderValue() {
        double total = 0;
        for (Product product : adapter.getProductList()) {  // Retrieve the list of products from the adapter
            total += product.getTotalPrice();  // Sum up the total price
        }
        tvOrderValue.setText(String.format(" $%.2f", total));  // Update the TextView with the new total
    }

}
