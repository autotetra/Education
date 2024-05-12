package com.example.madcourse2024;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String test_str;
    private String radio_selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MAD 2024");

        //String [] planets_str = {"Earth", "Mars", "Mercury"};
        String [] planets_str = getResources().getStringArray(R.array.planets_key);

        Spinner spin = findViewById(R.id.spn_id);

        ArrayAdapter<CharSequence> adapter_options = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                planets_str);
        adapter_options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter_options);

        spin.setSelection(2);

        EditText ed = findViewById(R.id.edt_address);
        ed.setText(R.string.red_key);

        // ed.setTextColor(Color.parseColor("#cccccc"));
        ed.setTextColor(Color.BLACK);

        CheckBox ch = findViewById(R.id.ch_remember);
        ch.setEnabled(true);
        ch.setChecked(true);

        RadioGroup rg = findViewById(R.id.rg_id);
        int sel_radio_id = rg.getCheckedRadioButtonId();
        RadioButton selected_radio_button = findViewById(sel_radio_id);
        radio_selection = selected_radio_button.getText().toString();

        String checked_val = "";
        if (ch.isChecked()) {
            checked_val = "Checked";
        } else {
            checked_val = "Not checked";
        }
        final String cval = checked_val;

        test_str = "a value";
        test_str = "another val";

        Button btn = findViewById(R.id.btn_ok);
        btn.setOnLongClickListener(
                v -> {
                    int a = 5;
                    Toast.makeText(MainActivity.this, "the buttonwaspressed", Toast.LENGTH_LONG).show();
                    return false;
                }
        );

        btn.setOnClickListener( v-> {
            ConfirmDelete cd = new ConfirmDelete();
            cd.show(getSupportFragmentManager(), "delete");
        });

        Button btn_intent = findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(
                v -> {
                    EditText etxt = findViewById(R.id.edt_address);
                    String user_input = etxt.getText().toString();

                    Intent intent = new Intent(this, RecyclerActivity.class);

                    Bundle b = new Bundle();
                    b.putString("food", user_input);
                    b.putString("class", "MAD-2024");
                    b.putInt("Day", 30);

                    intent.putExtras(b);

                    startActivity(intent);
                }
        );

        ImageButton imb = findViewById(R.id.btn_ib);
        registerForContextMenu(imb);

        ImageView iv_gr = findViewById(R.id.gr_flag);
        iv_gr.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(MainActivity.this, iv_gr);
            popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item-> {
                int id= item.getItemId();
                if(id == R.id.id_menu_copy) {
                    ed.setText("You clicked on Copy (Popup)");
                    return true;
                } else if (id== R.id.id_menu_paste) {
                    ed.setText("You clicked on Paste (Popup)");
                    return true;
                } else if(id== R.id.id_menu_cut) {
                    ed.setText("You clicked on Cut (Popup)");
                    return true;
                }
                return false;
            });
            popup.show();
                }
        );

        registerForContextMenu(ed);
    }

    /////// Main Menu - attached to title bar
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_process_table, menu);
        if(menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    /////// Main Menu Item Click Handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        EditText ed = findViewById(R.id.edt_address);

        if (id == R.id.id_new_order) {
            ed.setText("New Order (from Action Bar)");

        } else if (id == R.id.id_payment) {
            ed.setText("You clicked payment");

        } else if (id == R.id.id_pay_cash) {
            ed.setText("The customer will pay with cash");

        } else if (id == R.id.id_pay_credit) {
            ed.setText("The customer will pay with a credit card");
        }

        return super.onOptionsItemSelected(item);
    }


    /////// Context Menu - attached to a View
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        if (v.getId() == R.id.btn_ib) {
            inflater.inflate(R.menu.main_menu, menu);
        } else if (v.getId() == R.id.edt_address) {
            inflater.inflate(R.menu.menu_process_table, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        EditText ed = findViewById(R.id.edt_address);
        if(id == R.id.id_menu_copy) {
            ed.setText("You clicked on Copy");

        } else if (id== R.id.id_menu_paste) {
            ed.setText("You clicked on Paste");

        } else if(id== R.id.id_menu_cut) {
            ed.setText("You clicked on Cut");
        } else if(id== R.id.id_new_order) {
            ed.setText("Newe order (from Context Menu)");
        }
        return super.onContextItemSelected(item);
    }
}