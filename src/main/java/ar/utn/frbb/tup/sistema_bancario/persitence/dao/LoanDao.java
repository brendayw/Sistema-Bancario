package ar.utn.frbb.tup.sistema_bancario.persitence.dao;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;

import java.util.HashMap;
import java.util.Map;

public class LoanDao {
    private Map<Long, LoanDto> loanDatabase = new HashMap<>();

    public void createLoan(LoanDto loan) {
        loanDatabase.put(loan.getIdLoan(), loan);
    }

    //busca el prestamo por id
    public LoanDto getLoanById(long idLoan) throws LoanNotFound {
        LoanDto loan = loanDatabase.get(idLoan);

        if (loan == null) {
            throw new LoanNotFound("Prestamo con id: " + idLoan + "no encontrado");
        }
        return loan;
    }

    public void updateLoanStatus(long idLoan, LoanStatus status) throws LoanNotFound {
        LoanDto loan = getLoanById(idLoan);
        loan.setLoanStatus(status);
    }

    public Map<Long, LoanDto> getAllLoans() {
        return loanDatabase;
    }
}
