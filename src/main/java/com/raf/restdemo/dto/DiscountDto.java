package com.raf.restdemo.dto;

public class DiscountDto {

    private Integer discount;

    private String rank;

    public DiscountDto() {

    }

    public DiscountDto(Integer discount, String rank) {
        this.discount = discount;
        this.rank = rank;
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
