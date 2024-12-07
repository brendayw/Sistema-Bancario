package ar.utn.frbb.tup.sistema_bancario.persitence;

import ar.utn.frbb.tup.sistema_bancario.model.Account;

import java.util.List;

public interface AccountDaoInterface {
    void saveAccount(Account account);
    Account findAccountById(String uan);
    Account findAccountByIdHolder(long id_client);
    Account deactivateAccount(String uan);
    void updateAccount(Account account);
    List<Account> findAllAccounts();
}
