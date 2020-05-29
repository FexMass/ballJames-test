package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.mapper.RowDataMapper;
import com.ballJamesTask.reactjava.model.*;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Service for loading all mapped data and then calculating certain statistics needed before sending
 * final object to the Client to display them
 *
 * @author Mass
 */
@Service
public class DataLoaderService {

    private final static Logger log = LoggerFactory.getLogger(DataLoaderService.class);

    private XmlParserService xmlParserService;
    private GameInformation gameInformation;
    private RowData tempRowData;

    public DataLoaderService(XmlParserService xmlParserService) {
        this.xmlParserService = xmlParserService;
    }

    /**
     * Method where XML parser is being called and after that CSV file is being called for reading
     *
     * @param txtFile CSV file
     * @param xmlFile XML file
     * @return final object filled with data
     */
    public void processMatchData(MultipartFile xmlFile, MultipartFile txtFile) {

        gameInformation = xmlParserService.getGameInformation(xmlFile);

        readFile(gameInformation.getFootballPlayerList().size(), txtFile);

    }

    /**
     * Getting all final data and sending to Controller
     * @return object containing all data
     */
    public FinalResult getAllMatchData() {
        return new FinalResult(gameInformation);
    }

    /**
     * Setting maximum speed of a football player [m/s]
     *
     * @param rowData current line of data
     * @see RowData
     */
    private void updateMaxSpeed(RowData rowData) {
        int index = 0;
        for (FootballPlayer fp : gameInformation.getFootballPlayerList()) {
            fp.setMaxSpeed(calculateMaxSpeed(fp.getMaxSpeed(), rowData.getPlayerCoordinateList().get(index).getVelocity()));
            index++;
        }
    }

    /**
     * Method for constantly updating (adding) distance, ran by every player on the field during match
     *
     * @param lastRowData previous data which holds (x1, y1) coordinates
     * @param newRowData  current data which holds (x2, y2) coordinates
     * @see Coordinate
     */
    private void updateLengthRun(RowData lastRowData, RowData newRowData) {
        int index = 0;
        for (FootballPlayer fp : gameInformation.getFootballPlayerList()) {
            fp.setLengthRun(fp.getLengthRun().add((calculatePlayerTravel(
                    lastRowData.getPlayerCoordinateList().get(index).getXPosition(),
                    lastRowData.getPlayerCoordinateList().get(index).getYPosition(),
                    newRowData.getPlayerCoordinateList().get(index).getXPosition(),
                    newRowData.getPlayerCoordinateList().get(index).getYPosition()
            ))));
            index++;
        }
    }

    /**
     * Method for reading each line in CSV file and mapping required data
     */
    @SneakyThrows
    private int readFile(int numberOfPayers, MultipartFile inputFile) {
        int errorsOccurred = 0;
        Scanner sc = new Scanner(inputFile.getInputStream(), "UTF-8");

        boolean flag = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            RowData currentData = RowDataMapper.toRowData(line);

            if (currentData.getPlayerCoordinateList().size() != numberOfPayers) {
                errorsOccurred++;
                log.error("Found coordinate data for {} player but expected {}", currentData.getPlayerCoordinateList().size(), numberOfPayers);
                continue;
            }

            updateMaxSpeed(currentData);
            if (flag) {
                updateLengthRun(getTempRowData(), currentData);
                flag = false;
            } else {
                saveTempRowData(currentData);
                flag = true;
            }
        }
        return errorsOccurred;
    }

    /**
     * Method for setting last RowData
     *
     * @param rowData last RawData
     */
    private void saveTempRowData(RowData rowData) {
        this.tempRowData = rowData;
    }

    /**
     * Method for getting last RowData
     *
     * @return last saved RowData
     */
    private RowData getTempRowData() {
        return this.tempRowData;
    }

    /**
     * Method for calculating distance of each player based on 2 data of coordinates (x1, y1)(x2, y2)
     *
     * @param xPreviousPosition last RowData x1 position
     * @param yPreviousPosition last RowData y1 position
     * @param xNextPosition     current RowData x2 position
     * @param yNextPosition     current RowData y2 position
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
     *
     * @param oldSpeed last maxSpeed of player
     * @param newSpeed new maxSpeed of player
     * @return bigger maxSpeed
     */
    private BigDecimal calculateMaxSpeed(BigDecimal oldSpeed, BigDecimal newSpeed) {
        return oldSpeed.compareTo(newSpeed) > 0 ? oldSpeed : newSpeed;
    }
}
