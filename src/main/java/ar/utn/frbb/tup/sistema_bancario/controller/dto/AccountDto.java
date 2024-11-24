package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;

public class AccountDto {
    private String uan; //unique account number similar al id
    private String cvu;
    private String alias;
    public AccountType accountType;
    public CurrencyType currencyType;

    //constructor
    public AccountDto(String uan, String cvu, String alias, AccountType accountType, CurrencyType currencyType) {
        this.uan = uan;
        this.cvu = cvu;
        this.alias = alias;
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
