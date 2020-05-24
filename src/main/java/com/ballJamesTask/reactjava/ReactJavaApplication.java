package com.ballJamesTask.reactjava;

import com.ballJamesTask.reactjava.service.FileReaderService;
import com.ballJamesTask.reactjava.service.XmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 *
 */
@SpringBootApplication
public class ReactJavaApplication {

	private static final Logger log = LoggerFactory.getLogger(ReactJavaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReactJavaApplication.class, args);
		runXmlParser();
	}

	private static void runXmlParser() {
		try {
			InputStream inputFile = ClassLoader.getSystemResourceAsStream("20200223_Heracles_vs_Ajax_20200223_Heracles_vs_Ajax.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XmlParser userHandler = new XmlParser();
			saxParser.parse(Objects.requireNonNull(inputFile), userHandler);

			System.out.println(userHandler.getGameInformation());


		} catch (IOException | SAXException | ParserConfigurationException e) {
			if (e instanceof SAXException) {
				e.getMessage();
			}
		} finally {
			new FileReaderService();
		}
	}
}
