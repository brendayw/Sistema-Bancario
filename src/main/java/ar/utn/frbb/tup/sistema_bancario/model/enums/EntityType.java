package ar.utn.frbb.tup.sistema_bancario.model.enums;

public enum EntityType {
    PHYSICAL("F"), //fisica
    LEGAL("J");  //juridica

    private String type;

    private EntityType(String type) {
        this.type = type;
    }

    public static EntityType fromString(String typeStr) {
        for (EntityType type : EntityType.values()) {
            if (type.type.equals(typeStr)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Entity Type not valid: " + typeStr);  // Moved the exception outside the loop
    }
}