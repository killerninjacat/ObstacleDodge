package com.example.obstacledodge;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("tip")
    String tip;
    @SerializedName("scores")
    List<Scoresreceive> scores;
    @SerializedName("characters")
    List<Characterreceive> characters;
    @SerializedName("character")
    Characterreceive character;

    public Characterreceive getCharacter() {
        return character;
    }

    public void setCharacter(Characterreceive character) {
        this.character = character;
    }

    public Data(String tip, List<Scoresreceive> scores, List<Characterreceive> characters,Characterreceive character)
    {
        this.tip=tip;
        this.scores=scores;
        this.characters=characters;
        this.character=character;
    }

    public List<Characterreceive> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characterreceive> characters) {
        this.characters = characters;
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
