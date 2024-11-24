package ar.utn.frbb.tup.sistema_bancario.persitence.dao;

import ar.utn.frbb.tup.sistema_bancario.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private List<Account> accountDatabase = new ArrayList<>();

    //guarda una cuenta
    public void saveAccount(Account account) {
        accountDatabase.add(account);
        System.out.println("Cuenta guardada con éxito: " + account.getAccountType());
    }

    //buscar cuenta por uan
    public Account findAccountById(String uan) {
        return accountDatabase.stream()
                .filter(account -> account.getUan().equals(uan))
                .findFirst()
                .orElse(null);
    }

    //buscar por titular
    public Account findAccountByIdHolder(String uan, long id_client) {
        return accountDatabase.stream()
                .filter(account -> account.getUan().equals(uan) && account.getHolder().getId_client() == id_client)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteAccount(String uan) {
        return accountDatabase.removeIf(account -> account.getUan().equals(uan));
    }
}
