package com.api.estramipyme.controllers;

import com.api.estramipyme.models.Client;
import com.api.estramipyme.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    // trae todos los clientes
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    //Traer solo un cliente por el id
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //crear un cliente
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    //borrar un cliente

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    //Actualizar un cliente
    //Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Optional<Client> existingClient = clientService.getClientById(id);

        if (existingClient.isPresent()) {
            Client clientToUpdate = existingClient.get();
            clientToUpdate.setName(clientDetails.getName());
            clientToUpdate.setSurname(clientDetails.getSurname());
            clientToUpdate.setEmail(clientDetails.getEmail());
            clientToUpdate.setPassword(clientDetails.getPassword());
            clientToUpdate.setSector(clientDetails.getSector());
            clientToUpdate.setBusinessName(clientDetails.getBusinessName());
            clientToUpdate.setDocNumber(clientDetails.getDocNumber());
            clientToUpdate.setDocType(clientDetails.getDocType());
            clientToUpdate.setPersonType(clientDetails.getPersonType());

            Client updatedClient = clientService.saveClient(clientToUpdate);
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
