package ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients;

public class ClientAlreadyExists extends Throwable {
    public ClientAlreadyExists(String message) {
        super(message);
    }
}