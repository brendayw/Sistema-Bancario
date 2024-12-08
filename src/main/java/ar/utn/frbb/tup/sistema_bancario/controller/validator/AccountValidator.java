package ar.utn.frbb.tup.sistema_bancario.controller.validator;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.AccountDto;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.EmptyFields;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeNotSupported;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.transactions.CurrencyNotSupported;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountValidator {

    public void validate(AccountDto accountDto) throws AccountTypeNotSupported, CurrencyNotSupported, EmptyFields {
        validateCurrencyType(accountDto.getCurrencyType());
        validateAccountType(accountDto.getAccountType());
        validateHolder(accountDto.getHolder());
        validateBalance(accountDto.getBalance());
        validateStatus(accountDto.isStatus());
        validateCreationDate(accountDto.getCreationDate());
        validateEmptyFields(accountDto);
    }

    private void validateCurrencyType(String currencyType) throws CurrencyNotSupported{
        if (!"PESOS".equals(currencyType) || !"DOLARES".equals(currencyType)) {
            throw new CurrencyNotSupported("Tipo de cuenta no soportada: " + currencyType);
        }
    }

    private void validateAccountType(String accountType) throws AccountTypeNotSupported{
        if (!"CHECKING".equals(accountType) || !"SAVINGS".equals(accountType)) {
            throw new AccountTypeNotSupported("Tipo de cuenta no soportada: " + accountType);
        }
    }

    private void validateHolder(String holder) throws  EmptyFields{
        if (holder == null) throw new EmptyFields("Titular no puede ser nulo");
    }

    public void validateBalance(int balance) {
        if (balance < 0) throw new IllegalArgumentException("Saldo no puede ser negativo");
    }

    public void validateStatus(Boolean status) {
        if (status == null) throw new IllegalArgumentException("El estado de la cuenta no puede ser nulo");
    }

    public void validateCreationDate(LocalDate creationDate) {
        if (creationDate == null) throw new IllegalArgumentException("La fecha de creacion no puede ser nulo");
        // la fecha no puede ser mayor a la fecha actual
        if (creationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de creacion no puede ser mayor a la fecha actual");
    }

    private void validateEmptyFields(AccountDto accountDto) throws EmptyFields {
        if (isNullOrEmpty(accountDto.getAccountType()) ||
                isNullOrEmpty(accountDto.getCurrencyType()) ||
                isNullOrEmpty(accountDto.getHolder()) ||
                accountDto.getCreationDate() == null) {
            throw new EmptyFields("Uno o más campos obligatorios están vacíos o son nulos.");
        }
    }

    private boolean isNullOrEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
