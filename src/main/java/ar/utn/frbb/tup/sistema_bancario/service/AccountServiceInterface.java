package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeNotSupported;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;

import java.util.List;

public interface AccountServiceInterface {
    Account activateAccount(long id_account) throws AccountNotFound;
    Account createAccount(Account account) throws AccountAlreadyExists, AccountTypeNotSupported, AccountNotFound;
    List<Account> getAllAccounts();
    Account getAccountById(long id_account) throws AccountNotFound;
    Account getAccountByIdHolder(long id_client) throws ClientNotFound, AccountNotFound;
    void deactivateAccountById(long id_account) throws AccountNotFound;

}
