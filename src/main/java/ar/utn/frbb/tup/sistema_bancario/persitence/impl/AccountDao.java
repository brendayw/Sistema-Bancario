package ar.utn.frbb.tup.sistema_bancario.persitence.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.AccountDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AccountDao implements AccountDaoInterface {
    private List<Account> accountDatabase = new ArrayList<>();

    @Autowired
    private AccountDaoInterface accountDAO;

    //guarda una cuenta
    @Override
    public void saveAccount(Account account) throws AccountAlreadyExists {
        Account existingAccount = findAccountById(account.getId_account());
        if (existingAccount != null) {
            accountDatabase.remove(existingAccount);
        } else {
            throw new AccountAlreadyExists("La cuenta ya existe.");
        }
        accountDatabase.add(account);
    }

    //buscar cuenta por id
    @Override
    public Account findAccountById(long id_account) {
        return accountDatabase.stream()
                .filter(account -> account.getId_account() == id_account)
                .findFirst()
                .orElse(null);
    }

    //buscar por titular
    @Override
    public Account findAccountByIdHolder(long id_client) {
        return accountDatabase.stream()
                .filter(account -> account.getId_client() == id_client)
                .findFirst()
                .orElse(null);
    }

    //desactiva la cuenta
    @Override
    public Account deactivateAccount(long id_account) throws AccountNotFound {
        Account account = findAccountById(id_account);
        if (account == null) {
            throw new AccountNotFound("No se pudo desactivar la cuenta con ID: " + id_account + ". Cuenta no encontrada.");
        }
        account.setStatus(false);
        updateAccount(account);
        return account;
    }

    //actualiza estado de cuenta
    public void updateAccount(Account account) throws AccountNotFound{
        Account update = findAccountById(account.getId_account());
        if (update == null) {
            throw new AccountNotFound("La cuenta que quiere eliminar no existe.");
        }

        if (update.isStatus() == true && account.isStatus() == false) {
            update.setStatus(false);
        }

        accountDAO.updateAccount(update);
    }

    //obtiene todas las cuentas
    @Override
    public List<Account> findAllAccounts() {
        return new ArrayList<>(accountDatabase);
    }

}
