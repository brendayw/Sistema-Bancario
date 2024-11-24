package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.PaymentPlan;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.dao.LoanDao;

public class LoanService {
    public final LoanDao loanDao;

    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public LoanDto createLoan(double amount, long id_client, int termMonths) {
        if (amount <= 0 || termMonths <= 0) {
            throw new IllegalArgumentException("El monto y el plazo deben ser mayores a cero");
        }

        LoanDto loan = new LoanDto(
                System.currentTimeMillis(), // Esto puede ser un ID generado automáticamente
                amount,
                new java.util.Date(),
                null, // Fecha de aceptación será asignada cuando el préstamo sea aprobado
                LoanStatus.PENDING
        );

        loanDao.createLoan(loan);
        return loan;
    }

    public PaymentPlan generatePaymentPlan(LoanDto loanDto) {
        double interestRate = 43.0;
        int termMonths = 12;

        Loan loan = new Loan(loanDao);
        loan.setAmount(loanDto.getAmount());
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMonths);

        PaymentPlan paymentPlan = new PaymentPlan(loan);
        return paymentPlan;
    }

    public void approveLoan(long id_loan) throws LoanNotFound {
        LoanDto loan = loanDao.getLoanById(id_loan);
        if (loan != null && loan.getLoanStatus() == LoanStatus.PENDING) {
            loan.setLoanStatus(LoanStatus.APROVED);
            loanDao.updateLoanStatus(id_loan, LoanStatus.APROVED);
        } else {
            throw new LoanNotFound("El préstamo no se puede aprobar.");
        }
    }
}
