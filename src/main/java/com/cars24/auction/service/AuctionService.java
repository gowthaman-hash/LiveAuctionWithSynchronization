package com.cars24.auction.service;

import com.cars24.auction.common.ApplicationConstants;
import com.cars24.auction.dto.RunningAuction;
import com.cars24.auction.entity.Auction;
import com.cars24.auction.entity.User;
import com.cars24.auction.repository.AuctionRepository;
import com.cars24.auction.singleton.SingletonClass;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;


    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    public List<RunningAuction> fetchRunningAuctions(String status, String page, String size) {
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), Sort.by(Sort.Order.asc("item_code")));
        return auctionRepository.fetchRunningAuctions(status, pageable);
    }

    public void save(Auction a) {
        auctionRepository.save(a);
    }

    public Auction findByItemCode(String itemCode) {
        return auctionRepository.fetchAuction(itemCode);
    }

    public String postBid(String itemCode, int bid) {
        String response = ApplicationConstants.NOT_FOUND;
        try {
            Auction auction = auctionRepository.fetchAuction(itemCode);

            if (auction != null) {
                SingletonClass lock = SingletonClass.getInstance();
                synchronized (lock) {

                    int calStepRate = 0;
                    String userName = getLoggedInUserName();
                    response = ApplicationConstants.ACCEPTED;


                    List<User> us = auction.getUser().stream().filter(user -> user.getUserName().equalsIgnoreCase(userName)).collect(Collectors.toList());
                    User filteredUser = null;

                    if (us.isEmpty()) {
                        filteredUser = new User.Builder().setUserName(userName).build();
                        auction.getUser().add(filteredUser);
                    } else {
                        filteredUser = us.get(0);
                    }

                    int maxBid = auctionRepository.findHighestBidOfItem(itemCode);
                    if (maxBid == 0) {
                        if (bid >= auction.getBasePrice()) {
                            calculateStepRateAndAssign(bid, auction, filteredUser);
                            auctionRepository.save(auction);
                        } else {
                            response = ApplicationConstants.NOT_ACCEPTED;
                        }
                    } else {
                        if (bid >= maxBid) {
                            calStepRate = bid - maxBid;
                            auction.setStepRate(calStepRate);
                            filteredUser.setBidAmount(bid);
                            auctionRepository.save(auction);
                        } else {
                            response = ApplicationConstants.NOT_ACCEPTED;
                        }

                    }
                }

            }
        } catch (Exception e) {
            response = ApplicationConstants.NOT_FOUND;
            e.printStackTrace();
        }

        return response;
    }

    private void calculateStepRateAndAssign(int bid, Auction auction, User filteredUser) {
        int calStepRate;
        calStepRate = bid - auction.getBasePrice();
        auction.setStepRate(calStepRate);
        filteredUser.setBidAmount(bid);
    }

    private String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }
}
