package ar.utn.frbb.tup.sistema_bancario.model.enums;

public enum TransactionType {
    DEPOSIT("DEPOSITO"),
    WITHDRAWAL("RETIRO"),
    TRANSFER("TRANSFERENCIA"),
    PAYMENT("PAGO");

    private String type1;

    private TransactionType(String type) {
        this.type1 = type1;
    }

    public static TransactionType fromString(String str) {
        for (TransactionType type1 : TransactionType.values()) {
            if (type1.type1.equals(str)) {
                return type1;
            }
        }
        throw new IllegalArgumentException("Transaction not valid: " + str);
    }
}
