package ar.utn.frbb.tup.sistema_bancario.model;

import java.util.ArrayList;
import java.util.List;

public class PaymentPlan {
    private double monthlyInterest;
    private double monthlyPayment;
    private List<Payment> payments;

    //constructor
    public PaymentPlan(double monthlyInterest, double monthlyPayment, List<Payment> payments) {
        this.monthlyInterest = monthlyInterest;
        this.monthlyPayment = monthlyPayment;
        this.payments = payments;
    }

    private void calculatePayments(double amount, double interestRate, int termMonths) {
        //convierta la tasa de interes anual a mensual
        this.monthlyInterest = interestRate / 12 / 100;

        //formula de amortizacion
        this.monthlyPayment = (amount * monthlyInterest) / (1 - Math.pow(1 + monthlyInterest, -termMonths));
        for (int i = 1; i <= termMonths; i++) {
            payments.add(new Payment(i, monthlyPayment));
        }
    }

    //getters and setters
    public double getMonthlyInterest() {
        return monthlyInterest;
    }
    public void setMonthlyInterest(double monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public List<Payment> getPayments() {
        return payments;
    }
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
