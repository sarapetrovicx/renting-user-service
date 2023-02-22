package com.raf.restdemo.listener;

import com.raf.restdemo.dto.ClientRentDaysDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.ClientService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class RentDaysListener {

    private MessageHelper messageHelper;
    private ClientService clientService;

    public RentDaysListener(MessageHelper messageHelper, ClientService clientService) {
        this.messageHelper = messageHelper;
        this.clientService = clientService;
    }

    @JmsListener(destination = "${destination.rentDays}", concurrency = "5-10")
    public void changerentDays(Message message) throws JMSException {
        ClientRentDaysDto clientRentDaysDto = messageHelper.getMessage(message, ClientRentDaysDto.class);
        clientService.changeRentDays(clientRentDaysDto);
    }
}
