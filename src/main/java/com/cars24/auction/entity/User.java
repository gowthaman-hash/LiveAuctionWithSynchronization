package com.cars24.auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @Column(name = "user_name")
    String userName;
    @ManyToOne
    Auction auction;
    @Column(name = "bid_amount")
    int bidAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public static class Builder {
        String userName;


        int bidAmount;

        public Builder setBidAmount(int bidAmount) {
            this.bidAmount = bidAmount;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public User build() {
            User user = new User();
            user.setBidAmount(bidAmount);
            user.setUserName(userName);
            return user;
        }
    }


}
