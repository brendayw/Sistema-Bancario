package ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients;

public class ClientNotFound extends Throwable {
    public ClientNotFound(String message) {
        super(message);
    }
}
