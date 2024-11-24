package ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients;

public class ClientUnderage extends Throwable {
    public ClientUnderage(String message) {
        super(message);
    }
}
