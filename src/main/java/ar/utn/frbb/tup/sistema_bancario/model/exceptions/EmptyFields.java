package ar.utn.frbb.tup.sistema_bancario.model.exceptions;

public class EmptyFields extends Throwable {
    public EmptyFields(String message) {
        super(message);
    }
}
