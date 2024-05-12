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

    public WebRequest(String endpoint) {
        this.url_to_fetch = endpoint;
    }

    public String GetResponse() {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(this.url_to_fetch);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(false);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream;
            int status = urlConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getErrorStream();
            } else {
                inputStream = urlConnection.getInputStream();
            }
            return readStream(inputStream);
        } catch (MalformedURLException e) {
            Log.e("WebRequest", "Malformed URL Exception", e);
            return "Malformed URL Exception: " + e.toString();
        } catch (IOException e) {
            Log.e("WebRequest", "IOException", e);
            return "IOException: " + e.toString();
        } catch (Exception e) {
            Log.e("WebRequest", "Exception", e);
            return "Exception: " + e.toString();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }


    private String readStream(InputStream is) {
        try (ByteArrayOutputStream bo = new ByteArrayOutputStream()) {
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
