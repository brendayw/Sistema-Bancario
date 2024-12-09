package ar.utn.frbb.tup.sistema_bancario.controller.dto;

public class PaymentDto {
    private int month;
    private double amount;

    //constructor
    public PaymentDto(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    //getters and setters
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
}
