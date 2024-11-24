package ar.utn.frbb.tup.sistema_bancario.model.exceptions.transactions;

public class CurrencyNotSupported extends Throwable {
    public CurrencyNotSupported(String message) {
        super(message);
    }
}
