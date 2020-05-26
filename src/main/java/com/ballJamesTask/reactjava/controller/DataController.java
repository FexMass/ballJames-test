package com.ballJamesTask.reactjava.controller;

import com.ballJamesTask.reactjava.model.GameInformation;
import com.ballJamesTask.reactjava.service.DataLoaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestController {

    private DataLoaderService dataLoaderService;


    public RestController(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @GetMapping()
    public ResponseEntity<GameInformation> getData(String txtPath, String xmlPath) {

        txtPath = "";
        xmlPath = "";

        try {
            return new ResponseEntity<>(dataLoaderService.getAllMatchData(txtPath, xmlPath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<GameInformation> getBallData(String txtPath, String xmlPath) {

        txtPath = "";
        xmlPath = "";

        try {
            return new ResponseEntity<>(dataLoaderService.getAllBallData(txtPath, xmlPath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
