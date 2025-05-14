package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.ClientRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client createClient(Client client) {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del cliente es obligatorio");
        }
        if (client.getIdentification() == null || client.getIdentification().trim().isEmpty()) {
            throw new ValidationException("La identificación del cliente es obligatoria");
        }
        if (clientRepository.existsByIdentification(client.getIdentification())) {
            throw new ValidationException("Ya existe un cliente con esa identificación");
        }
        if (client.getPhone() != null && !client.getPhone().matches("^\\+?\\d{10,15}$")) {
            throw new ValidationException("El teléfono debe ser un número válido");
        }

        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(String id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client updateClient(String id, Client client) {
        if (!clientRepository.existsById(String.valueOf(id))) {
            throw new ResourceNotfoundException("Cliente no encontrado con ID: " + id);
        }
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del cliente es obligatorio");
        }
        if (client.getIdentification() == null || client.getIdentification().trim().isEmpty()) {
            throw new ValidationException("La identificación del cliente es obligatoria");
        }
        clientRepository.findByIdentification(client.getIdentification())
                .ifPresent(existingClient -> {
                    throw new ValidationException("Ya existe un cliente con esa identificación");
                });
        client.setId(id);
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(String id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotfoundException("Cliente no encontrado con ID: " + id);
        }
        clientRepository.deleteById(id);
    }

    public Client findByIdentification(String identification) {
        return clientRepository.findByIdentification(identification)
                .orElseGet(() -> {
                    // Aquí puedes realizar alguna lógica si el cliente no se encuentra,
                    // como loguear un mensaje, crear un cliente por defecto, etc.
                    System.out.println("Cliente con identificación " + identification + " no encontrado.");
                    return null; // O podrías devolver un objeto por defecto si tiene sentido
                });
    }


}
