package com.example.orderpad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Xml;
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

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.rvProducts);
        // Initialize the adapter with an empty list of Product
        adapter = new ProductListAdapter(this, new ArrayList<Product>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String tableId = intent.getStringExtra("table_id");
        fetchProducts(tableId);
    }


    private void fetchProducts(String tableId) {
        String url = Constants.GET_COFFEE_DATA_URL;  // Use the constant URL
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
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
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("product".equals(tagName)) {
                            id = null;
                            name = null;
                            price = 0;
                        } else if ("id".equals(tagName)) {
                            id = parser.nextText().trim();
                        } else if ("name".equals(tagName)) {
                            name = parser.nextText().trim();
                        } else if ("price".equals(tagName)) {
                            price = Double.parseDouble(parser.nextText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("product".equals(tagName) && id != null && name != null) {
                            products.add(new Product(id, name, price));
                        }
                        break;
                }
                eventType = parser.next();
            }
            runOnUiThread(() -> updateRecyclerView(products));
        } catch (Exception e) {
            Log.e("OrderActivity", "Error parsing products XML", e);
            runOnUiThread(() -> Toast.makeText(OrderActivity.this, "Error parsing products!", Toast.LENGTH_SHORT).show());
        }
    }

    private void updateRecyclerView(List<Product> products) {
        adapter.updateData(products);
        adapter.notifyDataSetChanged();
    }
}
