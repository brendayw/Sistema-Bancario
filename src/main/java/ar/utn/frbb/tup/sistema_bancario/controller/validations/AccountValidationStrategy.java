package ar.utn.frbb.tup.sistema_bancario.controller.validations;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;

public interface AccountValidationStrategy {
    boolean isValid(Account account, Client client);
}
