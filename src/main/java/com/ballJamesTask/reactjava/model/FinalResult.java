package com.ballJamesTask.reactjava.model;

public class FinalResult {
    private GameInformation gameInformation;


    public FinalResult(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
    }

    public GameInformation getFinalResult() {
        return this.gameInformation;
    }
}
