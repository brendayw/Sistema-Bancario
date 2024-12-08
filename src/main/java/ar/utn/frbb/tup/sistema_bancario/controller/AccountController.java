package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.AccountDto;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.service.AccountServiceInterface;

import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceInterface accountService;

    @Autowired
    private ClientServiceInterface clientService;

    // get -> obtener todas las cuentas
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // GET: Obtener cuenta por UAN
    @RequestMapping(value = "/{uan}", method = RequestMethod.GET)
    public Account getAccountByUan(@PathVariable String uan) throws AccountNotFound {
        return accountService.getAccountByUan(uan);
    }

    // POST: Crear una cuenta nueva
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto) {
        try {
            Client client = clientService.getClientById(accountDto.getHolder());
            if (client == null) {
                return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.BAD_REQUEST);
            }

            Account account = new Account(accountDto.getUan(), accountDto.getCvu(), accountDto.getAlias(),
                    accountDto.getAccountType(), accountDto.getCurrencyType(), client);
            try {
                accountService.createAccount(account);
                return new ResponseEntity<>("Cuenta creada con éxito.", HttpStatus.CREATED);
            } catch (AccountAlreadyExists e) {
                return new ResponseEntity<>("La cuenta ya existe", HttpStatus.BAD_REQUEST);
            }

        } catch (ClientNotFound e) {
            return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Desactivar cuenta por UAN
    @RequestMapping(value = "/{uan}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateAccount(@PathVariable String uan) throws AccountNotFound {
        accountService.deactivateAccount(uan);
    }
}