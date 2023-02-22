package com.raf.restdemo.dto;

public class ClientRentDaysDto {
    private int days;
    private Long clientId;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
