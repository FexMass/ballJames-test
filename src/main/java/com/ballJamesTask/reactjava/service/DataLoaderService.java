package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.mapper.RowDataMapper;
import com.ballJamesTask.reactjava.model.*;
import com.sun.rowset.internal.Row;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Scanner;

@Service
public class DataLoaderService {

    private XmlParserService xmlParserService;
    private GameInformation gameInformation;
    private RowData tempRowData;

    public DataLoaderService(XmlParserService xmlParserService) {
        this.xmlParserService = xmlParserService;
    }

    public FinalResult getAllMatchData(String txtPath, String xmlPath) {

        gameInformation = xmlParserService.getGameInformation(xmlPath);

        readFileByLine();

        return new FinalResult(gameInformation);
    }

    public RowData updateMaxSpeed(RowData rowData) {
        int index = 0;
        for (FootballPlayer fp : gameInformation.getFootballPlayerList()) {
            fp.setMaxSpeed(calculateMaxSpeed(fp.getMaxSpeed(), rowData.getCoordinateList().get(index).getVelocity()));
            index++;
        }
        return rowData;
    }

    public void updateLengthRun(RowData lastRowData, RowData newRowData) {
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

    public void readFileByLine() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("20200223_Heracles_vs_Ajax_20200223_Heracles_vs_Ajax - Copy.txt");
        Scanner sc = new Scanner(Objects.requireNonNull(inputStream), "UTF-8");

        boolean flag = false;

        while (sc.hasNextLine()) {
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

    public void saveTempRowData (RowData rowData) {
        this.tempRowData = rowData;
    }

    public RowData getTempRowData() {
        return this.tempRowData;
    }

    public BigDecimal calculatePlayerTravel(BigDecimal xPreviousPosition, BigDecimal yPreviousPosition,
                                            BigDecimal xNextPosition, BigDecimal yNextPosition) {

        return new BigDecimal(Math.sqrt(
                (xPreviousPosition.doubleValue() - xNextPosition.doubleValue()) *
                        (xPreviousPosition.doubleValue() - xNextPosition.doubleValue()) +
                        (yPreviousPosition.doubleValue() - yNextPosition.doubleValue()) *
                        (yPreviousPosition.doubleValue() - yNextPosition.doubleValue())
        ), FootballPlayer.mathContext).round(FootballPlayer.mathContext);



    }

    public BigDecimal calculateMaxSpeed(BigDecimal oldSpeed, BigDecimal newSpeed) {
        return oldSpeed.compareTo(newSpeed) > 0 ? oldSpeed : newSpeed;
    }
}
