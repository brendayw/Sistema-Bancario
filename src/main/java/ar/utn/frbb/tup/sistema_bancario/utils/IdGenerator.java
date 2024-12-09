package ar.utn.frbb.tup.sistema_bancario.utils;

import java.util.UUID;

public class IdGenerator {
    public static String idNumberGenerator() {
        UUID uuId = UUID.randomUUID();

        String id_number = uuId.toString().replace("-", "").substring(0,12);

        return id_number;
    }
}
