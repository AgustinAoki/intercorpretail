package com.intercorpretail.microservicios.controllers;

import com.intercorpretail.microservicios.models.Client;
import com.intercorpretail.microservicios.services.interfaces.ClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(tags = "Clients")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @ApiOperation(value = "Obtiene todos los clientes",
            response = Client.class)
    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public  ResponseEntity<List<Client>> getAll(){
        List<Client>clients=this.clientService.getAll();
        return (clients!=null)
                ?ResponseEntity.ok().body(clients)
                :ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Obtiene las métricas de los clientes")
    @GetMapping("/kpideclientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public  ResponseEntity<?> getKpiClients(){
        Map kpiValues=this.clientService.getKpis();
        return (!kpiValues.isEmpty())
                ? ResponseEntity.ok().body(kpiValues)
                : ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Operación POST para crear un nuevo cliente")
    @PostMapping(value = "/creacliente")
    public ResponseEntity<?> createClient(
            @ApiParam(value = "Edad del cliente",required = true) @RequestParam(value = "age") Integer age,
            @ApiParam(value = "Fecha de nacimiento (yyyy-mm-dd)",required = true) @RequestParam(value = "birth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birth,
            @ApiParam(value = "Nombre del cliente",required = true) @RequestParam(value = "name") String name,
            @ApiParam(value = "Apellido del cliente",required = true) @RequestParam(value = "surname") String surname
    ) {
        var client=this.clientService.save(age.longValue(),birth,name,surname);
        return (client!=null)
                ? ResponseEntity.created(null).body(client)
                :   ResponseEntity.noContent().build();
    }

}
