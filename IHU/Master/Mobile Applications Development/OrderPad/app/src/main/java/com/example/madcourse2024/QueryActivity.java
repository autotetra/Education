package com.example.madcourse2024;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QueryActivity extends AppCompatActivity {

    private SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        myDB = openOrCreateDatabase("products_db", MODE_PRIVATE, null);

        Button create_db = findViewById(R.id.cmd_create_table);
        create_db.setOnClickListener( v-> {
            myDB.execSQL("CREATE TABLE IF NOT EXISTS products " +
                    "(product_id integer primary key autoincrement, " +
                    "product_title text not null, " +
                    "product_price real)");
        });

        Button insert_cmd = findViewById(R.id.cmd_insert);
        insert_cmd.setOnClickListener( v-> {
            EditText ed_id = findViewById(R.id.ed_id);
            String product_id = ed_id.getText().toString();

            EditText ed_title = findViewById(R.id.ed_title);
            String product_title = ed_title.getText().toString();

            EditText ed_price = findViewById(R.id.ed_price);
            String product_price = ed_price.getText().toString();

            myDB.execSQL("INSERT INTO products (product_title, product_price) VALUES (?, ?)",
                    new Object[] { product_title, product_price });
        });

        Button select_cmd = findViewById(R.id.cmd_select);
        select_cmd.setOnClickListener( v-> {

            Cursor c = myDB.rawQuery("SELECT * FROM products", null);
            if (c.moveToFirst()) {
                int Column_ID = c.getColumnIndex("product_id");
                int Column_Title = c.getColumnIndex("product_title");
                int Column_Price = c.getColumnIndex("product_price");

                // Check if our result was valid.
                c.moveToFirst();
                // Loop through all Results
                do {
                    int product_id = c.getInt(Column_ID);
                    String product_title = c.getString(Column_Title);
                    float product_price = c.getFloat(Column_Price);

                    System.out.println(product_id + ", " + product_title + ", " + product_price);
                } while (c.moveToNext());

                c.close();
            }
        });

        Button update_cmd = findViewById(R.id.cmd_update);
        update_cmd.setOnClickListener( v-> {
            EditText ed_id = findViewById(R.id.ed_id);
            String product_id = ed_id.getText().toString();

            EditText ed_title = findViewById(R.id.ed_title);
            String product_title = ed_title.getText().toString();

            EditText ed_price = findViewById(R.id.ed_price);
            String product_price = ed_price.getText().toString();

            myDB.execSQL("UPDATE products SET product_title = ?, product_price = ? WHERE product_id = ?",
                    new Object[] { product_title, product_price, product_id });
        });

        Button delete_cmd = findViewById(R.id.cmd_delete);
        delete_cmd.setOnClickListener( v-> {
            EditText ed_id = findViewById(R.id.ed_id);
            String product_id = ed_id.getText().toString();

            myDB.execSQL("DELETE FROM products WHERE product_id = ?",
                    new Object[] { product_id });
        });
        /*

myDB.execSQL("DROP TABLE IF EXISTS products");
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS products " +
                            "(product_id integer primary key autoincrement, " +
                            "product_title text not null, " +
                            "product_price real)");

                    myDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                            "customers " +
                            "(customer_id integer primary key autoincrement, " +
                            "customer_name text not null, " +
                            "customer_city text)");

                    myDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                            "suppliers " +
                            "(supplier_id integer primary key autoincrement, " +
                            "supplier_name text not null, " +
                            "supplier_city text)");

                    myDB.execSQL("INSERT INTO products " +
                            "(product_title, product_price) VALUES " +
                            "('Sony Playstation 3', '16.60')," +
                            "('LG 42.5 Monitor', '167.30')," +
                            "('Intel Core I7 7200 CPU', '500.30')," +
                            "('Apple iPhone 7 - 32 GB', '600.30')," +
                            "('Samsung Galaxy Note 3', '140.20')," +
                            "('Xiaomi Redmi Note 4', '125.30')," +
                            "('S-Band Microwave Radio Link', '900.20')," +
                            "('Solid State Power Amplifier', '3000.00')," +
                            "('Philips Headphones', '14.25')," +
                            "('Logitech microphone', '17.90')"
                    );
         */
    }
}