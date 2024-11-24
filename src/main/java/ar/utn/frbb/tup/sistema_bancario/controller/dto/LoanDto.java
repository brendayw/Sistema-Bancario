package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;

import java.util.Date;

public class LoanDto {
    private long idLoan;
    private double amount;
    private Date requestDate;
    private Date acceptDate;
    private LoanStatus loanStatus;

    //constructor
    public LoanDto(long idLoan, double amount, Date requestDate, Date acceptDate, LoanStatus loanStatus) {
        this.idLoan = idLoan;
        this.amount = amount;
        this.requestDate = requestDate;
        this.acceptDate = acceptDate;
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "Loan DTO: " +
                "\n ID: " + idLoan +
                "\n Monto: " + amount +
                "\n Fecha de solicitud: " + requestDate +
                "\n Fecha de acepacion: " + acceptDate +
                "\n Estado del prestamo: " + loanStatus;
    }

    //getters & setters
    public long getIdLoan() {
        return idLoan;
    }
    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }
    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
