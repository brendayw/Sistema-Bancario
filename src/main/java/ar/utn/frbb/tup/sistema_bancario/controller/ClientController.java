package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.service.impl.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientServiceInterface clientService;

    //get -> obtiene todos los clientes
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // get -> obtener cliente por id
    @GetMapping("/{id_client}")
    public Client getClientById(@PathVariable long id_client) throws ClientNotFound {
        return clientService.getClientById(id_client);
    }

    //actualizar cliente
    @RequestMapping(value = "/{id_client}", method = RequestMethod.PUT)
    public Client updateClient(@PathVariable long id_client, @RequestBody ClientDto clientDto) throws ClientNotFound {
        // Buscar el cliente por ID
        Client client = clientService.getClientById(id_client);

        // Actualizar los campos si no son nulos
        if (clientDto.getEmail() != null) {
            client.setEmail(clientDto.getEmail());
        }
        if (clientDto.getPhone() != null) {
            client.setPhone(clientDto.getPhone());
        }

        // Llamar al servicio para actualizar el cliente en la base de datos
        return clientService.updateClient(client);
    }

    //delete -> desactiva el cliente por id
    @RequestMapping(value = "/{id_client}", method = RequestMethod.DELETE)
    public void deactivateClient(@PathVariable long id_client) throws ClientNotFound {
        clientService.deactivateClient(id_client);
    }

}
