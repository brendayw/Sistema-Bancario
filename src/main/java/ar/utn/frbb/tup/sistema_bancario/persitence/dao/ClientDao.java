package ar.utn.frbb.tup.sistema_bancario.persitence.dao;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.controller.validations.AccountValidator;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;
import ar.utn.frbb.tup.sistema_bancario.persitence.entity.AccountEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private static List<Client> clientsDataBase = new ArrayList<>(); //simulacion de base de datos con una lista
    private AccountValidator accountValidator;
    //constructor
    public ClientDao(AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    //Metodos
    public Client createClient(ClientDto clientDto) {
        // Aquí agregamos EntityType y LocalDate
        EntityType entityType = EntityType.PHYSICAL;
        LocalDate registrationDate = LocalDate.now();

        Client client = new Client(
                clientDto.getId_client(),
                clientDto.getName(),
                clientDto.getLastname(),
                clientDto.getEmail(),
                clientDto.getPhone(),
                entityType,
                registrationDate,
                clientDto.getAccountValidator()
        );

        createDefaultAccounts(client);

        clientsDataBase.add(client);

        return client;
    }

    private void createDefaultAccounts(Client client) {
        List<AccountType> accountTypes = List.of(
                AccountType.SAVINGPESOS, AccountType.SAVINGSDOLLARS,
                AccountType.CHECKINGPESOS, AccountType.CHECKINGDOLLARS);

        List<CurrencyType> currencyTypes = List.of(
                CurrencyType.PESOS, CurrencyType.DOLARES);

        for (int i = 0; i < accountTypes.size(); i++) {
            Account account = new Account(accountTypes.get(i), currencyTypes.get(i), client);
            account.setUan(AccountEntity.generateUAN());
            account.setCvu(AccountEntity.generateCvu());
            account.setAlias(AccountEntity.generateAlias());
            client.addAccount(account);
        }
    }

    //buscar un cliente por id en la base de datos
    public Client getClientById(long id_client) {
        return clientsDataBase.stream()
                .filter(c -> c.getId_client() == id_client)
                .findFirst()
                .orElse(null);
    }

    //actualiza cliente
    public boolean updateClient(ClientDto clientDTO) {
        Client client = getClientById(clientDTO.getId_client());
        if (client == null) {
            return false;
        }

        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());

        return true;
    }

    //elimina cliente ! ACA VERIFICAR QUE EN REALIDAD SE DESACTIVE LA CUENTA
    public boolean deleteClient(long id_client) {
        Client client = getClientById(id_client);
        if (client != null) {
            return clientsDataBase.remove(client);
        }
        return false;
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clientsDataBase);
    };
}