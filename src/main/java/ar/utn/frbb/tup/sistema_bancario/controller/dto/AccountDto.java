package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import java.time.LocalDate;

public class AccountDto {
   private String accountType;
   private String currencyType;
   private String holder;
   private int balance;
   private boolean status;
   private LocalDate creationDate;

   //constructor
   public AccountDto(String accountType, String currencyType, String holder, int balance, boolean status, LocalDate creationDate) {
       this.accountType = accountType;
       this.currencyType = currencyType;
       this.holder = holder;
       this.balance = balance;
       this.status = status;
       this.creationDate = creationDate;
   }

   //getters and setters
   public String getAccountType() {
       return accountType;
   }
   public void setAccountType(String accountType) {
       this.accountType = accountType;
   }

   public String getCurrencyType() {
       return currencyType;
   }
   public void setCurrencyType(String currencyType) {
       this.currencyType = currencyType;
   }

   public String getHolder() {
       return holder;
   }
   public void setHolder(String holder) {
       this.holder = holder;
   }

   public int getBalance() {
       return balance;
   }
   public void setBalance(int balance) {
        this.balance = balance;
   }

   public boolean isStatus() {
       return status;
   }
   public void setStatus(boolean status) {
       this.status = status;
   }

   public LocalDate getCreationDate() {
      return creationDate;
   }
   public void setCreationDate(LocalDate creationDate) {
       this.creationDate = creationDate;
   }

}
