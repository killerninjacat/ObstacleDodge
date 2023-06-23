package com.example.obstacledodge;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIservice {
    @GET("/tips")
    Call<List<String>> alltips();

    @GET("/tip")
    Call<Data> randomtip();

    @POST("/characters")
    Call<List<Character>> getcharacters(@Query("type") String type);

    @POST("/character")
    Call<Data> getrandomcharacter();

    @GET("/scores")
    Call<Data> getScores();

    @GET("/word")
    Call<String> getRandomWord(@Query("length") int length);
}
