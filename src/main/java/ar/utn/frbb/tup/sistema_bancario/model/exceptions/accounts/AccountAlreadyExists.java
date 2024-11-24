package ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts;

public class AccountAlreadyExists extends Throwable {
    public AccountAlreadyExists(String message) {
        super(message);
    }
}
