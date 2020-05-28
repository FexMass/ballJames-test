package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.model.GameInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class XmlParserServiceTest {

    @Autowired
    private XmlParserService xmlParserService;


    @Test
    public void shouldParseGameInformationData() {
//        GameInformation gameInformation = xmlParserService.getGameInformation("./src/test/resources/structure.xml");
//
//        assertEquals("", gameInformation.toString());
//        assertEquals(30, gameInformation.getFootballPlayerList().size());
    }
}