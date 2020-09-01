package com.intercorpretail.microservicios;

import com.intercorpretail.microservicios.dao.ClientDaoImpl;
import com.intercorpretail.microservicios.dao.interfaces.ClientDao;
import com.intercorpretail.microservicios.models.Client;
import com.intercorpretail.microservicios.services.ClientServiceImpl;
import com.intercorpretail.microservicios.services.interfaces.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
class MicroserviciosApplicationTests {

    private ClientDaoImpl clientDao;
    private ClientServiceImpl clientService;
    private static final Double expectedAverageAgeTest=31.5;
    private static final Double standardDesviationTest=1.5;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() {
        clientDao = mock(ClientDaoImpl.class);
        clientService=new ClientServiceImpl();
        this.clientService.setClientDao(clientDao);
    }

    @Test
    void getAverageAge() throws ParseException {
        List<Client> clientList;
        clientList=mockClientList();
        Double valueAverage=this.clientService.getAverageAge(clientList);

        Assertions.assertEquals(expectedAverageAgeTest,valueAverage);
    }

    @Test
    void getStandardDesviation() throws ParseException {
        List<Client> clientList;
        clientList=mockClientList();
        Double valueStandardDesviation=this.clientService.getStandardDesviation(expectedAverageAgeTest,clientList);

        Assertions.assertEquals(standardDesviationTest,valueStandardDesviation);
    }

    private List<Client> mockClientList() throws ParseException {
        List<Client> clientList=new ArrayList<>();
        String sDateClientA="13/06/1987";
        Date dateClientA=new SimpleDateFormat("dd/MM/yyyy").parse(sDateClientA);
        Client clientA=new Client(Long.valueOf(33),dateClientA,"Agustin","Aoki");

        String sDateClientB="13/06/1990";
        Date dateClientB=new SimpleDateFormat("dd/MM/yyyy").parse(sDateClientB);
        Client clientB=new Client(Long.valueOf(30),dateClientB,"Juan","Perez");

        clientList.add(clientA);
        clientList.add(clientB);

        return  clientList;
    }
}
