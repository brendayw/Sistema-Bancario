package ar.utn.frbb.tup.sistema_bancario.persitence.entity;

import ar.utn.frbb.tup.sistema_bancario.controller.validations.AccountValidator;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;
import ar.utn.frbb.tup.sistema_bancario.persitence.entity.BaseEntity;

import java.time.LocalDate;
import java.util.List;

public class ClientEntity extends BaseEntity {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private AccountValidator accountValidator;
    private EntityType entityType;  // Usar EntityType en vez de String
    private LocalDate registrationDate;
    private List<Long> accounts;
    private List<Long> loans;

    // Constructor
    public ClientEntity(ClientDto clientDto) {
        super(clientDto.getId_client());
        this.name = clientDto.getName();
        this.lastname = clientDto.getLastname();
        this.email = clientDto.getEmail();
        this.phone = clientDto.getPhone();
        this.accountValidator = clientDto.getAccountValidator();
        this.entityType = clientDto.getEntityType();  // Guardamos directamente el enum
        this.registrationDate = LocalDate.now();  // Fecha de registro actual
    }

    // Método de conversión de String a LocalDate
    private LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date);
    }

    // Método para convertir ClientEntity a Client
    public Client toClient() {
        Client client = new Client(
            this.getId_client(),
            this.name,
            this.lastname,
            this.email,
            this.phone,
            this.entityType,
            this.registrationDate,
            this.accountValidator
            //client.setAccounts(this.accounts);  // Si tienes una lista de cuentas
           // client.setLoans(this.loans);  // Si tienes una lista de préstamos
        );

        return client;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", entityType=" + entityType +
                ", registrationDate=" + registrationDate +
               // ", accounts=" + accounts +
               // ", loans=" + loans +
                '}';
    }

    //aca me explicaron porque me daba error:
    // Este cambio debería corregir el error que estás viendo,
    // ya que ahora estás utilizando el constructor adecuado de
    // Client, pasando todos los parámetros requeridos para
    // la creación de un objeto Client.
    //Recuerda también que si no tienes el constructor adecuado
    // en Client (por ejemplo, si el constructor de Client no
    // tiene la firma que mencioné anteriormente), debes
    // agregarlo en la clase Client.
}
