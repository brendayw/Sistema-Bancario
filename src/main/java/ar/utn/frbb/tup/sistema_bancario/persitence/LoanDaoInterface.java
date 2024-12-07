package ar.utn.frbb.tup.sistema_bancario.persitence;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;

import java.util.List;
import java.util.Map;

public interface LoanDaoInterface {
    void saveLoan(Loan loan);
    Loan createLoan(Loan loan);
    Loan findLoanById(long id_loan) throws LoanNotFound;
    List<Loan> findLoanByClientId(long id_client);
    void updateLoanStatus(long id_loan, LoanStatus status) throws LoanNotFound;
    Loan deleteLoan(long id_loan) throws LoanNotFound;
    Map<Long, Loan> findAllLoans();
}
