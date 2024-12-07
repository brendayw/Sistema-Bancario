package ar.utn.frbb.tup.sistema_bancario.service.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.scorescredit.CreditScoreTooLowException;
import ar.utn.frbb.tup.sistema_bancario.persitence.LoanDaoInterface;
import ar.utn.frbb.tup.sistema_bancario.service.LoanServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.service.ScoreCreditServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoanService implements LoanServiceInterface {
    @Autowired
    private LoanDaoInterface loanDao;

    @Autowired
    private ScoreCreditServiceInterface scoreCredit;

    //solicitar prestamo
    @Override
    public Loan requestLoan(Loan loan,long id_client) throws CreditScoreTooLowException {
        if (loanDao.findLoanByClientId(loan.getId_client()) != null) {
            throw new CreditScoreTooLowException("El score es muy bajo.");
        }
        loanDao.saveLoan(loan);
        return loan;
    }

    @Override
    public List<Loan> getAllLoans() {
        Map<Long, Loan> loansMap = loanDao.findAllLoans();
        return new ArrayList<>(loansMap.values());
    }

    //obtiene el prestamo por id
    @Override
    public Loan getLoanById(long id_loan) throws LoanNotFound {
        Loan loans = loanDao.findLoanById(id_loan);
        if (loans == null) {
            throw new LoanNotFound("El préstamo con id " + id_loan + "no existe.");
        }
        return loans;
    }

    //obtiene un prestamo por id del cliente
    @Override
    public Loan getLoanByClientId(long id_client) throws ClientNotFound {
        List<Loan> loans = loanDao.findLoanByClientId(id_client);
        if (loans == null) {
            throw new ClientNotFound("El cliente con id: " + id_client + " no existe.");
        }
        return loans.get(0);
    }

    //obtener todos los prestamos por id del cliente
    @Override
    public List<Loan> getLoansByClientId(long id_client) throws ClientNotFound {
        List<Loan> loans = loanDao.findLoanByClientId(id_client);
        if (loans == null || loans.isEmpty()) {
            throw new ClientNotFound("El cliente con id: " + id_client + " no tiene prestamos solicitados.");
        }
        return loans;
    }

    @Override
    public Loan deleteLoan(long id_loan) throws LoanNotFound {
        Loan loan = loanDao.findLoanById(id_loan);
        if (loan == null) {
            throw new LoanNotFound("El prestamo con id: " + id_loan + " no existe.");
        }
        loan.setLoanStatus(LoanStatus.CLOSED);
        loanDao.updateLoanStatus(id_loan, LoanStatus.CLOSED);
        return loan;
    }

    @Override
    public Loan updateStatusById(long id_loan) throws LoanNotFound {
       Loan loan = loanDao.findLoanById(id_loan);
       if (loan == null) {
           throw new LoanNotFound("El prestamo no existe.");
       }

       //posibles opciones para el estado
        int score = scoreCredit.getScoreCredit(loan.getId_client());

       LoanStatus newLoanStatus;
        if (score >= 700) {
            newLoanStatus = LoanStatus.APROVED;
        } else if (score >= 500) {
            newLoanStatus = LoanStatus.PENDING;
        } else {
            newLoanStatus = LoanStatus.REJECTED;
        }
        loan.setLoanStatus(newLoanStatus);
        loanDao.saveLoan(loan);
        return loan;
    }
}
