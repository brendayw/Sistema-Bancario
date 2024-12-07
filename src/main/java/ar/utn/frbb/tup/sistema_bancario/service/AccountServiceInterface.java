package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;

import java.util.List;

public interface AccountServiceInterface {
    Account activateAccount(Account account, long id_client) throws AccountAlreadyExists;
    Account createAccount(Account account) throws AccountAlreadyExists;
    List<Account> getAllAccounts();
    Account getAccountByUan(String uan) throws AccountNotFound;
    Account getAccountByIdHolder(long id_client) throws AccountNotFound;
    Account deactivateAccount(String uan) throws AccountNotFound;

}
