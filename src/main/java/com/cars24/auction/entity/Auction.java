package com.cars24.auction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Auction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;
    @Column(name = "item_code")
    String itemCode;
    @Column(name = "base_price")
    int basePrice;

    @Column(name = "status")
    String status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    List<User> user;
    @Column(name = "step_rate")
    int stepRate;

    public int getStepRate() {
        return stepRate;
    }

    public void setStepRate(int stepRate) {
        this.stepRate = stepRate;
    }


    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class Builder {
        String itemCode;
        int basePrice;
        List<User> user;

        String status;
        int stepRate;

        public Builder setItemCode(String pItemCode) {
            this.itemCode = pItemCode;
            return this;
        }

        public Builder setBasePrice(int basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setUser(List<User> user) {
            this.user = user;
            return this;
        }


        public Builder setStepRate(int stepRate) {
            this.stepRate = stepRate;
            return this;
        }

        public Auction build() {
            Auction auction = new Auction();
            auction.setItemCode(itemCode);
            auction.setBasePrice(basePrice);
            auction.setStatus(status);
            auction.setUser(user);
            auction.setStepRate(stepRate);
            return auction;
        }

    }

}
