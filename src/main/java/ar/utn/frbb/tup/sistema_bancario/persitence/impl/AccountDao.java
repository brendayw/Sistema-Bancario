package ar.utn.frbb.tup.sistema_bancario.persitence.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.persitence.AccountDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class AccountDao implements AccountDaoInterface {
    private List<Account> accountDatabase = new ArrayList<>();

    //guarda una cuenta
    @Override
    public void saveAccount(Account account) {
        accountDatabase.add(account);
    }

    //buscar cuenta por uan
    @Override
    public Account findAccountById(String uan) {
        return accountDatabase.stream()
                .filter(account -> account.getUan().equals(uan))
                .findFirst()
                .orElse(null);
    }

    //buscar por titular
    @Override
    public Account findAccountByIdHolder(long id_client) {
        return accountDatabase.stream()
                .filter(account -> account.getHolder().getId_client() == id_client)
                .findFirst()
                .orElse(null);
    }

    //desactiva la cuenta
    @Override
    public Account deactivateAccount(String uan) {
        Account account = findAccountById(uan);
        if (account != null) {
            account.setActive(false);
            updateAccount(account);
        }
        return account;
    }

    //actualiza estado de cuenta
    public void updateAccount(Account account) {
        int index = accountDatabase.indexOf(account);

        if (index != -1) {
            accountDatabase.set(index,account);
        }
    }

    //obtiene todas las cuentas
    @Override
    public List<Account> findAllAccounts() {
        return new ArrayList<>(accountDatabase);
    }

}
