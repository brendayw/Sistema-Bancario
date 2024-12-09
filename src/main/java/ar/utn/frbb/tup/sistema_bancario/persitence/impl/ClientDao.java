package ar.utn.frbb.tup.sistema_bancario.persitence.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.ClientDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class ClientDao implements ClientDaoInterface {
    private static List<Client> clientsDataBase = new ArrayList<>(); //simulacion de base de datos con una lista

    //guarda el cliente
    @Override
    public void saveClient(Client client) {
        Client existingClient = findClientById(client.getId_client());
        if (existingClient != null) {
            clientsDataBase.remove(existingClient);
        }
        clientsDataBase.add(client);
    }

    //buscar un cliente por id
    @Override
    public Client findClientById(long id_client) {
        return clientsDataBase.stream()
                .filter(c -> c.getId_client() == id_client)
                .findFirst()
                .orElse(null);
    }

    //actualiza datos del cliente (email y telefono)
    @Override
    public Client updateClient(Client client) throws ClientNotFound {
        Client update = findClientById(client.getId_client());

        if(update != null) {
            if (client.getEmail() != null) {
                update.setEmail(client.getEmail());
            }
            if (client.getPhone() != null) {
                update.setPhone(client.getPhone());
            }

            System.out.println("Datos actualizados con exito.");
            return update;
        } else {
            throw new ClientNotFound("Cliente no encontrado.");
        }
    }

   //desactiva al cliente
    @Override
    public Client deactivateClient(long id_client) throws ClientNotFound{
        Client client = findClientById(id_client);
        if (client == null) {
            throw new ClientNotFound("El cliente no existe por ende no se puede desactivar.");
        }
        client.setStatus(false);
        updateClient(client);
        return client;
    }

    //encuentra todos los clientes
    @Override
    public List<Client> findAllClients() {
        return new ArrayList<>(clientsDataBase);
    };
}