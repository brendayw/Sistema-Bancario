package ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts;

public class AccountNotFound extends Throwable {
    public AccountNotFound(String message) {
        super(message);
    }
}