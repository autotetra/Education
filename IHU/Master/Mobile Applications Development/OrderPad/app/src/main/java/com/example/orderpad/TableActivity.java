package com.example.orderpad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TableActivity extends AppCompatActivity {
    private static final int ORDER_REQUEST = 1;  // Unique request code for starting OrderActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table); // Ensure this layout exists in your res/layout folder
        setupTableButtons();  // Setup buttons first
        fetchTableData();     // Fetch data when activity starts
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Fetch updated data whenever the activity resumes
        fetchTableData();
    }

    private void setupTableButtons() {
        int[] buttonIds = new int[]{
                R.id.btnTable1, R.id.btnTable2, R.id.btnTable3,
                R.id.btnTable4, R.id.btnTable5, R.id.btnTable6,
                R.id.btnTable7, R.id.btnTable8, R.id.btnTable9
        };
        for (int id : buttonIds) {
            Button tableButton = findViewById(id);
            // No need to set listeners here, they will be set in updateButton based on table status
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ORDER_REQUEST && resultCode == RESULT_OK && data != null) {
            String tableId = data.getStringExtra("table_id");
            String status = data.getStringExtra("table_status");
            // Instead of calling updateTableStatus, you can directly update the button color here
            int buttonId = getResources().getIdentifier("btnTable" + tableId, "id", getPackageName());
            Button tableButton = findViewById(buttonId);
            if (tableButton != null) {
                tableButton.setBackgroundColor("1".equals(status) ? Color.RED : Color.GREEN); // Change color dynamically
                Log.d("TableActivity", "UI updated for table " + tableId + " to status " + status);
            } else {
                Log.d("TableActivity", "Failed to find button for table " + tableId);
            }
            // Display a toast message indicating successful order submission
            Toast.makeText(this, "Order sent successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchTableData() {
        String url = Constants.GET_COFFEE_DATA_URL;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            WebRequest webRequest = new WebRequest(url);
            String result = webRequest.GetResponse();
            runOnUiThread(() -> {
                if (result != null && !result.isEmpty()) {
                    parseXMLAndUpdateUI(result);
                } else {
                    Toast.makeText(TableActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void parseXMLAndUpdateUI(String xmlData) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int eventType = parser.getEventType();
            int buttonIndex = 1; // Start with btnTable1

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();
                    if ("table".equals(tagName)) {
                        String id = null;
                        String status = null;

                        while (!(eventType == XmlPullParser.END_TAG && "table".equals(parser.getName()))) {
                            if (eventType == XmlPullParser.START_TAG) {
                                if ("id".equals(parser.getName())) {
                                    id = parser.nextText().trim();
                                } else if ("status".equals(parser.getName())) {
                                    status = parser.nextText().trim();
                                }
                            }
                            eventType = parser.next();
                        }

                        if (id != null && status != null) {
                            final int finalButtonIndex = buttonIndex;
                            final String finalId = id;
                            final String finalStatus = status;
                            runOnUiThread(() -> updateButton(finalButtonIndex, finalId, finalStatus));
                            buttonIndex++;
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            runOnUiThread(() -> {
                Toast.makeText(this, "Error parsing XML", Toast.LENGTH_SHORT).show();
                Log.e("XML Parse Error", "Error parsing XML", e);
            });
        }
    }

    private void updateButton(int index, String tableId, String status) {
        int buttonId = getResources().getIdentifier("btnTable" + index, "id", getPackageName());
        Button button = findViewById(buttonId);
        if (button != null) {
            button.setText("Table " + tableId); // Displaying "Table" for better UX
            button.setBackgroundColor("0".equals(status) ? Color.parseColor("#00802b") : Color.parseColor("#ff3333"));
            setButtonListeners(button, tableId, status);  // Include status when setting listeners
        }
    }

    private void setButtonListeners(Button button, String tableId, String status) {
        // We need to track if a long click was detected
        final boolean[] longClickDetected = {false};

        button.setOnClickListener(v -> {
            if (!longClickDetected[0]) {
                // All tables can initiate orders regardless of status.
                Intent intent = new Intent(TableActivity.this, OrderActivity.class);
                intent.putExtra("table_id", tableId);
                startActivity(intent);
            } else {
                // Reset long click detected flag
                longClickDetected[0] = false;
            }
        });

        button.setOnLongClickListener(v -> {
            // Only occupied tables (status = "1") should respond to long clicks for payments.
            if ("1".equals(status)) {
                Intent intent = new Intent(TableActivity.this, PaymentActivity.class);
                intent.putExtra("table_id", tableId);
                startActivity(intent);
                longClickDetected[0] = true;
                return true; // Consumes the long click
            } else {
                // Mark that a long click was detected but do nothing for green tables
                longClickDetected[0] = true;
                return true; // Consumes the long click
            }
        });
    }
}
