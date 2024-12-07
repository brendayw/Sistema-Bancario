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
    private boolean active = true;

    //constructor
    public Account(String uan, String cvu, String alias, AccountType accountType, CurrencyType currencyType, Client holder) {
        if (accountType == null || currencyType == null || holder == null) {
            throw new IllegalArgumentException("AccountType, CurrencyType y Holder no pueden ser nulos.");
        }

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

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountType=" + accountType +
                ", currencyType=" + currencyType +
                ", holder=" + holder.getName() + // Suponiendo un método getName()
                ", uan='" + uan + '\'' +
                ", cvu='" + cvu + '\'' +
                ", alias='" + alias + '\'' +
                ", active=" + active +
                '}';
    }


}
