package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;

public class AccountDto {
    private String uan; //unique account number similar al id
    private String cvu;
    private String alias;
    private long id_client;
    public AccountType accountType;
    public CurrencyType currencyType;

    //constructores
    public AccountDto() {}

    public AccountDto(String uan, String cvu, String alias, long id_client, AccountType accountType, CurrencyType currencyType) {
        this.uan = uan;
        this.cvu = cvu;
        this.alias = alias;
        this.id_client = id_client;
        this.accountType = accountType;
        this.currencyType = currencyType;
    }

    //getters & setters
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

    public long getId_client() {
        return id_client;
    }
    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

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
}
