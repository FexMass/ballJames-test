package com.ballJamesTask.reactjava.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class GameInformation {

    //public problem ?
    private List<HalfTimeInformation> halfTimeInformationList;
    private List<FootballPlayer> footballPlayerList;
    private HashMap<Integer, FootballPlayer> kurac;

    /**
     * @return
     */
    public List<HalfTimeInformation> getHalfTimeInformationList() {
        return halfTimeInformationList;
    }

    public void kurac(List<FootballPlayer> list) {
        Map<Integer, FootballPlayer> map = new HashMap();

        for (FootballPlayer fp : list) {
            map.put(fp.getId(), fp);
        }
    }

    /**
     * @param halfTimeInformationList
     */
    public void setHalfTimeInformationList(List<HalfTimeInformation> halfTimeInformationList) {
        this.halfTimeInformationList = halfTimeInformationList;
    }

    /**
     * @return
     */
    public List<FootballPlayer> getFootballPlayerList() {
        return footballPlayerList;
    }

    /**
     * @param footballPlayerList
     */
    public void setFootballPlayerList(List<FootballPlayer> footballPlayerList) {
        this.footballPlayerList = footballPlayerList;
    }


}
