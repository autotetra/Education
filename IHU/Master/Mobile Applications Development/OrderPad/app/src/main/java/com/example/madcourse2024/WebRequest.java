package com.example.madcourse2024;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebRequest {
    private String url_to_fetch;
    private String threadResponse;

    WebRequest(String u) {
        url_to_fetch = u;
    }

    public String GetResponse() {
        String response;
        try {
            URL url = new URL(url_to_fetch);
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(false);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                try {
                    InputStream inputStream;
                    int status = urlConnection.getResponseCode();

                    if (status != HttpURLConnection.HTTP_OK)  {
                        inputStream = urlConnection.getErrorStream();
                    } else  {
                        inputStream = urlConnection.getInputStream();
                    }
                    response = readStream(inputStream);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException ioex) {
                response = ioex.toString();
                Log.e("ConnectActivity: ", "Java Exception: ", ioex);
            }
        } catch (MalformedURLException muex) {
            response = muex.toString();
            Log.e("ConnectActivity: ", "Malformed URL Exception ", muex);
        } catch (Exception e) {
            response = e.toString();
            Log.e("ConnectActivity: ", "UnknownException ", e);
        }
        return response;
    }


    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }


}
