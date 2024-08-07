package com.example.themusicplayerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new FetchPlaylistTask().execute("http://mad.mywork.gr/get_playlist.php?t=1546");
    }

    private class FetchPlaylistTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            WebRequest webRequest = new WebRequest();
            Log.d(TAG, "doInBackground: Fetching data from URL");
            return webRequest.makeGetRequest(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: Data fetched");
            XMLParser parser = new XMLParser();
            List<Song> songs = parser.parse(result);
            songAdapter = new SongAdapter(songs);
            recyclerView.setAdapter(songAdapter);
        }
    }
}
