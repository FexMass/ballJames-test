package com.ballJamesTask.reactjava.mapper;

import com.ballJamesTask.reactjava.model.RowData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class RowDataMapperTest {

    @Test
    void shouldMapDatatoRowData() {
        String input = "5:1,2,0,3;1,2,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;:3,4,5,.000";
        RowData rowData = RowDataMapper.toRowData(input);
        assertEquals(30, rowData.getPlayerCoordinateList().size());
    }

    void shouldThrowExceptionBecauseInvalidNumberOfDoubleDot() {
        String input = "5:1,2,0,3;1,2,,.000;,,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;,,,.000;:3,4,5,.000";
//        assertEquals();
    }

    @Test
    void getBigDecimalValue() {
    }

    @Test
    void mapToCoordinate() {
    }

}