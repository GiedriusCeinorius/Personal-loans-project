package com.giedrius.assignment.app;

import com.giedrius.assignment.app.dao.ClientRepository;
import com.giedrius.assignment.app.model.Client;
import com.giedrius.assignment.app.model.ClientInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

    @Autowired
    private ClientRepository repository;

    private Client client;

    @Test
    public void register() {
        client = new Client();
        client.setName("As");
        String[] address = {"Seiseliai", "Statybininku", "45"};
        client.setAddress(address);

        repository.save(client);
        System.out.println(client);
    }

    @Test
    public void addLoanToAccount() {
        String name = "Harry";
        Long loan = 50L;
        Long term = 2L;
        String[] address = {"Seiseliai", "Statybininku", "45"};

        client = new Client();
        client.setName(name);
        client.setAddress(address);
        ClientInfo info = new ClientInfo();
        info.setLoan(0L);
        info.setTerm(0L);
        client.setClientInfo(info);
        Map<String, Boolean> map = client.getAddLoan().addLoanAndInfoOnLoaning(client, loan, term);
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
