package com.cars24.auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="steprate")
public class StepRate {
    @GeneratedValue
    @Id
    int id;

    @Column(name = "rate")
    int stepRate;

    @OneToOne
    Auction auction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getStepRate() {
        return stepRate;
    }

    public void setStepRate(int stepRate) {
        this.stepRate = stepRate;
    }

    public static class Builder {

        int stepRate;



        public Builder setStepRate(int stepRate) {
            this.stepRate = stepRate;
            return this;
        }

        public StepRate build() {
            StepRate stepRateObj = new StepRate();

            stepRateObj.setStepRate(stepRate);
            return stepRateObj;
        }
    }
}
