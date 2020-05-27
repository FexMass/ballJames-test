package com.ballJamesTask.reactjava.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class for storing each half time information
 * @author Mass
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HalfTimeInformation {

    private String start;
    private String end;
    private String length;
    private String width;
    private String location;
    private String value;
}
