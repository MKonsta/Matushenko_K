package com.simple_dao.dao;

import com.simple_dao.entity.Client;

import java.util.List;

public interface ClientDAO {

    void add(Client client);

    List<Client> getAllClients();

    Client getById(int id);

    void remove(int id);
}
