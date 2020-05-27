package com.ballJamesTask.reactjava.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing single line from CSV file
 * @author Mass
 */
@Getter
@Setter
@NoArgsConstructor
public class RowData {

    private int id;
    private List<Coordinate> coordinateList = new ArrayList<>();
    private Coordinate ballCoordinate;
}
