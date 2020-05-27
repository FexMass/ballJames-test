package com.ballJamesTask.reactjava.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing each half time and football player for specific match taken from XML
 * @author Mass
 * @see com.ballJamesTask.reactjava.service.XmlParserService
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GameInformation {

    private List<HalfTimeInformation> halfTimeInformationList;
    private List<FootballPlayer> footballPlayerList;
    private Map<Integer, FootballPlayer> footballPlayerMap = new HashMap<>();

    /**
     * Method for filling HashMap with data from the list
     * @param list to create HashMap from
     * @apiNote This is optional to use
     */
    public void playersMap(List<FootballPlayer> list) {
        for (FootballPlayer fp : list) {
            footballPlayerMap.put(fp.getId(), fp);
        }
    }
}
