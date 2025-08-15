package com.example.youtubevideosearchapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etSearch;
    Button btnSearch;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    VideoAdapter adapter;
    List<Video> videoList = new ArrayList<>();
    YouTubeApi api;

    String API_KEY = "AIzaSyAEk7F_bbhTFUWxwJXDn5fzxviwCJYk7EY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        adapter = new VideoAdapter(this, videoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(YouTubeApi.class);

        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString().trim();
            if(query.isEmpty()){
                Toast.makeText(this, "Enter search query", Toast.LENGTH_SHORT).show();
                return;
            }
            searchYouTube(query);
        });
    }

    private void searchYouTube(String query) {
        progressBar.setVisibility(View.VISIBLE);
        Call<YouTubeResponse> call = api.searchVideos(query, 10, API_KEY);
        call.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                progressBar.setVisibility(View.GONE);
                videoList.clear();

                if(response.body() != null && response.body().items != null){
                    for(YouTubeResponse.Item item : response.body().items){
                        Video video = new Video();
                        video.videoId = item.id.videoId;
                        video.title = item.snippet.title;
                        video.description = item.snippet.description;
                        video.publishedAt = item.snippet.publishedAt;
                        video.channelTitle = item.snippet.channelTitle;
                        video.thumbnailUrl = item.snippet.thumbnails.high.url;
                        videoList.add(video);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
