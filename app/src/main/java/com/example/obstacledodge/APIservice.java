package com.example.obstacledodge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface APIservice {
    @GET("/tips")
    Call<List<String>> alltips();

    @GET("/tip")
    Call<Data> randomtip();

    @POST("/character")
    Call<Data> getrandomcharacter(@Body CharacterRequest request);

    @POST("/characters")
    Call<Data> getallcharacters(@Body CharacterRequest request);

    @GET("/scores")
    Call<Data> getScores();

    @GET("/word")
    Call<String> getRandomWord(@Query("length") int length);
}
