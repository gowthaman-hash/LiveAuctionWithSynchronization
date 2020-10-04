package com.cars24.auction.dto;

public class RunningAuctionDTO implements RunningAuction{
    String itemCode;
    public int bidAmount;

    public RunningAuctionDTO(String itemCode, int bidAmount, int stepRate) {
        this.itemCode = itemCode;
        this.bidAmount = bidAmount;
        this.stepRate = stepRate;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setStepRate(int stepRate) {
        this.stepRate = stepRate;
    }

    public int stepRate;
    @Override
    public String getItemCode() {
        return null;
    }

    @Override
    public int getBidAmount() {
        return 0;
    }

    @Override
    public int getStepRate() {
        return 0;
    }
}
