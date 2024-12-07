package ar.utn.frbb.tup.sistema_bancario.service.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.ClientDaoInterface;

import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements ClientServiceInterface {
    @Autowired
    private ClientDaoInterface clientDao;

    //constructor
    public ClientService(ClientDaoInterface clientDao) {
        this.clientDao = clientDao;
    }

    // Crea un cliente nuevo
    @Override
    public Client createClient(Client client) {
        try {
            Client existingClient = clientDao.findClientById(client.getId_client());

            if (existingClient != null) {
                throw new ClientAlreadyExists("El cliente ya existe.");
            }
            clientDao.saveClient(client);
            return client;
        } catch (ClientAlreadyExists e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClientNotFound e) {
            System.out.println("Cliente no encontrado: " + e.getMessage());
            return null;
        }
    }

    //asocia una cuenta a un cliente
    @Override
    public Client addAccountToClient(Client client, Account account) throws AccountAlreadyExists {
        for (Account existingAccount : client.getAccounts()) {
            if (existingAccount.getUan().equals(account.getUan())) {
                throw new AccountAlreadyExists("La cuenta ya está asociada a un cliente.");
            }
        }
        client.getAccounts().add(account);
        clientDao.saveClient(client);
        return client;
    }

    //muestra todos los clientes
    @Override
    public List<Client> getAllClients() {
        return clientDao.findAllClients();
    }

    //busca cliente por id
    @Override
    public Client getClientById(long id_client) throws ClientNotFound {
        Client client = clientDao.findClientById(id_client);
        if (client == null) {
            throw new ClientNotFound("Cliente con id: " + id_client + " no encontrado.");
        }
        return client;
    }

    //da de baja el cliente
    @Override
    public Client deactivateClient(long id_client) throws ClientNotFound {
        Client client = deactivateClient(id_client);
        if (client == null) {
            throw new ClientNotFound("No se pudo encontrar el cliente.");
        }
        clientDao.updateClient(client);
        return client;
    }

    public Client updateClient(Client client) throws ClientNotFound {
        return clientDao.updateClient(client);
    }
}