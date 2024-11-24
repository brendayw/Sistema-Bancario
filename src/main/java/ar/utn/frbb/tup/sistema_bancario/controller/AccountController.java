package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.AccountDto;
import ar.utn.frbb.tup.sistema_bancario.model.Account;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.accounts.AccountAlreadyExists;
import ar.utn.frbb.tup.sistema_bancario.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //endpoint para crear cuenta
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto) {
        Account account = new Account(accountDto.getAccountType(), accountDto.getCurrencyType(), null);
        /*accountService.activateAccount(account, 1); //ejemplo de id_client*/
        try {
            accountService.activateAccount(account, 1);
            return new ResponseEntity<>("Cuenta creada con exito.",HttpStatus.CREATED);
        } catch (AccountAlreadyExists e) {
            return new ResponseEntity<>("La cuenta ya existe", HttpStatus.BAD_REQUEST);
        }
    }
}
