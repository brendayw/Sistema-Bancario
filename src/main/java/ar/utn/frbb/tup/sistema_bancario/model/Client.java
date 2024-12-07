package ar.utn.frbb.tup.sistema_bancario.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;
import ar.utn.frbb.tup.sistema_bancario.controller.validations.AccountValidator;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;

public class Client extends Person {
    private long id_client;
    private EntityType entityType;
    private LocalDate registrationDate;
    private Set<Account> accounts = new HashSet<>();
    private AccountValidator accountValidator;
    private boolean active;

    //constructor
    public Client(String name, String lastname, String email, String phone, long id_client, EntityType entityType, LocalDate registrationDate, Set<Account> accounts, AccountValidator accountValidator, boolean active) {
        super(name, lastname, email, phone);
        this.id_client = id_client;
        this.entityType = entityType;
        this.registrationDate = registrationDate;
        this.accounts = accounts;
        this.accountValidator = accountValidator;
        this.active = active;
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
    public long getId_client() {
        return id_client;
    }
    public void setId_client(long id_client) {
        this.id_client = id_client;
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

    public Set<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public AccountValidator getAccountValidator() {
        return accountValidator;
    }
    public void setAccountValidator(AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "\nId: " + id_client +
                "\nTipo de persona: " + entityType +
                "\nFecha de registro: " + registrationDate +
                "\nCuentas: " + accounts +
                "\nEstado del cliente: " + active;
    }
}