package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.model.FootballPlayer;
import com.ballJamesTask.reactjava.model.GameInformation;
import com.ballJamesTask.reactjava.model.HalfTimeInformation;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for parsing XML file and getting data from it to store it.
 * Class extends DefaultHandler Class to override certain needed methods for parsing.
 *
 * @author Mass
 * @see DefaultHandler
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
     * Overridden method which is invoked when parsing begins and constructing new GameInformation object
     *
     * @see GameInformation
     */
    @Override
    public void startDocument() {
        gameInformation = new GameInformation();
    }

    /**
     * Overridden Method receives characters with boundaries.
     * Converting those characters into String.
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length);
    }

    /**
     * Overridden method which is invoked when the parsing begins for an element.
     *
     * @param qName holds the name of the tag in the XML
     * @param attr  holds the value if needed from attribute of a XML tag
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
            footballPlayer.setTeamId(attr.getValue("teamId"));
            gameInformation.getFootballPlayerList().add(footballPlayer);
        }
    }

    /**
     * Overridden method which is invoked when the parsing ends for an element.
     *
     * @param qName holds the name of the tag in the XML
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case START:
                getLatestHalfTime(gameInformation.getHalfTimeInformationList()).setStart(elementValue);
                break;
            case END:
                getLatestHalfTime(gameInformation.getHalfTimeInformationList()).setEnd(elementValue);
                break;
            case LENGTH:
                getLatestHalfTime(gameInformation.getHalfTimeInformationList()).setLength(elementValue);
                break;
            case WIDTH:
                getLatestHalfTime(gameInformation.getHalfTimeInformationList()).setWidth(elementValue);
                break;
            case LOCATION:
                getLatestHalfTime(gameInformation.getHalfTimeInformationList()).setLocation(elementValue);
                break;
            case SHIRT_NUMBER:
                latestPlayer().setShirtNumber(elementValue);
                break;
        }
    }

    /**
     * A method to retrieve the latest encountered halftime information
     *
     * @return latest halftime information
     * @apiNote This isn't Thread safe
     */
    private HalfTimeInformation getLatestHalfTime(List<HalfTimeInformation> halfTimeInformationList) {
        int latestOtherInformationIndex = halfTimeInformationList.size() - 1;
        return halfTimeInformationList.get(latestOtherInformationIndex);
    }

    /**
     * A method to retrieve the latest encountered football player
     *
     * @return latest football player
     * @apiNote This isn't Thread safe
     */
    private FootballPlayer latestPlayer() {
        List<FootballPlayer> footballPlayerList = gameInformation.getFootballPlayerList();
        int latestFootballPlayerIndex = footballPlayerList.size() - 1;
        return footballPlayerList.get(latestFootballPlayerIndex);
    }

    /**
     * Method for triggering SAX parser instance and start of parsing process
     *
     * @param inputFile XML file to be parsed
     * @return complete structured object based on XML
     */
    @SneakyThrows
    GameInformation getGameInformation(MultipartFile inputFile) {
        //TODO file from client upload
//        File file = new File(System.getProperty("java.io.tmpdir"));
        saxParser.parse(inputFile.getInputStream(), this);
        //optional
//           gameInformation.playersMap(gameInformation.getFootballPlayerList());
        return gameInformation;
    }
}