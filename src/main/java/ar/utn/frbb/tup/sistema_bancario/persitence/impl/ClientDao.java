package ar.utn.frbb.tup.sistema_bancario.persitence.impl;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.ClientDaoInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements ClientDaoInterface {
    private static List<Client> clientsDataBase = new ArrayList<>(); //simulacion de base de datos con una lista

    @Override
    public void saveClient(Client client) {
        Client existingClient = findClientById(client.getId_client());
        if (existingClient != null) {
            clientsDataBase.remove(existingClient);
        }
        clientsDataBase.add(client);
    }

    //buscar un cliente por id en la base de datos
    @Override
    public Client findClientById(long id_client) {
        return clientsDataBase.stream()
                .filter(c -> c.getId_client() == id_client)
                .findFirst()
                .orElse(null);
    }

    //actualiza cliente
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

   //desactiva la cuenta
    @Override
    public Client deactivateClient(long id_client) {
        Client client = findClientById(id_client);
        if (client != null) {
            client.setStatus(false);
        }
        return client;
    }

    //encuentra todos los clientes
    @Override
    public List<Client> findAllClients() {
        return new ArrayList<>(clientsDataBase);
    };
}