package ar.utn.frbb.tup.sistema_bancario.model;

public class Payment {
    private final int month;
    private final double amount;

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

    @Override
    public String toString() {
        return "Pago:" +
                "\n mensual: " + month +
                "\n monto: " + amount;
    }
}
