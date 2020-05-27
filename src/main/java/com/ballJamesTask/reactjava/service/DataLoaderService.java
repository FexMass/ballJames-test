package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.mapper.RowDataMapper;
import com.ballJamesTask.reactjava.model.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

/**
 * Service for loading all mapped data and then calculating certain statistics needed before sending
 * final object to the Client to display them
 * @author Mass
 */
@Service
public class DataLoaderService {

    private XmlParserService xmlParserService;
    private GameInformation gameInformation;
    private RowData tempRowData;

    public DataLoaderService(XmlParserService xmlParserService) {
        this.xmlParserService = xmlParserService;
    }

    /**
     * Method where XML parser is being called and after that CSV file is being called for reading
     * @param txtPath CSV file
     * @param xmlPath XML file
     * @return final object filled with data
     */
    public FinalResult getAllMatchData(String txtPath, String xmlPath) {

        gameInformation = xmlParserService.getGameInformation(xmlPath);

        readFileByLine();

        return new FinalResult(gameInformation);
    }

    /**
     * Setting maximum speed of a football player [m/s]
     * @param rowData current line of data
     * @see RowData
     */
    private void updateMaxSpeed(RowData rowData) {
        int index = 0;
        for (FootballPlayer fp : gameInformation.getFootballPlayerList()) {
            fp.setMaxSpeed(calculateMaxSpeed(fp.getMaxSpeed(), rowData.getCoordinateList().get(index).getVelocity()));
            index++;
        }
    }

    /**
     * Method for constantly updating (adding) distance, ran by every player on the field during match
     * @param lastRowData previous data which holds (x1, y1) coordinates
     * @param newRowData current data which holds (x2, y2) coordinates
     * @see Coordinate
     */
    private void updateLengthRun(RowData lastRowData, RowData newRowData) {
        int index = 0;
        for (FootballPlayer fp : gameInformation.getFootballPlayerList()) {
            fp.setLengthRun(fp.getLengthRun().add((calculatePlayerTravel(
                    lastRowData.getCoordinateList().get(index).getXPosition(),
                    lastRowData.getCoordinateList().get(index).getYPosition(),
                    newRowData.getCoordinateList().get(index).getXPosition(),
                    newRowData.getCoordinateList().get(index).getYPosition()
                    ))));
            index++;
        }
    }

    /**
     * Method for reading each line in CSV file and mapping required data
     */
    private void readFileByLine() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("20200223_Heracles_vs_Ajax_20200223_Heracles_vs_Ajax - Copy.txt");
        Scanner sc = new Scanner(Objects.requireNonNull(inputStream), "UTF-8");

        boolean flag = false;

        while (sc.hasNextLine()) {
            //TODO Regex Pattern line check
            String line = sc.nextLine();
            RowData currentData = RowDataMapper.toRowData(line);
            updateMaxSpeed(currentData);
            if (flag) {
                updateLengthRun(getTempRowData(), currentData);
                flag = false;
            } else {
                saveTempRowData(currentData);
                flag = true;
            }
        }
    }

    /**
     * Method for setting last RowData
     * @param rowData last RawData
     */
    private void saveTempRowData(RowData rowData) {
        this.tempRowData = rowData;
    }

    /**
     * Method for getting last RowData
     * @return last saved RowData
     */
    private RowData getTempRowData() {
        return this.tempRowData;
    }

    /**
     * Method for calculating distance of each player based on 2 data of coordinates (x1, y1)(x2, y2)
     * @param xPreviousPosition last RowData x1 position
     * @param yPreviousPosition last RowData y1 position
     * @param xNextPosition current RowData x2 position
     * @param yNextPosition current RowData y2 position
     * @return calculated distance [meters]
     */
    private BigDecimal calculatePlayerTravel(BigDecimal xPreviousPosition, BigDecimal yPreviousPosition,
                                             BigDecimal xNextPosition, BigDecimal yNextPosition) {

        return new BigDecimal(Math.sqrt(
                (xPreviousPosition.doubleValue() - xNextPosition.doubleValue()) *
                        (xPreviousPosition.doubleValue() - xNextPosition.doubleValue()) +
                        (yPreviousPosition.doubleValue() - yNextPosition.doubleValue()) *
                        (yPreviousPosition.doubleValue() - yNextPosition.doubleValue())
        ), FootballPlayer.mathContext).round(FootballPlayer.mathContext);
    }

    /**
     * Method for comparing maxSpeed of each player
     * MaxSpeed is only updated if its bigger then previous
     * @param oldSpeed last maxSpeed of player
     * @param newSpeed new maxSpeed of player
     * @return bigger maxSpeed
     */
    private BigDecimal calculateMaxSpeed(BigDecimal oldSpeed, BigDecimal newSpeed) {
        return oldSpeed.compareTo(newSpeed) > 0 ? oldSpeed : newSpeed;
    }
}
