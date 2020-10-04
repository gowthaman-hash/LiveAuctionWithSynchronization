package com.cars24.auction.common;

import com.cars24.auction.entity.Auction;
import com.cars24.auction.entity.User;
import com.cars24.auction.repository.AuctionRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    public AuctionRepository auctionRepository;

    public void run(String... args) throws Exception {
        User user = new User.Builder().setUserName("user1").setBidAmount(1200).build();


        List<User> userList = new ArrayList<>();
        userList.add(user);
        Auction a = new Auction.Builder().setItemCode("BMW7").setBasePrice(1000).setStatus(ApplicationConstants.RUNNING).setUser(userList).setStepRate(0).build();

        auctionRepository.save(a);
        Auction a1 = auctionRepository.fetchAuction("BMW7");
        user = new User.Builder().setUserName("user2").setBidAmount(1400).build();
        a1.getUser().add(user);

        a1.setStepRate(200);
        auctionRepository.save(a1);
        /*====================================*/
        user = new User.Builder().setUserName("user1").setBidAmount(5200).build();


        userList = new ArrayList<>();
        userList.add(user);
        a1 = new Auction.Builder().setItemCode("BMW8").setBasePrice(5000).setStatus(ApplicationConstants.RUNNING).setUser(userList).setStepRate(0).build();

        auctionRepository.save(a1);

        a1 = auctionRepository.fetchAuction("BMW8");
        user = new User.Builder().setUserName("user2").setBidAmount(5400).build();
        a1.getUser().add(user);

        a1.setStepRate(200);
        auctionRepository.save(a1);
        /*====================================*/
        user = new User.Builder().setUserName("user3").setBidAmount(5200).build();


        userList = new ArrayList<>();
        userList.add(user);
        a1 = new Auction.Builder().setItemCode("BMW1").setBasePrice(5000).setStatus(ApplicationConstants.RUNNING).setUser(userList).setStepRate(0).build();

        auctionRepository.save(a1);

        a1 = auctionRepository.fetchAuction("BMW1");
        user = new User.Builder().setUserName("nirai").setBidAmount(5400).build();
        a1.getUser().add(user);

        a1.setStepRate(400);
        auctionRepository.save(a1);
        /*====================================*/
        user = new User.Builder().setUserName("user4").setBidAmount(1200).build();


        userList = new ArrayList<>();
        userList.add(user);
        a1 = new Auction.Builder().setItemCode("BMW2").setBasePrice(1000).setStatus(ApplicationConstants.OVER).setUser(userList).setStepRate(0).build();

        auctionRepository.save(a1);

    }
}
