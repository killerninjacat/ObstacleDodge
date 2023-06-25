package com.example.obstacledodge;

import com.google.gson.annotations.SerializedName;

public class CharacterRequest {
    @SerializedName("type")
    private String type;

    public CharacterRequest(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

