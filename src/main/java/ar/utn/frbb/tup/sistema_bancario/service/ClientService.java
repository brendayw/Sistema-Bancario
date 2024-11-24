package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.persitence.dao.ClientDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientDao clientDao;

    // Crea un cliente nuevo
    public Client createClient(ClientDto clientDto) {
        return clientDao.createClient(clientDto);
    }

    // Obtener cliente por id
    public Client getClientById(long id_client) {
        return clientDao.getClientById(id_client);  // Cambiado a getClientById
    }

    // Actualizar el cliente
    public Client updateClient(long id_client, ClientDto clientDto) {
        if (clientDao.updateClient(clientDto)) {
            return getClientById(id_client);
        }
        return null;
    }

    // Eliminar cliente
    public boolean deleteClient(long id_client) {
        return clientDao.deleteClient(id_client);
    }
}