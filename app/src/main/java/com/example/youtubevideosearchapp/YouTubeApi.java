package com.example.youtubevideosearchapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeApi {
    @GET("search?part=snippet&type=video")
    Call<YouTubeResponse> searchVideos(
            @Query("q") String query,
            @Query("maxResults") int maxResults,
            @Query("key") String apiKey
    );
}
