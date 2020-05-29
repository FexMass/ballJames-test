# BallJames back end Java/SpringBoot

 This is back end Application for BallJames front end React. Application handles file upload from Client
 and each file is properly parsed. XML file will be parsed with SAX parser and CSV file will be read line
 by line and map data into objects/mappers in Java. 
 
 Front end Application repository: https://github.com/FexMass/ballJames-React
 
 Application is meant to read following data: 
 * Halftime information (first half start and end, second half
 start and end, date of the played game, location of the match, field size width x length)
 * Player information (x position, y position, z position, velocity [m/s], id of the player,
 team name of each player)
 
 With that data properly stored in Java there are methods for calculation of maximum speed and comparing 
 those speeds to set last maximum speed, calculation of player run distance by formula 
 "distance = sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))" and assigning every distance to each player.
 
 With those data all stored and calculated, they are polished to be send to the Client as json and displayed
 accordingly.
 
 ## Note!
 
 Data is available as long the server is up and running. If server is closed or restarted all data is lost 
 from objects in Java.

## Getting Started

### Prerequisites
* Git
* JDK 8 or later
* Maven 3 or later

### Download/Clone
To get started you can simply clone/download this repository using git or browser:

### Run by spring boot maven plugin
Navigate into aplication folder via "cd" from cmd where it was downloaded or cloned

Run first:
```
mvn install
```

Then run the application from the command line using:
```
mvn spring-boot:run
```

Json can be seen on (needs to be refreshed when client do something):
```
http://localhost:8080/gameInformation
```

To see logs (if any) they are available via cmd from where application was started

### Build an executable JAR
Build a single executable JAR file that contains all the necessary dependencies, classes, and resources with:
```
mvn clean package
```
Then run the JAR file with:
```
java -jar target/*.jar 
```
Or if it cannot find or access jar then cd target/ to get into target folder and then run:
```
java -jar react-java-0.0.1-SNAPSHOT.jar 
```

## Technologies used

* Java 8
* Maven 4.0.0
* Jakson
* Lombok
* Spring Boot 5
* Junit 5

## License
This project is licensed under the terms of the [MIT license](LICENSE).
This project is licensed under the terms of the [Maven license](LICENSE).

