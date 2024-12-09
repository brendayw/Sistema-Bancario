package ar.utn.frbb.tup.sistema_bancario.model;

public class Payment {
    private int month;
    private double amount;

    //constructor
    public Payment(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    // Getters & setters
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Pago:" +
                "\n mensual: " + month +
                "\n monto: " + amount;
    }
}
