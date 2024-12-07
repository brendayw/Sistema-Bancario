package ar.utn.frbb.tup.sistema_bancario.persitence;

import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;

import java.util.List;

public interface ClientDaoInterface {
    void saveClient(Client client);
    Client findClientById(long id_client) throws ClientNotFound;
    Client updateClient(Client client) throws ClientNotFound;
    Client deactivateClient(long id_client) throws ClientNotFound;
    List<Client> findAllClients();
}
