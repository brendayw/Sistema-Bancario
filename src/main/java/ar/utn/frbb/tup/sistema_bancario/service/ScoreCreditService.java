package ar.utn.frbb.tup.sistema_bancario.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ScoreCreditService {
    private final Random random = new Random();

    public ScoreCreditService() {}

    //obtener puntaje
    public int getScoreCredit(long id_client) {

        int score = 300 + random.nextInt(551);

        try {
            System.out.println("Puntaje crediticio simulado para el cliente " + id_client + ": " + score);
            return score;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el puntaje crediticio", e);
        }
    }
}
