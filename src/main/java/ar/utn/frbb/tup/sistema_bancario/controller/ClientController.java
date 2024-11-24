package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    //crea nuevo cliente
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDto clientDto) {
        Client client = clientService.createClient(clientDto);
        return ResponseEntity.status(201).body(client);
    }

    //obtener cliente por id
    @GetMapping("/{id_clinet}")
    public ResponseEntity<Client> getClientById(@PathVariable long id_client) {
        Client client = clientService.getClientById(id_client);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    //actualizar cliente
    @PutMapping("/{id_client}")
    public ResponseEntity<Client> updateClient(@PathVariable long id_client, @RequestBody ClientDto clientDto) {
        Client updatedClient = clientService.updateClient(id_client, clientDto);
        return updatedClient != null ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
    }

    //eliminar cliente
    @DeleteMapping("/{id_client}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id_client) {
        boolean deleted = clientService.deleteClient(id_client);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
