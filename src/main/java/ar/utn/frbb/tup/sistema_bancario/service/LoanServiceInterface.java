package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.scorescredit.CreditScoreTooLowException;

import java.util.List;

public interface LoanServiceInterface {
    Loan requestLoan(Loan loan, long id_client) throws CreditScoreTooLowException;
    List<Loan> getAllLoans();
    Loan getLoanById(long id_loan) throws LoanNotFound;
    Loan getLoanByClientId(long id_client) throws ClientNotFound;
    List<Loan> getLoansByClientId(long id_client) throws ClientNotFound;
    Loan deleteLoan(long id_loan) throws LoanNotFound;
    Loan updateStatusById(long id_loan) throws LoanNotFound;
}

