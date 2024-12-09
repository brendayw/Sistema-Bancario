package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;

import java.util.List;

public class LoanDto {
    private double requestedAmount;
    private int repaymentPeriodMonths;
    private double anualInterestRate;
    private String loanStatus;
    private String requestDate;
    private List<PaymentDto> payments;

    //constructor


    public LoanDto(double requestedAmount, int repaymentPeriodMonths, double anualInterestRate, String loanStatus, String requestDate, List<PaymentDto> payments) {
        this.requestedAmount = requestedAmount;
        this.repaymentPeriodMonths = repaymentPeriodMonths;
        this.anualInterestRate = anualInterestRate;
        this.loanStatus = loanStatus;
        this.requestDate = requestDate;
        this.payments = payments;
    }

    //constructor sin parametros para compatibilidad
    public LoanDto() {}

    //getters & setters
    public double getRequestedAmount() {
        return requestedAmount;
    }
    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public int getRepaymentPeriodMonths() {
        return repaymentPeriodMonths;
    }
    public void setRepaymentPeriodMonths(int repaymentPeriodMonths) {
        this.repaymentPeriodMonths = repaymentPeriodMonths;
    }

    public double getAnualInterestRate() {
        return anualInterestRate;
    }
    public void setAnualInterestRate(double anualInterestRate) {
        this.anualInterestRate = anualInterestRate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public List<PaymentDto> getPayments() {
        return payments;
    }
    public void setPayments(List<PaymentDto> payments) {
        this.payments = payments;
    }
}
