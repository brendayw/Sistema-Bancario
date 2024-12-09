package ar.utn.frbb.tup.sistema_bancario.persitence;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;

import java.util.List;

public interface AccountDaoInterface {
    void saveAccount(Account account) throws AccountAlreadyExists;
    Account findAccountById(long id_account) throws AccountNotFound;
    Account findAccountByIdHolder(long id_client) throws ClientNotFound;
    Account deactivateAccount(long id_account) throws AccountNotFound;
    void updateAccount(Account account) throws AccountNotFound;
    List<Account> findAllAccounts();
}
