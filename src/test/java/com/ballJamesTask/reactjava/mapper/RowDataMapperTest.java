package com.ballJamesTask.reactjava.mapper;

import com.ballJamesTask.reactjava.model.Coordinate;
import com.ballJamesTask.reactjava.model.RowData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RowData mapper Class
 * @author Mass
 */
class RowDataMapperTest {

    @Test
    void shouldMapDataToRowData() {
        String input = "5:1,2,0,3;1,2,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;" +
                ",,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;" +
                ",,,.000;,,,.000;,,,.000;,,,.000;:3,4,5,.000";
        RowData rowData = RowDataMapper.toRowData(input);
        assertEquals(30, rowData.getPlayerCoordinateList().size());
    }

    @Test
    void shouldThrowExceptionBecauseInvalidNumberOfDoubleDot() {
        String input = "5:1,2,0,3;1,2,,.000;,,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;" +
                ",,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;" +
                ",,,.000;,,,.000;:3,4,5,.000";

        RowData rowData = RowDataMapper.toRowData(input);
        assertEquals(29, rowData.getPlayerCoordinateList().size());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RowDataMapper.toRowData(""));
        assertEquals("input line not valid", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionBecauseNullValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RowDataMapper.getBigDecimalValue(null));
        assertEquals("Null value not allowed to be converted to BigDecimal.", exception.getMessage());
    }

    @Test
    void shouldGetBigDecimalValueToZero() {
        String value = "";
        RowDataMapper.getBigDecimalValue(value);
        assertEquals(BigDecimal.valueOf(0), RowDataMapper.getBigDecimalValue(value));
    }

    @Test
    void shouldGetProperBigDecimalValueWithPrecision() {
        String value = "13.9010101";
        RowDataMapper.getBigDecimalValue(value);
        assertEquals(BigDecimal.valueOf(13.901), RowDataMapper.getBigDecimalValue(value));
    }

    @Test
    void shouldReturnNewCoordinate() {
        RowData rowData = new RowData();
        Coordinate coordinate= new Coordinate(BigDecimal.valueOf(3.5), BigDecimal.valueOf(12.4), BigDecimal.valueOf(0.00),BigDecimal.valueOf(1.5));
        rowData.getPlayerCoordinateList().add(coordinate);
        assertEquals(rowData.getPlayerCoordinateList().get(0), coordinate);
    }

}