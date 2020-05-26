package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.model.FootballPlayer;
import com.ballJamesTask.reactjava.model.GameInformation;
import com.ballJamesTask.reactjava.model.HalfTimeInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Service
public class XmlParserService extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(XmlParserService.class);

    private static final String START = "Start";
    private static final String END = "End";
    private static final String LENGTH = "Length";
    private static final String WIDTH = "Width";
    private static final String LOCATION = "Location";
    private static final String VALUE = "value";
    private static final String SHIRT_NUMBER = "ShirtNumber";

    private static final String SESSIONS = "Sessions";
    private static final String SESSION = "Session";
    private static final String PLAYERS = "Players";
    private static final String PLAYER = "Player";

    private GameInformation gameInformation;
    private String elementValue;

    private final SAXParser saxParser;

    public XmlParserService() throws ParserConfigurationException, SAXException {
        this.saxParser = SAXParserFactory.newInstance().newSAXParser();
    }

    /**
     *
     */
    @Override
    public void startDocument() {
        gameInformation = new GameInformation();
    }

    /**
     *
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length);
    }

    /**
     *
     * @param uri
     * @param lName
     * @param qName
     * @param attr
     */
    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        if (SESSIONS.equals(qName)) {
            gameInformation.setHalfTimeInformationList(new ArrayList<>());
        } else if (SESSION.equals(qName)) {
            gameInformation.getHalfTimeInformationList().add(new HalfTimeInformation());
        } else if (PLAYERS.equals(qName)) {
            gameInformation.setFootballPlayerList(new ArrayList<>());
        } else if (PLAYER.equals(qName)) {
            FootballPlayer footballPlayer = new FootballPlayer();
            footballPlayer.setId(Integer.parseInt(attr.getValue("id")));
            footballPlayer.setTeamId("teamId");
            gameInformation.getFootballPlayerList().add(footballPlayer);
        }
    }

    /**
     *
     * @param uri
     * @param localName
     * @param qName
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case START:
                latestInformation().setStart(elementValue);
                break;
            case END:
                latestInformation().setEnd(elementValue);
                break;
            case LENGTH:
                latestInformation().setLength(elementValue);
                break;
            case WIDTH:
                latestInformation().setWidth(elementValue);
                break;
            case LOCATION:
                latestInformation().setLocation(elementValue);
                break;
            case VALUE:
                latestInformation().setValue(elementValue);
                break;
            case SHIRT_NUMBER:
                latestPlayer().setShirtNumber(elementValue);
                break;
        }
    }

    /**
     *
     * @return
     */
    private HalfTimeInformation latestInformation() {
        List<HalfTimeInformation> halfTimeInformationList = gameInformation.getHalfTimeInformationList();
        int latestOtherInformationIndex = halfTimeInformationList.size() - 1;
        return halfTimeInformationList.get(latestOtherInformationIndex);
    }

    /**
     *
     * @return
     */
    private FootballPlayer latestPlayer() {
        List<FootballPlayer> footballPlayerList = gameInformation.getFootballPlayerList();
        int latestFootballPlayerIndex = footballPlayerList.size() - 1;
        return footballPlayerList.get(latestFootballPlayerIndex);
    }

   GameInformation getGameInformation(String inputFile) {
       try {
           //TODO file from client upload
           saxParser.parse(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("20200223_Heracles_vs_Ajax_20200223_Heracles_vs_Ajax[8730].xml")), this);
           gameInformation.playersMap(gameInformation.getFootballPlayerList());
       } catch (Exception e) {
           e.printStackTrace();
       }
       return gameInformation;
   }
}