package com.api.estramipyme.services;

import com.api.estramipyme.models.Client;
import com.api.estramipyme.respositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client updateClientById(Long id, Client client) {
        return clientRepository.findById(id).map(c -> {
                c.setEmail(client.getEmail());
                c.setName(client.getName());
                c.setSector(client.getSector());
                c.setSurname(client.getSurname());
                c.setBusinessName(client.getBusinessName());
                c.setPassword(client.getPassword());
                c.setDocType(client.getDocType());
                c.setPersonType(client.getPersonType());
                c.setDocNumber(client.getDocNumber());
                return c;
                })
                .orElse(null);
    }
}
