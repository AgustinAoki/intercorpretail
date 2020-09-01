package com.intercorpretail.microservicios.services.interfaces;

import com.intercorpretail.microservicios.models.Client;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> getAll();
    Client save(Long age, Date birth, String name, String surname);
    Map<String,Long> getKpis();
}
