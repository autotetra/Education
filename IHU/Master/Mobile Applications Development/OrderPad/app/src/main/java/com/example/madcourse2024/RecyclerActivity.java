package com.example.madcourse2024;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler);

        LinkedList<Product> ProductList = new LinkedList<>();
        ProductList.add( new Product(1, "Sony Playstation 3", 16.60F) );
        ProductList.add( new Product(2, "LG 42.5' Monitor", 140.2F));
        ProductList.add( new Product(3, "Intel Core I7 7200 CPU", 167.3F));
        ProductList.add( new Product(4, "Apple iPhone 7 - 32 GB", 500.3F));
        ProductList.add( new Product(5, "Samsung Galaxy Note 3", 600.3F));
        ProductList.add( new Product(6, "Xiaomi Redmi Note 4", 125.3F));
        ProductList.add( new Product(7, "S-Band Microwave Radio Link", 900.2F));
        ProductList.add( new Product(8, "Solid State Power Amplifier", 3000.0F));
        ProductList.add( new Product(9, "A laptop", 800.0F));
        ProductList.add( new Product(10, "Washing machine", 650.0F));

        RecyclerView mRecyclerView = findViewById(R.id.product_recycler);

        ProductListAdapter mAdapter = new ProductListAdapter(this, ProductList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}