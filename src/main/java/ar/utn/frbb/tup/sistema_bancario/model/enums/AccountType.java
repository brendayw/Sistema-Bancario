package ar.utn.frbb.tup.sistema_bancario.model.enums;

public enum AccountType {
    CHECKINGPESOS("CORRIENTE_PESOS"),
    CHECKINGDOLLARS("CORRIENTE_DOLARES"),
    SAVINGPESOS("AHORRO_PESOS"),
    SAVINGSDOLLARS("AHORRO_DOLARES");

    private String type;

    private AccountType(String status) {
        this.type = type;
    }

    public static AccountType fromString(String typeStr) {
        for (AccountType type : AccountType.values()) {
            if (type.type.equals(typeStr)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Account type not valid:  " + typeStr);
    }

    public String getType() { //buscar que hacia esto?
        return type;
    }
}
