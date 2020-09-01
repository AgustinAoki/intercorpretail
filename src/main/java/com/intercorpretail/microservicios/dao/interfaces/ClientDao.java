package com.intercorpretail.microservicios.dao.interfaces;

import com.intercorpretail.microservicios.models.Client;

import java.util.List;

public interface ClientDao {
    List<Client>  getAll();
    Client save(Client client);
}
