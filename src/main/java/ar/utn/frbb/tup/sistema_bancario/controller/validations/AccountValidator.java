package ar.utn.frbb.tup.sistema_bancario.controller.validations;

import java.util.List;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;

public class AccountValidator {
    private final List<AccountValidationStrategy> strategies;

    public AccountValidator(List<AccountValidationStrategy> strategies) {
        this.strategies = strategies;
    }

    //valida la cuenta con HasAccount y HasCurrencyAccountType
    public boolean validate(Account account, Client client) {
        for (AccountValidationStrategy strategy : strategies) {
            if (!strategy.isValid(account, client)) {
                return false; //no agrega
            }
        }
        return true; //si agrega
    }
}
