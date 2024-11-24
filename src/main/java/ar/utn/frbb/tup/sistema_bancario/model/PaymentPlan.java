package ar.utn.frbb.tup.sistema_bancario.model;

import java.util.ArrayList;
import java.util.List;

public class PaymentPlan {
    private final List<Payment> payments;

    public PaymentPlan(Loan loan) {
        this.payments = new ArrayList<>();
        calculatePayments(loan);
    }

    private void calculatePayments(Loan loan) {
        double monthlyRate = loan.getInterestRate() / 100 / 12; // Tasa mensual
        double monthlyPayment = (loan.getAmount() * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -loan.getTermMonths()));

        for (int i = 1; i <= loan.getTermMonths(); i++) {
            payments.add(new Payment(i, monthlyPayment));
        }
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
