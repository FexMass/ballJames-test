package com.ballJamesTask.reactjava.mapper;

import com.ballJamesTask.reactjava.model.Coordinate;
import com.ballJamesTask.reactjava.model.RowData;
import com.sun.javaws.exceptions.InvalidArgumentException;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Class for mapping String line of data to RowData object
 * @author Mass
 * @see RowData
 */
public final class RowDataMapper {

    private static final String GROUP_DATA_DELIMITER = ":";
    private static final String DATA_DELIMITER = ",";
    private static final String PLAYER_DELIMITER = ";";

    private static final int ROW_ID = 0;
    private static final int PLAYER_DATA_INDEX = 1;
    private static final int BALL_DATA_INDEX = 2;

    private static final int X_POSITION_INDEX = 0;
    private static final int Y_POSITION_INDEX = 1;
    private static final int Z_POSITION_INDEX = 2;
    private static final int VELOCITY_INDEX = 3;

    private RowDataMapper () {
        throw new UnsupportedOperationException("Creating objects from this class not allowed.");
    }

    /**
     * Method for mapping line of data separated with regex to RowData object
     * @param rawRowData incoming String line from txt file
     * @return mapped object with data for one line
     */
    @SneakyThrows
    public static RowData toRowData(String rawRowData) {
        RowData rowData = new RowData();
        String[] splitLine = rawRowData.split(GROUP_DATA_DELIMITER);

        if (splitLine.length != 3) {
           throw new IllegalArgumentException("input line not valid");
        }

        rowData.setId(Integer.valueOf(splitLine[ROW_ID]));
        rowData.setBallCoordinate(mapToCoordinate(splitLine[BALL_DATA_INDEX].split(DATA_DELIMITER)));

        for (String line : splitLine[PLAYER_DATA_INDEX].split(PLAYER_DELIMITER)) {
            if (line.split(DATA_DELIMITER).length != 4) {
                continue;
            }
            rowData.getPlayerCoordinateList().add(mapToCoordinate(line.split(DATA_DELIMITER)));
        }

        return rowData;
    }

    /**
     * Method for converting regular String containing Number to BigDecimal
     * @param value to be converted to BigDecimal
     * @return new BigDecimal value
     */
    public static BigDecimal getBigDecimalValue(String value) {

        if (value == null) {
            throw new IllegalArgumentException("Null value not allowed to be converted to BigDecimal.");
        }
        if (value.isEmpty()) {
            value = "0";
        }

        return new BigDecimal(value, new MathContext(5));
    }

    /**
     * Method for getting new Coordinate from provided data
     * @param data incoming data with values x,y,z, velocity
     * @return new Coordinate object
     * @see Coordinate
     */
    public static Coordinate mapToCoordinate(String[] data) {
        return new Coordinate(
                getBigDecimalValue(data[X_POSITION_INDEX]),
                getBigDecimalValue(data[Y_POSITION_INDEX]),
                getBigDecimalValue(data[Z_POSITION_INDEX]),
                getBigDecimalValue(data[VELOCITY_INDEX])
        );
    }
}