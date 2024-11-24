package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.controller.validations.AccountValidator;
import ar.utn.frbb.tup.sistema_bancario.model.Person;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;

import java.time.LocalDate;

public class ClientDto extends PersonDto {
    private long id_client;
    private AccountValidator accountValidator;
    private EntityType entityType;

    // Constructor
    public ClientDto(String name, String lastname, String email, String phone, String birthDate, long id_client, AccountValidator accountValidator, EntityType entityType) {
        super();
        this.id_client = id_client;
        this.accountValidator = accountValidator;
        this.entityType = entityType;
    }

    // Getters y setters
    public long getId_client() {
        return id_client;
    }
    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

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
                "id_client=" + id_client +
                ", accountValidator=" + accountValidator +
                ", entityType=" + entityType +
                '}';
    }
}