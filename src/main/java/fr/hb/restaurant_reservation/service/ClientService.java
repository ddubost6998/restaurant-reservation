package fr.hb.restaurant_reservation.service;

import fr.hb.restaurant_reservation.model.Client;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {

    private static final Map<Long, Client> clientsStorage = new HashMap<>();
    private static long counter = 1;

    /**
     * CREATE ou UPDATE
     */
    public Client save(Client client) {
        if (client.getId() == null || client.getId() == 0) {
            client.setId(counter++);
        }
        clientsStorage.put(client.getId(), client);
        return client;
    }

    /**
     * READ ALL
     */
    public List<Client> findAll() {
        return new ArrayList<>(clientsStorage.values());
    }

    /**
     * READ ONE
     */
    public Client findById(Long id) {
        return clientsStorage.get(id);
    }

    /**
     * DELETE
     */
    public void deleteById(Long id) {
        clientsStorage.remove(id);
    }

    /**
     * Vider tous les clients
     */
    public void clearAll() {
        clientsStorage.clear();
        counter = 1;
    }
}
