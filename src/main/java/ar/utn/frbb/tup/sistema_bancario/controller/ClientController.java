package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientServiceInterface clientService;

    //crea al cliente
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            // Llamar al servicio para crear el cliente
            Client createdClient = clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);

        } catch (ClientAlreadyExists e) {
            // si el cliente ya existe, respondemos con 409 (Conflict)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (ClientNotFound e) {
            // si no se encuentra el cliente, respondemos con 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // otros errores (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

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

    //agrega cuentas a un cliente
    @PostMapping("/{id_client}/accounts")
    public ResponseEntity<Client> addAccountToClient(@PathVariable long id_client, @RequestBody Account account) {
        try {
            // primero busca al cliente
            Client client = clientService.getClientById(id_client);
            // si no se encuentra el cliente arroja exception clientnotfound
            if (client == null) {
                throw new ClientNotFound("Cliente no encontrado con ID: " + id_client);
            }
            // Agrega la cuenta
            Client updatedClient = clientService.addAccountToClient(client, account);
            // retorna el cliente actualizado
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFound e) {
            // Cliente no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (AccountAlreadyExists e) {
            // Cuenta ya existe
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            // Otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
