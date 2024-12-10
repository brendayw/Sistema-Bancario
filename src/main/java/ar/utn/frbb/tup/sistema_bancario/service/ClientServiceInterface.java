package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientUnderage;

import java.util.List;

public interface ClientServiceInterface {
    Client createClient(Client client) throws ClientAlreadyExists, ClientUnderage;
    Client addAccountToClient(Client client, Account account) throws AccountAlreadyExists;
    List<Client> getAllClients();
    Client getClientById(long id_client) throws ClientNotFound;
    Client deactivateClient(long id_client) throws ClientNotFound;
    Client updateClient(Client client) throws ClientNotFound;
}
