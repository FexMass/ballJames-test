package com.ballJamesTask.reactjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Class representing one location(x, y, z) and velocity [m/s] of player or a ball
 * @author Mass
 */
@Getter
@Setter
@AllArgsConstructor
public class Coordinate {

    private BigDecimal xPosition;
    private BigDecimal yPosition;
    private BigDecimal zPosition;
    private BigDecimal velocity;

    /**
     * Method for avoiding null pointer
     * @return velocity or 0 if velocity is null
     */
    public BigDecimal getVelocity() {
        return velocity == null ? BigDecimal.valueOf(0) : velocity;
    }
}
