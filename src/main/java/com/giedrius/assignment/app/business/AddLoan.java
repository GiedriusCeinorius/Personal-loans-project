package com.giedrius.assignment.app.business;

import com.giedrius.assignment.app.model.Client;
import com.giedrius.assignment.app.model.ClientInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AddLoan {

    public Map<String, Boolean> addLoanAndInfoOnLoaning(Client client, Long loan, Long term) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean validIPOrNot = true;
        Boolean atRiskTimeOrNot = true;
        ClientInfo info = new ClientInfo();
        info.setDateTime(LocalDateTime.now());
        info.setExpirationDate(LocalDateTime.now().plusMonths(term));
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        info.setIpAddress("" + localhost.getHostAddress().trim());
        info.setLoan(loan);
        info.setTerm(term);
        Validation validation = new Validation(client, info);
        if (validation.validateIP()) {
            validIPOrNot = true;
            map.put("PassedIPValidation", validIPOrNot);
            if (validation.validateTime()) {
                map.put("PassedDateValidation", atRiskTimeOrNot);
                client.getClientInfo().add(info);
            } else {
                map.put("DidntPassDateValidation", atRiskTimeOrNot);
            }
        } else {
            validIPOrNot = false;
            map.put("DidntPassIPValidation", validIPOrNot);
        }
        return map;
    }
}
