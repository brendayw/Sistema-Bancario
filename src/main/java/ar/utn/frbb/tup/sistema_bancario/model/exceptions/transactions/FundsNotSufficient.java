package ar.utn.frbb.tup.sistema_bancario.model.exceptions.transactions;

public class FundsNotSufficient extends Throwable {
    public FundsNotSufficient(String message) {
        super(message);
    }
}

