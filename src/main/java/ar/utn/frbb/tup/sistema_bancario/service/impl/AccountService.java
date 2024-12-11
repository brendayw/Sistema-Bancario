package ar.utn.frbb.tup.sistema_bancario.service.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeNotSupported;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.AccountDaoInterface;
import ar.utn.frbb.tup.sistema_bancario.persitence.ClientDaoInterface;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.frbb.tup.sistema_bancario.service.AccountServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    private AccountDaoInterface accountDAO;

    @Autowired
    private ClientDaoInterface clientDAO;

    @Autowired
    private ClientServiceInterface clientService;

    @Override
    public Account activateAccount(long id_account) throws AccountNotFound {
        Account account = accountDAO.findAccountById(id_account);

        //exception si la cuenta no existe
        if (account == null) {
            throw new AccountNotFound("No se pudo activar la cuenta con ID: " + id_account + ". Cuenta no encontrada.");
        }
        //no hacer nada si la cuenta ya esa activa
        if (account.isStatus()) {
            return account;
        }

        //si esta desactivada
        account.setStatus(true);
        accountDAO.updateAccount(account);
        return account;
    }

    @Override
    public Account createAccount(Account account) throws AccountAlreadyExists, AccountTypeNotSupported, AccountNotFound {
        List<String> validAccountTypes = Arrays.stream(AccountType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        //si el tipo de cuenta no es valido
        if (!validAccountTypes.contains(account.getAccountType().toString())) {
            throw new AccountTypeNotSupported("El tipo de cuenta " + account.getAccountType() + " no es soportado.");
        }

        //si la cuenta ya existe
        Account existingAccount = accountDAO.findAccountById(account.getId_account());
        if (existingAccount != null) {
            throw new AccountAlreadyExists("La cuenta con ID " + account.getId_account() + " ya existe.");
        }

        //si la cuenta no existe
        if (account.getId_account() == 0) {
            long generatedAccountId = Long.parseLong(IdGenerator.idNumberGenerator());
            account.setId_account(generatedAccountId);
        }

        accountDAO.saveAccount(account);
        return account;
    }

    //obtiene todas las cuentas
    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.findAllAccounts();
    }

    //obtiene las cuentas por id
    @Override
    public Account getAccountById(long id_account) throws AccountNotFound {
        Account account = accountDAO.findAccountById(id_account);
        if (account == null) {
            throw new AccountNotFound("Cuenta con id: " + id_account + " no encontrada.");
        }

        return account;
    }

    //obtiene las cuentas por id del cliente
    @Override
    public Account getAccountByIdHolder(long id_client) throws ClientNotFound, AccountNotFound {
        //verifica si existe el cliente
        Client client = clientDAO.findClientById(id_client);
        if (client == null) {
            throw new ClientNotFound("El cliente con id: " + id_client + " no existe.");
        }
        Account account = accountDAO.findAccountByIdHolder(id_client);
        if (account == null) {
            throw new AccountNotFound("El cliente con id: " + id_client + " no tiene cuentas asociadas.");
        }
        return account;
    }

    //desactiva la cuenta
    @Override
    public void deactivateAccountById(long id_account) throws AccountNotFound {
        Account account = accountDAO.findAccountById(id_account);
        if (account == null) {
            throw new AccountNotFound("No se pudo desactivar la cuenta con UAN: " + id_account + ". Cuenta no encontrada.");
        }
        account.setStatus(false);
        accountDAO.updateAccount(account);
    }
}
