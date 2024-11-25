package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.service.LoanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    //solicita prestamo
    @PostMapping("/apply")
    public ResponseEntity<LoanDto> requestLoan(@RequestBody LoanDto loanRequest) {
        LoanDto createdLoan = loanService.requestLoan(loanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }

    //obtener prestamo de cliente por id
    @GetMapping("/client/{id_client}")
    public ResponseEntity<List<LoanDto>> getLoansByCustomer(@PathVariable long id_client) {
        List<LoanDto> loans = loanService.getLoansByClientId(id_client);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable long id_loan) throws LoanNotFound {
        LoanDto loan = loanService.getLoanById(id_loan);
        return ResponseEntity.ok(loan);
    }

    //aprobar prestamo por id
    @PostMapping("{loanId}/approve/")
    public ResponseEntity<LoanDto> approveLoan(@PathVariable long id_loan) throws LoanNotFound {
        LoanDto approveLoan = loanService.approveLoan(id_loan);
        return ResponseEntity.ok(approveLoan);
    }

    //obtener todos los prestamos
    @GetMapping
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }
}

