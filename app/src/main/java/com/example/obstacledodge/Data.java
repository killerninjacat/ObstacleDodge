package com.example.obstacledodge;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("tip")
    String tip;
    @SerializedName("scores")
    List<Scoresreceive> scores;
    @SerializedName("character")
    List<Characterreceive> character;
    public Data(String tip)
    {
        this.tip=tip;
        this.scores=scores;
        this.character=character;
    }

    public List<Characterreceive> getCharacter() {
        return character;
    }

    public void setCharacter(List<Characterreceive> charcater) {
        this.character = charcater;
    }

    public List<Scoresreceive> getScores() {
        return scores;
    }

    public void setScores(List<Scoresreceive> scores) {
        this.scores = scores;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

}
