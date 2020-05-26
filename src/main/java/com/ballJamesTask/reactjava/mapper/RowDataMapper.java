package com.ballJamesTask.reactjava.mapper;

import com.ballJamesTask.reactjava.model.Coordinate;
import com.ballJamesTask.reactjava.model.RowData;

import java.math.BigDecimal;

/**
 * Class for mapping String line of data to RowData object
 *
 * @see com.ballJamesTask.reactjava.model.RowData
 */
public final class RowDataMapper {

    private static final String GROUP_DATA_DELIMITER = ":";
    private static final String DATA_DELIMITER = ",";
    private static final String PLAYER_DELIMITER = ";";

    private static final int ROW_ID = 0;
    private static final int PLAYER_DATA_INDEX = 1;
    private static final int BALL_DATA_INDEX = 2;

    /**
     * Method for mapping line of data separated with regex to RowData object
     *
     * @param rawRowData incoming String line from txt file
     * @return mapped object with data for one line
     */
    public static RowData toRowData(String rawRowData) {
        RowData rowData = new RowData();
        String[] splitLine = rawRowData.split(GROUP_DATA_DELIMITER);

        rowData.setId(Integer.valueOf(splitLine[ROW_ID]));
        rowData.setBallCoordinate(mapToCoordinate(splitLine[BALL_DATA_INDEX].split(DATA_DELIMITER)));

        for (String line : splitLine[PLAYER_DATA_INDEX].split(PLAYER_DELIMITER)) {
            rowData.getCoordinateList().add(mapToCoordinate(line.split(DATA_DELIMITER)));
        }

        return rowData;
    }

    /**
     * Method for converting regular String containing Number to BigDecimal
     *
     * @param value to be converted to BigDecimal
     * @return new BigDecimal value
     */
    private static BigDecimal getBigDecimalValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Null value not allowed to be converted to BigDecimal.");
        }

        if (value.isEmpty()) {
            value = "0";
        }

        return new BigDecimal(value);
    }

    private static Coordinate mapToCoordinate(String[] data) {
        return new Coordinate(
                getBigDecimalValue(data[0]), //todo remove magic numbers
                getBigDecimalValue(data[1]),
                getBigDecimalValue(data[2]),
                getBigDecimalValue(data[3])
        );
    }
}
