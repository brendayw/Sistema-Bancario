package ar.utn.frbb.tup.sistema_bancario.service;


import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.dao.LoanDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus.PENDING;

@Service
public class LoanService {
    public final LoanDao loanDao;

    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    //solicitar prestamo
    public LoanDto requestLoan(LoanDto loanDto) {
        Loan loan = new Loan(
                loanDto.getId_loan(),
                loanDto.getId_client(),
                loanDto.getAmount(),
                loanDto.getInterestRate(),
                loanDto.getTermMonths(),
                LoanStatus.PENDING,
                LocalDateTime.now().toString(),
                null
        );

        loanDao.saveLoan(loan);
        loanDto.setId_loan(loan.getId_loan());
        loanDto.setRequestDate(loan.getRequestDate());
        return loanDto;
    }

    //obtiene prestamo por id del cliente
    public List<LoanDto> getLoansByClientId(long id_client) {
        List<Loan> loans = loanDao.findLoanByClientId(id_client);

        return loans.stream()
                .map(loan -> new LoanDto(
                        loan.getId_loan(),
                        loan.getId_client(),
                        loan.getAmount(),
                        loan.getInterestRate(),
                        loan.getTermMonths(),
                        loan.getLoanStatus(),
                        loan.getRequestDate(),
                        loan.getApprovalDate()
                ))
                .toList();
    }

    //obtiene el prestamo por id
    public LoanDto getLoanById(long id_loan) throws LoanNotFound {
        List<Loan> loans = loanDao.findLoanById(id_loan);
        if (loans.isEmpty()) {
            throw new LoanNotFound("El préstamo con id " + id_loan + "no existe.");
        }

        Loan loan = loans.get(0);

        return new LoanDto(
                loan.getId_loan(),
                loan.getId_client(),
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getTermMonths(),
                loan.getLoanStatus(),
                loan.getRequestDate(),
                loan.getApprovalDate()
        );
    }

    //aprueba el prestamo
    public LoanDto approveLoan(long id_loan) throws LoanNotFound {
        List<Loan> loans = loanDao.findLoanById(id_loan);
        if (loans.isEmpty()) {
            throw new LoanNotFound("El préstamo con id " + id_loan + " no existe.");
        }

        Loan loan = loans.get(0);
        if (loan.getLoanStatus() != LoanStatus.PENDING) {
            throw new LoanNotFound("El préstamo no se puede aprobar.");
        }
        loan.setLoanStatus(LoanStatus.APROVED);
        loan.setApprovalDate(LocalDateTime.now().toString());
        loanDao.updateLoanStatus(id_loan, LoanStatus.APROVED);

        return new LoanDto(
                    loan.getId_loan(),
                    loan.getId_client(),
                    loan.getAmount(),
                    loan.getInterestRate(),
                    loan.getTermMonths(),
                    loan.getLoanStatus(),
                    loan.getRequestDate(),
                    loan.getApprovalDate()
        );
    }

    //guarda el prestamo
    public LoanDto saveLoan(LoanDto loanDto) {
        // Validaciones de negocio
        if (loanDto.getAmount() <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a 0.");
        }

        if (loanDto.getTermMonths() <= 0) {
            throw new IllegalArgumentException("El plazo del préstamo debe ser mayor a 0.");
        }

        loanDao.createLoan(loanDto);

        return loanDto;
    }

    public List<LoanDto> getAllLoans() {
        Map<Long, LoanDto> loans = loanDao.findAllLoans();
        return loans.values().stream()
                .map(loan -> new LoanDto(
                        loan.getId_loan(),
                        loan.getId_client(),
                        loan.getAmount(),
                        loan.getInterestRate(),
                        loan.getTermMonths(),
                        loan.getLoanStatus(),
                        loan.getRequestDate(),
                        loan.getApprovalDate()
                ))
                .toList();
    }
}
