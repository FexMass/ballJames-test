package com.ballJamesTask.reactjava.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Class representing each football player in the match
 * @author Mass
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FootballPlayer {

    private int id;
    private String teamId;
    private String shirtNumber;
    private BigDecimal maxSpeed;
    private BigDecimal lengthRun;
    public static MathContext mathContext = new MathContext(7);

    /**
     * Method for avoiding null pointer
     * @return maxSpeed or 0 if maxSpeed is null
     */
    public BigDecimal getMaxSpeed() {
        return maxSpeed == null ? BigDecimal.valueOf(0) : maxSpeed;
    }

    /**
     * Method for avoiding null pointer
     * @return lengthRun or 0 if lengthRun is null
     */
    public BigDecimal getLengthRun() {
        return lengthRun == null ? BigDecimal.valueOf(0) : lengthRun;
    }
}
