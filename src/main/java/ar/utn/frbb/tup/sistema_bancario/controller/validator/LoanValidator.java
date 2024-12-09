package ar.utn.frbb.tup.sistema_bancario.controller.validator;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.EmptyFields;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.scorescredit.CreditScoreTooLowException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class LoanValidator {
    public void validate(LoanDto loanDto) throws EmptyFields, ClientNotFound, CreditScoreTooLowException {
        validateEmptyFields(loanDto);
        validateAmount(loanDto.getRequestedAmount());
        validateInterestRate(loanDto.getAnualInterestRate());
        validateTermMonths(loanDto.getRepaymentPeriodMonths());
        validateRequestDate(loanDto.getRequestDate());
    }

    private void validateEmptyFields(LoanDto loanDto) throws EmptyFields {
        if (loanDto == null ||
                loanDto.getRequestedAmount() <= 0 ||
                loanDto.getAnualInterestRate() <= 0 ||
                loanDto.getRepaymentPeriodMonths() <= 0 ||
                loanDto.getRequestDate() == null ||
                loanDto.getRequestDate().isEmpty()) {
            throw new EmptyFields("Uno o más campos obligatorios están vacíos o son inválidos.");
        }
    }

    private void validateAmount(double amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("El monto debe ser mayor o igual a 1000.");
        }
    }

    private void validateInterestRate(double anualInterestRate) {
        if (anualInterestRate <= 0 || anualInterestRate > 100) {
            throw new IllegalArgumentException("La tasa de interés debe ser mayor a 0 y menor o igual a 100.");
        }
    }

    private void validateTermMonths(int termMonths) {
        if (termMonths < 6 || termMonths > 360) {
            throw new IllegalArgumentException("El plazo en meses debe estar entre 6 y 360.");
        }
    }

    private void validateRequestDate(String requestDate) {
        try {
            LocalDate parsedRequestDate = LocalDate.parse(requestDate);
            if (parsedRequestDate.isAfter(LocalDate.now())) {
                LocalDate parsedApprovalDate = LocalDate.parse(requestDate);
                throw new IllegalArgumentException("La fecha de solicitud no puede ser una fecha futura.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de la fecha de solicitud es inválido. Se espera yyyy-MM-dd.");
        }
    }
}
