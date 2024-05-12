package com.example.madcourse2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView img = findViewById(R.id.img_view);

        Bundle b = getIntent().getExtras();
        String Food = b.getString("food");
        String class_str = b.getString("class");
        Integer Day = b.getInt("Day");


        if (Food.equals("gemista")) {
            img.setImageResource(R.drawable.gemista);
        } else if (Food.equals("g")) {
            img.setImageResource(R.drawable.g_icon);
        } else if (Food.equals("fb")) {
            img.setImageResource(R.drawable.fb_icon);
        } else {
            img.setImageResource(R.drawable.recipe_navi_left);
        }

        TextView txt_bs = findViewById(R.id.txt_bs);
        txt_bs.setText(class_str);

        Button btn_img = findViewById(R.id.img_btn);
        btn_img.setOnClickListener(
                v -> {
                    ImageActivity.this.finish();
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView txt_bs = findViewById(R.id.txt_bs);
        String str = txt_bs.getText().toString();

        outState.putString("strToSave", str);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}