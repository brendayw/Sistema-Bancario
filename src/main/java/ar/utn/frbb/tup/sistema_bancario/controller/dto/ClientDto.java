package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.controller.validations.AccountValidator;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;

import java.time.LocalDate;

public class ClientDto extends PersonDto {
    private AccountValidator accountValidator;
    private EntityType entityType;

    public ClientDto(String name, String lastname, String email, String phone, String birthDate, AccountValidator accountValidator, EntityType entityType) {
        super(name, lastname, email, phone, birthDate);
        this.accountValidator = accountValidator;
        this.entityType = entityType;
    }

    // Getters y setters
    public AccountValidator getAccountValidator() {
        return accountValidator;
    }
    public void setAccountValidator(AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    public EntityType getEntityType() {
        return entityType;
    }
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                ", accountValidator=" + accountValidator +
                ", entityType=" + entityType +
                '}';
    }
}