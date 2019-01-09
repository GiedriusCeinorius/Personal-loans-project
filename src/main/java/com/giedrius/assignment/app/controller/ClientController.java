package com.giedrius.assignment.app.controller;

import com.giedrius.assignment.app.dao.ClientRepository;
import com.giedrius.assignment.app.model.Client;
import com.giedrius.assignment.app.model.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @PostMapping("/registration")
    public String saveClients(@RequestBody List<Client> clients) {
        repository.saveAll(clients);
        return clients.size() + " clients registered.";
    }


    @GetMapping("/getClientsHistory/{name}")
    public List<String> findClientByName(@PathVariable String name) {
        List<String> list = new ArrayList<>();
        Client client = repository.findByName(name);
        list.add("Client name: " + client.getName());
        list.add("Client address: ");
        for (String s : client.getAddress()) {
            list.add(s);
        }

        for (ClientInfo clientInfo : client.getClientInfo()) {
            list.add("Loan " + clientInfo.getLoan() + " euros");
            list.add("Term: " + clientInfo.getTerm() + " months");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String dateTime = clientInfo.getDateTime().format(formatter);
            String expirationDate = clientInfo.getExpirationDate().format(formatter);

            list.add("Expiration date: " + ((expirationDate.equals("1986-04-08 12:30")) ? "No expiration date." : expirationDate));
            list.add(((clientInfo.getLoan() == 0L) ? "Registration date" : "Loan's date") + " and IP address:");
            list.add(dateTime);
            list.add(clientInfo.getIpAddress());
        }
        return list;
    }

    @PostMapping("/addLoanToAccount/{name}/{amount}/{term}")
    public String addLoanToAccount(@PathVariable String name, @PathVariable Long amount, @PathVariable Long term) {
        Client client = repository.findByName(name);

        Map<String, Boolean> map = client.getAddLoan().addLoanAndInfoOnLoaning(client, amount, term);
        String response = "";
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getKey().contains("DidntPassIPValidation")) {
                response = "We are sorry, but you exceeded your loan limit for this day! Try after 24h!";
            } else if (entry.getKey().contains("DidntPassDateValidation")) {
                response = "We are sorry, it's  to risky to loan you between 00:00 and 06:00, try different hours!";
            } else {
                repository.save(client);
                response = amount + " loaned  to " + name + " with the term of " + term + " month's";
            }
        }
        return response;
    }
}
