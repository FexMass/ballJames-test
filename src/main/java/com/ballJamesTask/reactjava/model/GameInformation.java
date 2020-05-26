package com.ballJamesTask.reactjava.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GameInformation {

    private List<HalfTimeInformation> halfTimeInformationList;
    private List<FootballPlayer> footballPlayerList;
    private Map<Integer, FootballPlayer> footballPlayerMap = new HashMap<>();

    public void playersMap(List<FootballPlayer> list) {
        for (FootballPlayer fp : list) {
            footballPlayerMap.put(fp.getId(), fp);
        }
    }
}
