package com.intercorpretail.microservicios.services;

import com.intercorpretail.microservicios.dao.interfaces.ClientDao;
import com.intercorpretail.microservicios.models.Client;
import com.intercorpretail.microservicios.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;

    public List<Client> getAll(){
        return this.clientDao.getAll();
    }

    public Map<String,Long> getKpis(){
        Map kpisMap=new HashMap();
        List<Client> clients=this.clientDao.getAll();
        if(!clients.isEmpty()) {
            Double averageAge = getAverageAge(clients);
            kpisMap.put("averageAge:", getAverageAge(clients));
            kpisMap.put("standarDesviation:", getStandardDesviation(averageAge, clients));
        }
        return kpisMap;
    }

    public Double getAverageAge(List<Client> clients){
        LongSummaryStatistics stats = clients.stream()
                .mapToLong((x) -> x.getAge())
                .summaryStatistics();
        return stats.getAverage();
    }

    public Double getStandardDesviation(Double average, List<Client> clients){
        double numi = 0.0;
        double num=0.0;
        for (Client client : clients) {
            numi = Math.pow(((double) client.getAge() - average), 2);
            num+=numi;
        }
        return Math.sqrt(num/clients.size());
    }

    public Client save(Long age, Date birth, String name, String surname){
        Client client=new Client(age,birth,name,surname);
        return this.clientDao.save(client);
    }

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }
}
