package ar.utn.frbb.tup.sistema_bancario.persitence.entity;

public class BaseEntity {
    private long id_client;

    public BaseEntity(long id_client) {
        this.id_client = id_client;
    }

    public long getId_client() {
        return id_client;
    }
}

