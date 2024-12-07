package ar.utn.frbb.tup.sistema_bancario.controller.dto;

import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;

public class LoanDto {
    private long id_loan;
    private long id_client;
    private double amount; //cantidad
    private double interestRate; //taza de interes
    private int termMonths; //plazo en meses
    private LoanStatus loanStatus; //estado del prestamo
    private String requestDate;
    private String approvalDate;

    //constructor
    public LoanDto(long id_loan, long id_client, double amount, double interestRate, int termMonths, LoanStatus loanStatus, String requestDate, String approvalDate) {
        this.id_loan = id_loan;
        this.id_client = id_client;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.loanStatus = loanStatus;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
    }

    //constructor sin parametros para compatibilidad
    public LoanDto() {}

    //getters & setters
    public long getId_loan() {
        return id_loan;
    }
    public void setId_loan(long id_loan) {
        this.id_loan = id_loan;
    }

    public long getId_client() {
        return id_client;
    }
    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }
    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

}
