package ar.utn.frbb.tup.sistema_bancario.model.exceptions.scorescredit;

public class CreditScoreTooLowException extends Throwable {

    public CreditScoreTooLowException() {
        super("El puntaje crediticio es demasiado bajo para aceptar el préstamo.");
    }

    public CreditScoreTooLowException(String message) {
        super(message);
    }

    public CreditScoreTooLowException(String message, Throwable cause) {
        super(message, cause);
    }
}
