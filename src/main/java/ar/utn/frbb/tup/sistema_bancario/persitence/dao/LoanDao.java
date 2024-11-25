package ar.utn.frbb.tup.sistema_bancario.persitence.dao;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.LoanDto;
import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanDao {
    private Map<Long, Loan> loanDatabase = new HashMap<>();

    // Crea un nuevo préstamo
    public void createLoan(LoanDto loanDto) {
        Loan loan = new Loan(
                loanDto.getId_loan(),
                loanDto.getId_client(),
                loanDto.getAmount(),
                loanDto.getInterestRate(),
                loanDto.getTermMonths(),
                LoanStatus.PENDING,
                loanDto.getRequestDate(),
                loanDto.getApprovalDate()
        );
        loanDatabase.put(loan.getId_loan(), loan);
    }

    // Busca un préstamo por ID
    public List<Loan> findLoanById(long id_client) {
        return loanDatabase.values().stream()
                .filter(loan -> loan.getId_client() == id_client)
                .toList();
    }

    //buscar prestamos por id del cliente
    public List<Loan> findLoanByClientId(long id_client) {
        return loanDatabase.values().stream()
                .filter(loan -> loan.getId_client() == id_client)
                .toList();
    }

    // Actualiza el estado de un préstamo
    public void updateLoanStatus(long id_loan, LoanStatus status) throws LoanNotFound {
        Loan loan = loanDatabase.get(id_loan);
        if (loan == null) {
            throw new LoanNotFound("Préstamo con ID " + id_loan + " no encontrado.");
        }

        loan.setLoanStatus(status);

        LoanDto loanDto = new LoanDto(
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

    // Devuelve todos los préstamos
    public Map<Long, LoanDto> findAllLoans() {
        Map<Long, LoanDto> loanDtoMap = new HashMap<>();

        for (Map.Entry<Long, Loan> entry : loanDatabase.entrySet()) {
            Loan loan = entry.getValue();
            LoanDto loanDto = new LoanDto(
                    loan.getId_loan(),
                    loan.getId_client(),
                    loan.getAmount(),
                    loan.getInterestRate(),
                    loan.getTermMonths(),
                    loan.getLoanStatus(),
                    loan.getRequestDate(),
                    loan.getApprovalDate()
            );
            loanDtoMap.put(entry.getKey(), loanDto);
        }
        return loanDtoMap;
    }

    //guarda el prestamo
    public void saveLoan(Loan loan) {
        loanDatabase.put(loan.getId_loan(), loan);
    }

    // Elimina un préstamo por ID
    public void deleteLoan(long id_loan) throws LoanNotFound {
        if (!loanDatabase.containsKey(id_loan)) {
            throw new LoanNotFound("No se puede eliminar. Préstamo con id: " + id_loan + " no encontrado.");
        }
        loanDatabase.remove(id_loan);
    }
}
