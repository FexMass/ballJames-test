package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.model.FootballPlayer;
import com.ballJamesTask.reactjava.model.GameInformation;
import com.ballJamesTask.reactjava.statistics.Distance;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class CsvParserService {

//    private Logger logger = Logger.getLogger(this.getClass().getName());
//    private InputStream inputStream = null;
//    private Scanner sc = null;
//    private List<Statistics> statisticsList;
//
//    public FileReaderService () {
//        try {
//            readingFromFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void readingFromFile() throws IOException {
//        ClassLoader classLoader = getClass().getClassLoader();
//        String lastLine = null;
//        String line = null;
//
//        try {
//            inputStream = classLoader.getResourceAsStream("20200223_Heracles_vs_Ajax_20200223_Heracles_vs_Ajax.txt");
//            sc = new Scanner(Objects.requireNonNull(inputStream), "UTF-8");
//            //line by line
//            while (sc.hasNextLine()) {
//                int i = 0;
//                lastLine = line;
//                line = sc.nextLine();
//                String[] split = line.split("[:;]");
//
//                System.out.println(split[0]); //ignore
//                System.out.println(split[1]); //1-31 player info  x,y,z,speed[m/s]
//
//                for (String s : split) {
////                    new Distance().calculate(s.);
//                }
//
////                GameInformation gi = new GameInformation();
////                gi.kurac.get(1);
//
//
//                System.out.println(split[32]); // last ball position
//
//                //distance = sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))
////                statisticsList.forEach(statistics -> statistics.calcultate(line, lastLine));
//
//               break;
//            }
//            if (sc.ioException() != null) {
//                throw sc.ioException();
//            }
//        } finally {
//            if (inputStream != null) {
//                inputStream.close();
//            }
//            if (sc != null) {
//                sc.close();
//            }
//        }
//    }
}
