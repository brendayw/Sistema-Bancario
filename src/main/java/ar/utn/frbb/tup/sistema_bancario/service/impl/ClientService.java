package ar.utn.frbb.tup.sistema_bancario.service.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientUnderage;
import ar.utn.frbb.tup.sistema_bancario.persitence.ClientDaoInterface;

import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClientService implements ClientServiceInterface {
    @Autowired
    private final ClientDaoInterface clientDao;

    //constructor
    public ClientService(ClientDaoInterface clientDao) {
        this.clientDao = clientDao;
    }

    // Crea un cliente nuevo
    @Override
    public Client createClient(Client client) throws ClientAlreadyExists, ClientUnderage {
        try {
            //verifica si existe el cliente
            Client existingClient = clientDao.findClientById(client.getId_client());

            if (existingClient != null) {
                throw new ClientAlreadyExists("El cliente ya existe.");
            }
        } catch (ClientNotFound e) {
            //verifica si el cliente tiene id y si no tiene genera uno
            if (client.getId_client() == 0) {
                long generatedClientId = Long.parseLong(IdGenerator.idNumberGenerator());
                client.setId_client(generatedClientId);
            }
        }

        if (isUnderage(client)) {
            throw new ClientUnderage("El cliente es menor de edad y no puede ser creado.");
        }

        clientDao.saveClient(client);
        return client;
    }

    //asocia una cuenta a un cliente
    @Override
    public Client addAccountToClient(Client client, Account account) throws AccountAlreadyExists, AccountTypeAlreadyExists {
        //verifica si la cuenta ya esta asociada a un cliente
        for (Account existingAccount : client.getAccounts()) {
            if (existingAccount.getId_account() == account.getId_account() ) {
                throw new AccountAlreadyExists("La cuenta ya está asociada a un cliente.");
            } else if (existingAccount.getAccountType() == account.getAccountType()) {
                throw new AccountTypeAlreadyExists("El cliente ya tiene este tipo de cuenta.");
            }
        }

        //genera el id
        if (account.getId_account() == 0) {
            account.setId_account(Long.parseLong(IdGenerator.idNumberGenerator()));
        }

        client.getAccounts().add(account); //agrega cuenta
        clientDao.saveClient(client); //guarda cuenta
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
        Client client = clientDao.findClientById(id_client);
        //verifica si existe el cliente
        if (client == null) {
            throw new ClientNotFound("No se pudo encontrar el cliente.");
        }
        client.setStatus(false); //cambia el estado del cliente
        clientDao.updateClient(client); //acualiza el estado
        return client;
    }

    public Client updateClient(Client client) throws ClientNotFound {
        return clientDao.updateClient(client);
    }

    //metodo que verifica si es menor de edad
    private boolean isUnderage(Client client) {
        // Supongamos que client tiene un método getBirthDate() que retorna LocalDate
        LocalDate birthDate = client.getBirthDate();
        LocalDate today = LocalDate.now();

        // Calcula la edad
        int age = Period.between(birthDate, today).getYears();

        // Si la edad es menor de 18 años, retorna true
        return age < 18;
    }

}