package ar.utn.frbb.tup.sistema_bancario.controller.validations;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;

public class SameCurrecyAccountValidation implements AccountValidationStrategy {
    @Override
    public boolean isValid(Account account, Client client) {
        return !client.HasCurrencyAccountType(account.getCurrencyType());
    }
}
