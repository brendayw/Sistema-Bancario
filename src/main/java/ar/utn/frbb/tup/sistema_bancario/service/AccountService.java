package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.persitence.dao.AccountDao;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {
    @Autowired
    private AccountDao accountDAO = new AccountDao();

    @Autowired
    private ClientService clientService;

    public void activateAccount(Account account, long id_client) throws AccountAlreadyExists {
        if (accountDAO.findAccountById(account.getUan()) != null) {
            throw new AccountAlreadyExists("La cuenta ya existe.");
        }
        accountDAO.saveAccount(account);
    }
}
