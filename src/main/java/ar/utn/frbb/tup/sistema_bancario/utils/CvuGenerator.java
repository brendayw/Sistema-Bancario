package ar.utn.frbb.tup.sistema_bancario.utils;

import java.util.Random;

public class CvuGenerator {
    public static String generarCvu() {
        Random random = new Random();
        StringBuilder cvu = new StringBuilder();

        for (int i = 0; i < 22; i++) {
            int digit = random.nextInt(10);
            cvu.append(digit);
        }

        return cvu.toString();
    }
}
