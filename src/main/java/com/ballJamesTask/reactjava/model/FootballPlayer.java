package com.ballJamesTask.reactjava.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
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
     *
     * @return
     */
    public BigDecimal getMaxSpeed() {
        return maxSpeed == null ? BigDecimal.valueOf(0) : maxSpeed;
    }

    /**
     *
     * @return
     */
    public BigDecimal getLengthRun() {
        return lengthRun == null ? BigDecimal.valueOf(0) : lengthRun;
    }
}
