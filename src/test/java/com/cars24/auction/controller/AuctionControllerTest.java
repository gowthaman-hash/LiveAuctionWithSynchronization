package com.cars24.auction.controller;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cars24.auction.common.ApplicationConstants;
import com.cars24.auction.dto.RunningAuction;
import com.cars24.auction.dto.RunningAuctionDTO;
import com.cars24.auction.service.AuctionService;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuctionController.class)
@WithMockUser
public class AuctionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuctionService auctionService;

    String exampleBidJson = "{\"bidAmount\":\"1200\"}";

    @Test
    public void getRunningAuctions() throws Exception {
        RunningAuctionDTO run = new RunningAuctionDTO("BMW7", 1200, 200);
        List<RunningAuction> runningAuctions = Arrays.asList(run);
        Mockito.when(
                auctionService.fetchRunningAuctions(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString())).thenReturn(runningAuctions);

        String uri = "/auction?status=RUNNING";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                uri).accept(
                MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void postBid() throws Exception {
        //Accepted
        Mockito.when(
                auctionService.postBid(Mockito.anyString(),
                        Mockito.anyInt())).thenReturn(ApplicationConstants.ACCEPTED);
        String uri = "/auction/BMW8/bid";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                uri).accept(
                MediaType.APPLICATION_JSON).content(exampleBidJson).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated());
        //Rejected
        exampleBidJson = "{\"bidAmount\":\"1100\"}";
        Mockito.when(
                auctionService.postBid(Mockito.anyString(),
                        Mockito.anyInt())).thenReturn(ApplicationConstants.NOT_ACCEPTED);

        requestBuilder = MockMvcRequestBuilders.post(
                uri).accept(
                MediaType.APPLICATION_JSON).content(exampleBidJson).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotAcceptable());
        //Action(Item) not found
        exampleBidJson = "{\"bidAmount\":\"1300\"}";
        uri = "/auction/YYY/bid";
        Mockito.when(
                auctionService.postBid(Mockito.anyString(),
                        Mockito.anyInt())).thenReturn(ApplicationConstants.NOT_FOUND);

        requestBuilder = MockMvcRequestBuilders.post(
                uri).accept(
                MediaType.APPLICATION_JSON).content(exampleBidJson).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }
}
