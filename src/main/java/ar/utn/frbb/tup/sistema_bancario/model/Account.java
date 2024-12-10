package ar.utn.frbb.tup.sistema_bancario.model;

import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;

import java.time.LocalDate;

public class Account {
   private long id_account; //id_generator
   private long id_client; //idgenerator
   private long cvu;
   private String alias;
   private AccountType accountType;
   private CurrencyType currencyType;
   private int balance;
   private LocalDate creationDate;
   private boolean status;

   //constructor
   public Account(long id_account, long id_client, long cvu, String alias, AccountType accountType, CurrencyType currencyType, int balance, LocalDate creationDate, boolean status) {
       this.id_account = id_account;
       this.id_client = id_client;
       this.cvu = cvu;
       this.alias = alias;
       this.accountType = accountType;
       this.currencyType = currencyType;
       this.balance = balance;
       this.creationDate = creationDate;
       this.status = status;
   }

   public Account() {}

   //getters and setters
   public long getId_account() {
       return id_account;
   }
   public void setId_account(long id_account) {
       this.id_account = id_account;
   }

   public long getId_client() {
       return id_client;
   }
   public void setId_client(long id_client) {
       this.id_client = id_client;
   }

   public long getCvu() {
       return cvu;
   }
   public void setCvu(long cvu) {
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

   public int getBalance() {
       return balance;
   }
   public void setBalance(int balance) {
       this.balance = balance;
   }

   public LocalDate getCreationDate() {
       return creationDate;
   }
   public void setCreationDate(LocalDate creationDate) {
       this.creationDate = creationDate;
   }

   public boolean isStatus() {
       return status;
   }
   public void setStatus(boolean status) {
       this.status = status;
   }
}
