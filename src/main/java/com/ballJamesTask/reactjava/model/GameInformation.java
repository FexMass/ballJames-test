package com.ballJamesTask.reactjava.model;

import java.util.List;

/**
 *
 */
public class GameInformation {

    //public problem ?
    public List<HalfTimeInformation> halfTimeInformationList;
    public static List<FootballPlayer> footballPlayerList;

    /**
     *
     * @return
     */
    public List<HalfTimeInformation> getHalfTimeInformationList() {
        return halfTimeInformationList;
    }

    /**
     *
     * @param halfTimeInformationList
     */
    public void setHalfTimeInformationList(List<HalfTimeInformation> halfTimeInformationList) {
        this.halfTimeInformationList = halfTimeInformationList;
    }

    /**
     *
     * @return
     */
    public List<FootballPlayer> getFootballPlayerList() {
        return footballPlayerList;
    }

    /**
     *
     * @param footballPlayerList
     */
    public void setFootballPlayerList(List<FootballPlayer> footballPlayerList) {
        GameInformation.footballPlayerList = footballPlayerList;
    }


}
