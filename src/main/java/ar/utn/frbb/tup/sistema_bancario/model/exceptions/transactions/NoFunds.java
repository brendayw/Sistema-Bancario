package ar.utn.frbb.tup.sistema_bancario.model.exceptions.transactions;

public class NoFunds extends Throwable{
    public NoFunds(String message) {
        super(message);
    }
}
