package ar.utn.frbb.tup.sistema_bancario.persitence.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class AccountEntity {
    private static final Random random = new Random();
    private static final List<String> words = List.of(
            "libro", "cielo", "nube", "auto", "luz", "estrella", "mar", "rojo",
            "sol", "luna", "agua", "tierra", "fuego", "aire", "luz", "oscuro",
            "claro", "pelo", "rulo", "estrella", "cometa", "color", "fondo",
            "papel", "bufanda", "frio", "calor"
    );

    public static String generateUAN() {
        return UUID.randomUUID().toString().substring(0,3);
    }

    public static String generateCvu() {
        return UUID.randomUUID().toString().replace("-","").substring(0,22);
    }

    public static String generateAlias() {
        StringBuilder alias = new StringBuilder();

        for (int i = 0; i <3; i++) {
            String word = words.get(random.nextInt(words.size()));
            alias.append(word);
            if (i <2) {
                alias.append(" ");
            }
        }
        return alias.toString();
    }
}
