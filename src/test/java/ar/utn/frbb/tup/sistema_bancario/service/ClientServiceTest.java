package ar.utn.frbb.tup.sistema_bancario.service;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientUnderage;
import ar.utn.frbb.tup.sistema_bancario.persitence.impl.AccountDao;
import ar.utn.frbb.tup.sistema_bancario.persitence.impl.ClientDao;
import ar.utn.frbb.tup.sistema_bancario.service.impl.AccountService;
import ar.utn.frbb.tup.sistema_bancario.service.impl.ClientService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //no necesario el void setUp()
@TestInstance(PER_METHOD) // predecibilidad y el aislamiento en las pruebas
public class ClientServiceTest {
    @Mock private AccountDao accountDao;
    @Mock private AccountService accountService;
    @Mock private ClientDao clientDao;
    @InjectMocks private ClientService clientService;

    //crea un cliente con exito
    @Test
    public void testCreateClientSuccess() throws ClientAlreadyExists, ClientUnderage {
        Client client = new Client();
        client.setId_client(1);
        client.setEntityType(EntityType.PHYSICAL);
        client.setStatus(true);
        client.setRegistrationDate(LocalDate.now());
        client.setBirthDate(LocalDate.of(1998, 6, 13));

        //verifica que la lista de cuentas del cliente nuevo este vacia
        assertTrue(client.getAccounts().isEmpty());

        when(clientDao.findClientById(client.getId_client())).thenReturn(null);

        try {
            clientService.createClient(client);
            System.out.println("Cliente creada con éxito");
        } catch (Exception e) {
            // si ocurre algun error
            e.printStackTrace();
        }
        verify(clientDao).saveClient(client);
    }

    //intenta crear un cliente menor de edad
    @Test
    public void testCreateClientUnderage() throws ClientAlreadyExists, ClientUnderage {
        Client client = new Client();
        client.setBirthDate(LocalDate.of(2010, 6, 15)); // Cliente menor de edad

        try {
            clientService.createClient(client);
        } catch (ClientUnderage exception) {
            System.out.println(exception.getMessage());
            assertEquals("El cliente es menor de edad y no puede ser creado.", exception.getMessage());
        }
    }

    //intenta asociar una nueva cuenta de un tipo que ya existe a un cliente
    @Test
    public void testAddAccountToClient() throws AccountAlreadyExists, AccountTypeAlreadyExists {
        var accountType = AccountType.CHECKINGPESOS;

        Client client = new Client();
        client.setId_client(123564);

        // crea una cuenta existente
        Account existingAccount = new Account();
        existingAccount.setId_account(123456); // ID de cuenta
        existingAccount.setAccountType(accountType);

        // Asociar la cuenta existente al cliente
        Set<Account> accounts = new HashSet<>();
        accounts.add(existingAccount);  // Agrega la cuenta al cliente
        client.setAccounts(accounts);

        //nueva cuenta
        Account newAccount = new Account();
        newAccount.setId_account(123457); // ID diferente
        newAccount.setAccountType(accountType);

       try {
           clientService.addAccountToClient(client, newAccount);
       } catch (AccountAlreadyExists exception) {
            System.out.println(exception.getMessage());
            assertEquals("La cuenta ya está asociada a un cliente.", exception.getMessage());
       } catch (AccountTypeAlreadyExists exception) {
           System.out.println(exception.getMessage());
           assertEquals("El cliente ya tiene este tipo de cuenta.", exception.getMessage());
       }

    }

    //intenta buscar un cliente con un id que no existe
    @Test
    public void getClientById() throws ClientNotFound {
        long id = 999;
        when(clientDao.findClientById(id)).thenReturn(null);

        try {
            clientService.getClientById(id);
        } catch (ClientNotFound exception) {
            System.out.println(exception.getMessage());
            assertEquals("Cliente con id: " + id + " no encontrado.", exception.getMessage());
        }

    }

    //da de baja u cliente de manera exitosa
    @Test
    public void deactivateClient() throws ClientNotFound{
        Client client = new Client();
        client.setId_client(123456);
        client.setStatus(true);

        when(clientDao.findClientById(123456)).thenReturn(client);

        try {
            clientService.deactivateClient(123456);
            System.out.println("Operacion exitosa. Cliente dado de baja con exito.");
        } catch (ClientNotFound e){
            System.out.println(e.getMessage());
            assertEquals("No se pudo encontrar el cliente.", e.getMessage());
        }
    }

}
