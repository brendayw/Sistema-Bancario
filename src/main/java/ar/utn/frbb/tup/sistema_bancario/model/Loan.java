package ar.utn.frbb.tup.sistema_bancario.model;

import ar.utn.frbb.tup.sistema_bancario.persitence.dao.LoanDao;

public class Loan {
    private final LoanDao loanDAO;
    private double amount;         // Monto del préstamo
    private double interestRate;   // Tasa de interés anual
    private int termMonths;        // Plazo en meses (12 meses, 24 meses, etc.)

    public Loan(LoanDao loanDAO) {
        this.loanDAO = loanDAO;
    }

    // Métodos de validación (ya tienes estos)
    private boolean isValidLoanId(long idLoan) {
        return idLoan != 0;
    }

    private boolean isValidAmount(double amount) {
        return amount > 0;
    }

    private boolean isValidInterestRate(double interestRate) {
        return interestRate > 0 && interestRate <= 100;
    }

    private boolean isValidTermMonths(int termMonths) {
        return termMonths > 0 && termMonths <= 120; // de 1 a 10 años
    }

    // getters & setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (isValidAmount(amount)) {
            this.amount = amount;
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (isValidInterestRate(interestRate)) {
            this.interestRate = interestRate;
        }
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        if (isValidTermMonths(termMonths)) {
            this.termMonths = termMonths;
        }
    }
}
