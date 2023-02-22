package com.raf.restdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer minNumberOfRentalDays;
    private Integer maxNumberOfRentalDays;
    private Integer discount;
    private String rank;

    public UserStatus() {

    }

    public UserStatus(Integer minNumberOfRentalDays, Integer maxNumberOfRentalDays, Integer discount, String rank) {
        this.minNumberOfRentalDays = minNumberOfRentalDays;
        this.maxNumberOfRentalDays = maxNumberOfRentalDays;
        this.discount = discount;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
