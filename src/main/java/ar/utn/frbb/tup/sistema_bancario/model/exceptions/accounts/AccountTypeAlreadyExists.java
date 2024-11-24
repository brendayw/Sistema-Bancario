package ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts;

public class AccountTypeAlreadyExists extends Throwable {
    public AccountTypeAlreadyExists(String message) {
        super(message);
    }
}
