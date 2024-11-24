package ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts;

public class AccountTypeNotSupported extends Throwable {
    public AccountTypeNotSupported(String message) {
        super(message);
    }
}