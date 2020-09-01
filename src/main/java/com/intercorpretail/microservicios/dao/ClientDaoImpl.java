package com.intercorpretail.microservicios.dao;

import com.intercorpretail.microservicios.dao.interfaces.ClientDao;
import com.intercorpretail.microservicios.models.Client;
import com.intercorpretail.microservicios.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDaoImpl implements ClientDao {

    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }

    public Client save(Client client){
      return this.clientRepository.save(client);
    }


    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
