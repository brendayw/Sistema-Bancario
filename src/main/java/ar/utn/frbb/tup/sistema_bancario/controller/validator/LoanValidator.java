package ar.utn.frbb.tup.sistema_bancario.controller.validator;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.EmptyFields;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.scorescredit.CreditScoreTooLowException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoanValidator {
    public void validate(LoanDto loanDto) throws EmptyFields, ClientNotFound, CreditScoreTooLowException {
        validateEmptyFields(loanDto);
        validateClientExists(loanDto.getId_client());
        validateAmount(loanDto.getAmount());
        validateInterestRate(loanDto.getInterestRate());
        validateTermMonths(loanDto.getTermMonths());
        validateDates(loanDto.getRequestDate(), loanDto.getApprovalDate());
    }

    private void validateEmptyFields(LoanDto loanDto) throws EmptyFields {
        if (loanDto == null ||
                loanDto.getId_client() == null ||
                loanDto.getAmount() <= 0 ||
                loanDto.getInterestRate() <= 0 ||
                loanDto.getTermMonths() <= 0 ||
                loanDto.getRequestDate() == null ||
                loanDto.getRequestDate().isEmpty()) {
            throw new EmptyFields("Uno o más campos obligatorios están vacíos o son inválidos.");
        }
    }

    private void validateClientExists(String id_client) throws ClientNotFound {
        if (id_client == null) {
            throw new ClientNotFound("El cliente con id: " + id_client + " no existe.");
        }
    }

    private void validateAmount(double amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("El monto debe ser mayor o igual a 1000.");
        }
    }

    private void validateInterestRate(double interestRate) {
        if (interestRate <= 0 || interestRate > 100) {
            throw new IllegalArgumentException("La tasa de interés debe ser mayor a 0 y menor o igual a 100.");
        }
    }

    private void validateTermMonths(int termMonths) {
        if (termMonths < 6 || termMonths > 360) {
            throw new IllegalArgumentException("El plazo en meses debe estar entre 6 y 360.");
        }
    }

    private void validateDates(String requestDate, String approvalDate) {
        try {
            LocalDate parsedRequestDate = LocalDate.parse(requestDate);
            if (approvalDate != null && !approvalDate.isEmpty()) {
                LocalDate parsedApprovalDate = LocalDate.parse(approvalDate);
                if (parsedApprovalDate.isBefore(parsedRequestDate)) {
                    throw new IllegalArgumentException("La fecha de aprobación no puede ser anterior a la fecha de solicitud.");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha inválido para las fechas de solicitud o aprobación.");
        }
    }
}
