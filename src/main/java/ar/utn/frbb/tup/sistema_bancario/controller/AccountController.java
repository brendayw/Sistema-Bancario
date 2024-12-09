package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountTypeNotSupported;
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
    @RequestMapping(value = "/{id_account}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable long id_account) throws AccountNotFound {
        return accountService.getAccountById(id_account);
    }

    // POST: Crear una cuenta nueva
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            //llama al servicio para crear la cuenta
            Account createdAccount = accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (AccountAlreadyExists e) {
            //si la cuenta existe
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (AccountTypeNotSupported e) {
            //si el tipo de cuenta a crear no es valido
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (AccountNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            //otros
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // DELETE: Desactivar cuenta por id de la cuenta
    @PutMapping("/{id_account}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateAccount(@PathVariable long id_account) throws AccountNotFound {
        accountService.deactivateAccountById(id_account);
    }
}