package com.cars24.auction.controller;

import com.cars24.auction.common.ApplicationConstants;
import com.cars24.auction.dto.BidAuction;
import com.cars24.auction.dto.RunningAuction;
import com.cars24.auction.entity.Auction;
import com.cars24.auction.service.AuctionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @GetMapping("/all")
    public List<Auction> fetchAll() {
        return auctionService.findAll();
    }

    @GetMapping
    public List<RunningAuction> fetchRunningAuctions(@RequestParam("status") String status, @RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "4") String size) {
        return auctionService.fetchRunningAuctions(status,page,size);
    }

    @PostMapping("/{itemCode}/bid")
    @ResponseBody
    public ResponseEntity<String> postBid(@RequestBody BidAuction bidAmount, @PathVariable String itemCode) {
        String status = auctionService.postBid(itemCode, bidAmount.getBidAmount());
        if (ApplicationConstants.NOT_ACCEPTED.equalsIgnoreCase(status)) {
            return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
        } else if (ApplicationConstants.ACCEPTED.equalsIgnoreCase(status)) {
            return new ResponseEntity<>(status, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
    }
}
