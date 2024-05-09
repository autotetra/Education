package com.example.orderpad;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebRequest {
    private String url_to_fetch;

    public WebRequest(String u) {
        url_to_fetch = u;
    }

    public String GetResponse() {
        String response;
        try {
            URL url = new URL(url_to_fetch);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(false);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            try {
                InputStream inputStream;
                int status = urlConnection.getResponseCode();

                if (status != HttpURLConnection.HTTP_OK) {
                    inputStream = urlConnection.getErrorStream();
                } else {
                    inputStream = urlConnection.getInputStream();
                }
                response = readStream(inputStream);
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            response = "Malformed URL Exception: " + e.toString();
            Log.e("WebRequest", "Malformed URL Exception", e);
        } catch (IOException e) {
            response = "IOException: " + e.toString();
            Log.e("WebRequest", "IOException", e);
        } catch (Exception e) {
            response = "Exception: " + e.toString();
            Log.e("WebRequest", "Exception", e);
        }
//        Log.d("WebRequest", "Response received: " + response);

        return response;
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            Log.e("WebRequest", "Error reading stream", e);
            return "";
        }
    }
}
