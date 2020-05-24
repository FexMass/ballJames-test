package com.ballJamesTask.reactjava.statistics;

import com.ballJamesTask.reactjava.service.Statistics;

import java.math.BigDecimal;

public class Velocity implements Statistics {

    private String playerId;
    private BigDecimal distance;

    @Override
    public void calculate(String stringLine, String lastLine) {
        //speed for each player
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
