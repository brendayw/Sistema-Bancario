package ar.utn.frbb.tup.sistema_bancario.model.enums;

public enum CurrencyType {
    PESOS("PESOS"),
    DOLARES("DOLARES");

    private String type1;

    private CurrencyType(String type1) {
        this.type1 = type1;
    }

    public static CurrencyType fromString(String typeStr) {
        for (CurrencyType type1 : CurrencyType.values()) {
            if (type1.type1.equals(typeStr)) {
                return type1;
            }
        }
        throw new IllegalArgumentException("Currency Type not valid: " + typeStr);
    }
}
