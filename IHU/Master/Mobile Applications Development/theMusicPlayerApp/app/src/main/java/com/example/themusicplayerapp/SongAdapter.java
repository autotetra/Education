package com.example.themusicplayerapp;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private static final String TAG = "SongAdapter";
    private final List<Song> songList;
    private final MediaPlayer mediaPlayer;
    private int currentPlayingPosition = -1;
    private SongViewHolder currentPlayingHolder = null;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
        this.mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(mp -> {
            if (currentPlayingHolder != null) {
                currentPlayingHolder.playButton.setVisibility(View.VISIBLE);
                currentPlayingHolder.pauseButton.setVisibility(View.GONE);
                currentPlayingPosition = -1;
                currentPlayingHolder = null;
            }
        });
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(holder.getAdapterPosition());
        holder.titleTextView.setText(song.getTitle());
        holder.artistTextView.setText(song.getArtist());

        holder.playButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (currentPlayingPosition != adapterPosition) {
                if (currentPlayingHolder != null) {
                    currentPlayingHolder.playButton.setVisibility(View.VISIBLE);
                    currentPlayingHolder.pauseButton.setVisibility(View.GONE);
                }
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(song.getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    holder.playButton.setVisibility(View.GONE);
                    holder.pauseButton.setVisibility(View.VISIBLE);
                    currentPlayingPosition = adapterPosition;
                    currentPlayingHolder = holder;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                mediaPlayer.start();
                holder.playButton.setVisibility(View.GONE);
                holder.pauseButton.setVisibility(View.VISIBLE);
            }
        });

        holder.pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                holder.playButton.setVisibility(View.VISIBLE);
                holder.pauseButton.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView artistTextView;
        public ImageButton playButton;
        public ImageButton pauseButton;

        public SongViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
            artistTextView = view.findViewById(R.id.artistTextView);
            playButton = view.findViewById(R.id.playButton);
            pauseButton = view.findViewById(R.id.pauseButton);
        }
    }
}
