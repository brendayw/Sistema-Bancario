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
        // Validación -> ESTO CAPAZ PODRIA IR EN OTRO LADO
        if (loan.getTermMonths() <= 0 || loan.getAmount() <= 0 || loan.getInterestRate() <= 0) {
            throw new IllegalArgumentException("Los valores del préstamo no son válidos.");
        }

        double monthlyRate = loan.getInterestRate() / 100 / 12; // Tasa mensual
        double monthlyPayment = (loan.getAmount() * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -loan.getTermMonths()));

        //REDONDEO
        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;

        for (int i = 1; i <= loan.getTermMonths(); i++) {
            payments.add(new Payment(i, monthlyPayment));
        }
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
