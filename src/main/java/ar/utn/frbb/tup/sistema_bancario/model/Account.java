package ar.utn.frbb.tup.sistema_bancario.model;

import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;
import ar.utn.frbb.tup.sistema_bancario.persitence.entity.AccountEntity;


public class Account {
    public AccountType accountType;
    public CurrencyType currencyType;
    public Client holder;
    private String uan;
    private String cvu;
    private String alias;

    //constructor
    public Account(AccountType accountType, CurrencyType currencyType, Client holder) {
        this.accountType = accountType;
        this.currencyType = currencyType;
        this.holder = holder;
        this.uan = AccountEntity.generateUAN();
        this.cvu = AccountEntity.generateCvu();
        this.alias = AccountEntity.generateAlias();
    }

    //getters & setters
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }
    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Client getHolder() {
        return holder;
    }
    public void setHolder(Client holder) {
        this.holder = holder;
    }

    public String getUan() {
        return uan;
    }
    public void setUan(String uan) {
        this.uan = uan;
    }

    public String getCvu() {
        return cvu;
    }
    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
