package ar.utn.frbb.tup.sistema_bancario.controller.dto;

public class ClientDto extends PersonDto {
    private String entityType;

    //constructor
    public ClientDto(String name, String lastname, String dni, String email, String phone, String birthDate, String entityType) {
        super(name, lastname, dni, email, phone, birthDate);
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                ", entityType=" + entityType +
                '}';
    }
}