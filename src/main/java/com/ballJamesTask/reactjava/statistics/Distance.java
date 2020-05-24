package com.ballJamesTask.reactjava.statistics;

import com.ballJamesTask.reactjava.service.Statistics;

import java.math.BigDecimal;

public class Distance implements Statistics {

    private String playerId;
    private BigDecimal distance;

    @Override
    public void calculate(String stringLine, String lastLine) {

    }
        //distance = sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getDistance() {
        return distance;
    }
}
