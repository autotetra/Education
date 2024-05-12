package com.example.madcourse2024;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SecondActivity extends AppCompatActivity {
    private EditText ed_remote_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        EditText ed_url = findViewById(R.id.ed_uri);
        ed_remote_content = findViewById(R.id.ed_result);

        Button cmd_down = findViewById(R.id.btn_fetch);
        cmd_down.setOnClickListener(v -> {
            String url = ed_url.getText().toString();

            RequestQueue queue = Volley.newRequestQueue(SecondActivity.this);
            StringRequest stringRequest = new StringRequest (Request.Method.GET, url,
                    response -> {
                        System.out.println("Fetch OK");
                        ed_remote_content.setText(response);
                    },
                    error -> {
                        System.out.println("error: " + error.toString());
                        ed_remote_content.setText(error.toString());
                    });
            queue.add(stringRequest);
        });

        Button cmd_down_async = findViewById(R.id.btn_fetch_async);
        cmd_down_async.setOnClickListener(v -> {
            RemoteContentTask RCT = new RemoteContentTask();
            String url = ed_url.getText().toString();
            RCT.execute(url);
        });
    }

    private class RemoteContentTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            WebRequest request = new WebRequest(urls[0]);
            return request.GetResponse();
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);
            ed_remote_content.setText(result);
        }
    }
}