package ar.utn.frbb.tup.sistema_bancario.controller;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.Client;
import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.scorescredit.CreditScoreTooLowException;
import ar.utn.frbb.tup.sistema_bancario.service.ClientServiceInterface;
import ar.utn.frbb.tup.sistema_bancario.service.LoanServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanServiceInterface loanService;

    @Autowired
    private ClientServiceInterface clientService;

    //obtener todos los prestamos
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    //solicita prestamo
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> requestLoan(@RequestBody LoanDto loanDto) {
        try {
            //obtener el cliente por id
            Client client = clientService.getClientById(loanDto.getId_client());

            // Si el cliente no existe, devolver error 400
            if (client == null) {
                return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.BAD_REQUEST);
            }

            // Convertir el LoanDto en un Loan, si es necesario.
            Loan loan = new Loan(loanDto.getId_loan(), loanDto.getId_client(), loanDto.getAmount(),
                    loanDto.getInterestRate(), loanDto.getTermMonths(), loanDto.getLoanStatus(),
                    loanDto.getRequestDate(), loanDto.getApprovalDate());

            try {
                // solicita préstamo
                loanService.requestLoan(loan, loanDto.getId_client());
                return new ResponseEntity<>("Préstamo solicitado con éxito", HttpStatus.CREATED);
            } catch (CreditScoreTooLowException e) {
                // excepcion si el score es muy bajo
                return new ResponseEntity<>("El score credit es del cliente es demasiado bajo", HttpStatus.BAD_REQUEST);
            }
        } catch (ClientNotFound e) {
            // no encuentra al cliente
            return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    //obtiene el prestamo por id
    @RequestMapping(value = "/{id_loan}", method = RequestMethod.GET)
    public Loan getLoanById(@PathVariable long id_loan) throws LoanNotFound {
        return loanService.getLoanById(id_loan);
    }

    //obtiene un prestamo por id del cliente
    @RequestMapping(value = "/client/{id_client}", method = RequestMethod.GET)
    public Loan getLoanByClientId(long id_client) throws ClientNotFound {
        return loanService.getLoanByClientId(id_client);
    }

    //obtiene todos los loans de un cliente
    @RequestMapping(value = "/client/id_client", method = RequestMethod.GET)
    public List<Loan> getLoansByClientId(long id_client) throws ClientNotFound {
        return loanService.getLoansByClientId(id_client);
    }

    //delete -> cierra el prestamo
    @RequestMapping(value = "/{id_loan}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable long id_loan) throws LoanNotFound {
        loanService.deleteLoan(id_loan);
    }

    @RequestMapping(value = "/{id_loan}/status", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateLoanStatus(@PathVariable long id_loan) {
        try {
            loanService.updateStatusById(id_loan);
            return new ResponseEntity<>("Estado del préstamo actualizado con éxito.", HttpStatus.OK);
        } catch (LoanNotFound e) {
            return new ResponseEntity<>("Préstamo no encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}

