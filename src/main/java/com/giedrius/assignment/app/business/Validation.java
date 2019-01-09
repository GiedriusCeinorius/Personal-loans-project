package com.giedrius.assignment.app.business;

import com.giedrius.assignment.app.model.Client;
import com.giedrius.assignment.app.model.ClientInfo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Validation {

    private ClientInfo info;
    private Client client;

    public Validation(Client client, ClientInfo info) {
        this.info = info;
        this.client = client;
    }

    public Boolean validateIP() {

        Boolean valid = true;
        List<ClientInfo> kInfo = client.getClientInfo();
        LocalDateTime start = null;
        for (ClientInfo clientInfo : kInfo) {
            start = clientInfo.getDateTime();
        }
        LocalDateTime end = info.getDateTime();
        Duration duration = Duration.between(start, end);
        Duration duration2 = Duration.of(3600, ChronoUnit.SECONDS);
        if (start != null) {
            if ((Duration.ofSeconds(duration.getSeconds()).compareTo(duration2) < 1) && (kInfo.size() >= 3)) {
                for (ClientInfo clientInfo : kInfo) {
                    if (clientInfo.getIpAddress().equals(info.getIpAddress())) {
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    public Boolean validateTime() {

        Boolean beforeAfter = true;
        LocalDateTime submition = info.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime submitionTime = LocalTime.parse(submition.format(formatter));

        LocalTime midnight = LocalTime.parse("23:00");
        LocalTime morning = LocalTime.parse("06:00");

        if (!submitionTime.isBefore(midnight)) {
            beforeAfter = false;
        } else if (!submitionTime.isAfter(morning)) {
            beforeAfter = false;
        } else {
            beforeAfter = true;
        }
        return beforeAfter;
    }
}
