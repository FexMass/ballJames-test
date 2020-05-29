package com.ballJamesTask.reactjava.controller;

import com.ballJamesTask.reactjava.model.FinalResult;
import com.ballJamesTask.reactjava.service.DataLoaderService;
import com.sun.istack.internal.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Rest Controller with one method for receiving CSV and XML file to be processed and result to be returned to Client
 * @author Mass
 */
@RestController
@RequestMapping
@CrossOrigin
public class DataController {

    private DataLoaderService dataLoaderService;
    private static final int XML_FILE = 0;
    private static final int CSV_FILE = 1;

    public DataController(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    /**
     * @param file collection of files from Client
     * @return ResponseEntity object which is filled with all data <FinalResult>
     * @see FinalResult
     */
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @RequestMapping(value = "/upload", method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<HttpStatus> uploadData(@NotNull @RequestParam("file") MultipartFile[] file) {
        try {
            dataLoaderService.processMatchData(file[XML_FILE], file[CSV_FILE]);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/gameInformation", method = RequestMethod.GET )
    @ResponseBody
    public ResponseEntity<FinalResult> getData() {
        try {
            return new ResponseEntity<>(dataLoaderService.getAllMatchData(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}