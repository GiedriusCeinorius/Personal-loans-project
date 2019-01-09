package com.giedrius.assignment.app.dao;

import com.giedrius.assignment.app.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    Client findByName(String name);
}
