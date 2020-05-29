package com.ballJamesTask.reactjava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DataLoader service
 * @author Mass
 */
@SpringBootTest
class DataLoaderServiceTest {

    @Autowired
    DataLoaderService dataLoaderService;

    @Test
    void shouldCalculateMaxSpeed() {
        MathContext mathContext = new MathContext(3);
        BigDecimal oldSpeed = new BigDecimal(3.55, mathContext).round(mathContext);
        BigDecimal newSpeed = new BigDecimal(3.55, mathContext).round(mathContext);

        assertEquals(BigDecimal.valueOf(3.55),dataLoaderService.calculateMaxSpeed(oldSpeed, newSpeed));

        BigDecimal oldSpeed1 = new BigDecimal(3, mathContext).round(mathContext);
        BigDecimal newSpeed1 = new BigDecimal(3.55, mathContext).round(mathContext);

        assertEquals(BigDecimal.valueOf(3.55),dataLoaderService.calculateMaxSpeed(oldSpeed1, newSpeed1));

        BigDecimal oldSpeed2 = new BigDecimal(6, mathContext).round(mathContext);
        BigDecimal newSpeed2 = new BigDecimal(3, mathContext).round(mathContext);

        assertEquals(BigDecimal.valueOf(6),dataLoaderService.calculateMaxSpeed(oldSpeed2, newSpeed2));
    }

    @Test
    void shouldCalculatePlayerTravelRun() {
        MathContext mathContext = new MathContext(3);

        BigDecimal xPreviousPosition = new BigDecimal(3, mathContext).round(mathContext);
        BigDecimal yPreviousPosition = new BigDecimal(3, mathContext).round(mathContext);
        BigDecimal xNextPosition = new BigDecimal(5, mathContext).round(mathContext);
        BigDecimal yNextPosition = new BigDecimal(5, mathContext).round(mathContext);

        assertEquals(BigDecimal.valueOf(2.83),dataLoaderService.calculatePlayerTravel(xPreviousPosition, yPreviousPosition, xNextPosition, yNextPosition));
    }
}