package ar.utn.frbb.tup.sistema_bancario.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;
import ar.utn.frbb.tup.sistema_bancario.controller.validations.AccountValidator;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;

public class Client extends Person {
    private EntityType entityType;
    private LocalDate registrationDate;
    private List<Account> accounts = new ArrayList<>();
    private AccountValidator accountValidator;

    //constructor
    public Client(long id_client, String name, String lastname, String email, String phone, EntityType entityType, LocalDate registrationDate, AccountValidator accountValidator) {
        super(id_client, name, lastname, email, phone);
        this.entityType = entityType;
        this.registrationDate = LocalDate.now();
        this.accountValidator = accountValidator;
    }

    public boolean addAccount(Account account) {
        if (accountValidator.validate(account, this)) {
            this.accounts.add(account);
            account.setHolder(this);
            return true; //se agregó correctamente
        }
        return false; //no se agrego
    }

    //verificacion
    public boolean hasAccount(AccountType accountType, CurrencyType currencyType) {
        for (Account account : this.accounts) {
            if (accountType.equals(account.getAccountType()) && currencyType.equals(account.getCurrencyType())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCurrencyAccountType(CurrencyType currencyType) {
        for (Account account : this.accounts) {
            if (currencyType.equals(account.getCurrencyType())) {
                return true;
            }
        }
        return false;
    }

    //getters & setters
    public List<Account> getAccounts() {
        return accounts;
    }

    public AccountValidator getAccountValidator() {
        return accountValidator;
    }

    public EntityType getEntityType() {
        return entityType;
    }
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}