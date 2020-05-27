package com.ballJamesTask.reactjava.model;

/**
 * Class holding the final object which will be passed to Client
 * @author Mass
 */
public class FinalResult {
    private GameInformation gameInformation;

    public FinalResult(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
    }

    //This method is for mapping Controller to not complain
    public GameInformation getFinalResult() {
        return this.gameInformation;
    }
}
