package ar.utn.frbb.tup.sistema_bancario.model;

public class Payment {
    private int month;
    private double amount;

    public Payment(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    // Getters & setters
    public int getMonth() {
        return month;
    }

    public double getAmount() {
        return amount;
    }
}
