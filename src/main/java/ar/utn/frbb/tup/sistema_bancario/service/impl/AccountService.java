package ar.utn.frbb.tup.sistema_bancario.service.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.AccountDaoInterface;

import java.util.List;

import ar.utn.frbb.tup.sistema_bancario.service.AccountServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    private AccountDaoInterface accountDAO;

    @Autowired
    private ClientServiceInterface clientService;

    @Override
    public Account activateAccount(Account account, long id_client) throws AccountAlreadyExists {
        //verifica si existe
        if (accountDAO.findAccountById(account.getUan()) != null) {
            throw new AccountAlreadyExists("La cuenta ya existe.");
        }
        //guarda la cuenta
        accountDAO.saveAccount(account);
        return account;
    }

    @Override
    public Account createAccount(Account account) throws AccountAlreadyExists {
        if (accountDAO.findAccountById(account.getUan()) != null) {
            throw new AccountAlreadyExists("La cuenta ya existe.");
        }
        accountDAO.saveAccount(account);
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.findAllAccounts();
    }

    @Override
    public Account getAccountByUan(String uan) throws AccountNotFound {
        Account account = accountDAO.findAccountById(uan);
        if (account == null) {
            throw new AccountNotFound("Cuenta con UAN: " + uan + " no encontrada");
        }

        return account;
    }

    @Override
    public Account getAccountByIdHolder(long id_client) throws AccountNotFound {
        Account account = accountDAO.findAccountByIdHolder(id_client);
        if (account == null) {
            throw new AccountNotFound("Cuenta del cliente: " + id_client + " no encontrada para el titular con ID: " + id_client);
        }
        return account;
    }

    @Override
    public Account deactivateAccount(String uan) throws AccountNotFound {
        Account account = accountDAO.findAccountById(uan);
        if (account == null) {
            throw new AccountNotFound("No se pudo desactivar la cuenta con UAN: " + uan + ". Cuenta no encontrada.");
        }
        account.setActive(false);
        accountDAO.updateAccount(account);
        return account;
    }
}
