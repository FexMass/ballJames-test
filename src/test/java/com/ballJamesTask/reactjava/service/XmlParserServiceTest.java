package com.ballJamesTask.reactjava.service;

import com.ballJamesTask.reactjava.model.GameInformation;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for XML parser service
 *
 * @author Mass
 */
@SpringBootTest
class XmlParserServiceTest {

    @Autowired
    private XmlParserService xmlParserService;

    @Test
    @SneakyThrows
    void shouldParseGameInformationData() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "structure.xml");
        String fileName = "structure.xml";
        String contentType = "application/xml";
        byte[] content = Files.readAllBytes(resourceDirectory.toAbsolutePath());

        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, contentType, content);

        GameInformation gameInformation = xmlParserService.toGameInformation(multipartFile);

        assertEquals(30, gameInformation.getFootballPlayerList().size());
    }

    @Test
    void shouldCreateEmptyRootObject() {
        xmlParserService.startDocument();
        assertNull(xmlParserService.getGameInformation().getFootballPlayerList());
        assertNull(xmlParserService.getGameInformation().getHalfTimeInformationList());
    }
}