package com.ballJamesTask.reactjava.controller;

import com.ballJamesTask.reactjava.model.FinalResult;
import com.ballJamesTask.reactjava.model.GameInformation;
import com.ballJamesTask.reactjava.service.DataLoaderService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataLoaderService dataLoaderService;

    @Test
    @SneakyThrows
    void shouldUploadData() {
//        MultipartFile[] files = new MultipartFile[2];
//        files[0]= null;
//        files[1] = null;
//        Mockito.doNothing().when(dataLoaderService.processMatchData(files[0], files[1]));
//        Assertions.assertThrows(Exception.class, () -> {
//
//        });

    }

    @Test
    @SneakyThrows
    void shouldVerifyGetEndpointExists() {
        Mockito.when(dataLoaderService.getAllMatchData()).thenReturn(new FinalResult(new GameInformation()));

        this.mockMvc.perform(get("/gameInformation")).andDo(print())
                .andExpect(status().isOk());
    }
}