package com.ballJamesTask.reactjava.controller;

import com.ballJamesTask.reactjava.model.FinalResult;
import com.ballJamesTask.reactjava.service.DataLoaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DataController {

    private DataLoaderService dataLoaderService;

    public DataController(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @GetMapping()
    public ResponseEntity<FinalResult> getData(String txtPath, String xmlPath) {
        txtPath = "";
        xmlPath = "C:\\Development\\ballJames-test\\src\\main\\resources\\20200223_Heracles_vs_Ajax_20200223_Heracles_vs_Ajax[8730].xml";

        try {
            return new ResponseEntity<>(dataLoaderService.getAllMatchData(txtPath, xmlPath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
