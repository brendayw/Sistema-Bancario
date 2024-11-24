package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.PaymentPlan;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.service.LoanService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public LoanDto applyForLoan(@RequestBody LoanDto loanRequest) {
        // Creamos el préstamo y lo guardamos en el sistema
        LoanDto loan = loanService.createLoan(
                loanRequest.getAmount(),
                loanRequest.getIdLoan(), // Suponemos que el cliente tiene un ID único
                12 // Puede ser variable (plazo en meses)
        );
        return loan;
    }

    @GetMapping("/{loanId}")
    public PaymentPlan getPaymentPlan(@PathVariable long loanId) throws LoanNotFound {
        LoanDto loanDto = loanService.loanDao.getLoanById(loanId);
        return loanService.generatePaymentPlan(loanDto);
    }

    @PostMapping("/approve/{loanId}")
    public String approveLoan(@PathVariable long loanId) throws LoanNotFound {
        loanService.approveLoan(loanId);
        return "Loan approved";
    }
}

