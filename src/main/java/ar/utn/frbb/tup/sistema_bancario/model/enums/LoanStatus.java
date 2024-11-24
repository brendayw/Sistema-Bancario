package ar.utn.frbb.tup.sistema_bancario.model.enums;

import ar.utn.frbb.tup.sistema_bancario.model.Loan;

public enum LoanStatus {
    PENDING("PENDIENTE"),
    APROVED("APROBADO"),
    REJECTED("RECHAZADO"),
    DISBURD("DESEMBOLSADO"),
    CLOSED("CERRADO");

    private String type1;

    private LoanStatus(String type) {
        this.type1 = type1;
    }

    public static LoanStatus fromString(String typeStr) {
        for (LoanStatus type1 : LoanStatus.values()) {
            if (type1.type1.equals(typeStr)) {
                return type1;
            }
        }
        throw new IllegalArgumentException("Loan Status not valid:  " + typeStr);
    }
}
