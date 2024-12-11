package ar.utn.frbb.tup.sistema_bancario.service;


import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.enums.AccountType;
import ar.utn.frbb.tup.sistema_bancario.model.enums.CurrencyType;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeNotSupported;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.impl.AccountDao;
import ar.utn.frbb.tup.sistema_bancario.persitence.impl.ClientDao;
import ar.utn.frbb.tup.sistema_bancario.service.impl.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_METHOD)
public class AccountServiceTest {

    @Mock private AccountDao accountdao;
    @Mock private ClientDao clientDao;
    @InjectMocks private AccountService accountService;

    //se crea la cuenta con exito
    @Test
    public void testCreateAccount() {
        long id = 123456;
        var accountType = AccountType.CHECKINGPESOS;
        Account account = new Account();
        account.setId_account(id);
        account.setAccountType(accountType);
        account.setCurrencyType(CurrencyType.PESOS);

        when(accountdao.findAccountById(id)).thenReturn(null);

        try {
            accountService.createAccount(account);
            System.out.println("Cuenta creada con exito");
        } catch (AccountAlreadyExists e) {
            System.out.println(e.getMessage());
        } catch (AccountTypeNotSupported e) {
            System.out.println(e.getMessage());
        } catch (AccountNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void activateAccount() {}

    //busca una cuenta por id que no es encontrada
    @Test
    public void getAccountById() {
        long id = 321654;
        when(accountdao.findAccountById(id)).thenReturn(null);

        try {
            accountService.getAccountById(id);
        } catch (AccountNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    //busca una cuenta por id del cliente
    @Test
    public void getAccountByIdHolder_ClientNotFound() {
        long idclient = 234567;
        when(clientDao.findClientById(idclient)).thenReturn(null);

        try {
            accountService.getAccountByIdHolder(idclient);
            System.out.println("Cuenta del cliente encontrada con exito.");
        } catch (AccountNotFound e) {
            System.out.println(e.getMessage());
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getAccountByIdHolder_Success() throws ClientNotFound, AccountNotFound {
        long clientId = 234567;
        Client client = new Client();
        client.setId_client(clientId);

        Account account = new Account();
        account.setId_account(123456);
        account.setId_client(clientId);

        // Mock de los DAOs
        when(clientDao.findClientById(clientId)).thenReturn(client);  // El cliente existe
        when(accountdao.findAccountByIdHolder(clientId)).thenReturn(account);  // La cuenta existe

        // Ejecutamos el método del servicio
        Account result = accountService.getAccountByIdHolder(clientId);

        // Verificamos que la cuenta devuelta no sea null y que tenga los datos correctos
        assertNotNull(result);
        assertEquals(account.getId_account(), result.getId_account());
        assertEquals(account.getId_client(), result.getId_client());
        System.out.println("Cuenta del cliente con id: " + clientId + " encontrada con exito.");
    }

    @Test
    public void deactivateAccountById() {}


}
