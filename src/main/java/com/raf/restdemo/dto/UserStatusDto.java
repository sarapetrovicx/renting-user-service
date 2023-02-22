package com.raf.restdemo.dto;

public class UserStatusDto {

    private Integer minNumberOfRentalDays;
    private Integer maxNumberOfRentalDays;
    private Integer discount;
    private String rank;

    public UserStatusDto() {

    }
    public UserStatusDto(Integer minNumberOfRentalDays, Integer maxNumberOfRentalDays, Integer discount, String rank) {
        this.minNumberOfRentalDays = minNumberOfRentalDays;
        this.maxNumberOfRentalDays = maxNumberOfRentalDays;
        this.discount = discount;
        this.rank = rank;
    }

    public Integer getMinNumberOfRentalDays() {
        return minNumberOfRentalDays;
    }

    public void setMinNumberOfRentalDays(Integer minNumberOfRentalDays) {
        this.minNumberOfRentalDays = minNumberOfRentalDays;
    }

    public Integer getMaxNumberOfRentalDays() {
        return maxNumberOfRentalDays;
    }

    public void setMaxNumberOfRentalDays(Integer maxNumberOfRentalDays) {
        this.maxNumberOfRentalDays = maxNumberOfRentalDays;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
