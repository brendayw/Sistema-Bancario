package ar.utn.frbb.tup.sistema_bancario.persitence.impl;

import ar.utn.frbb.tup.sistema_bancario.model.Loan;
import ar.utn.frbb.tup.sistema_bancario.model.enums.LoanStatus;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.loans.LoanNotFound;
import ar.utn.frbb.tup.sistema_bancario.persitence.LoanDaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoanDao implements LoanDaoInterface {
    private Map<Long, Loan> loanDatabase = new HashMap<>();

    //guardar prestamo
    @Override
    public void saveLoan(Loan loan) {
        //loanDatabase.add(loan);
        loanDatabase.put(loan.getId_loan(), loan);
    }

    //crea un nuevo préstamo
    @Override
    public Loan createLoan(Loan loan) {
        loanDatabase.put(loan.getId_loan(), loan);
        return loan;
    }

    // Busca un préstamo por ID
    @Override
    public Loan findLoanById(long id_loan) throws LoanNotFound{
        Loan loan = loanDatabase.get(id_loan);
        if (loan == null) {
            throw new LoanNotFound("El prestamo no existe.");
        }
        return loan;
    }

    //buscar prestamos por id del cliente
    @Override
    public List<Loan> findLoanByClientId(long id_client) {
        return loanDatabase.values().stream()
                .filter(loan -> loan.getId_client() == id_client)
                .collect(Collectors.toList());
    }

    // Actualiza el estado de un préstamo
    @Override
    public void updateLoanStatus(long id_loan, LoanStatus status) throws LoanNotFound {
        Loan loan = loanDatabase.get(id_loan);
        if (loan == null) {
            throw new LoanNotFound("Préstamo con ID " + id_loan + " no encontrado.");
        }
        loan.setLoanStatus(status);
        loanDatabase.put(id_loan, loan);
    }

    // Elimina un préstamo por ID
    @Override
    public Loan deleteLoan(long id_loan) throws LoanNotFound {
        Loan loan = loanDatabase.get(id_loan);

        if (loan == null) {
            throw new LoanNotFound("El prestamo no puede cerrarse.");
        }
        loan.setLoanStatus(LoanStatus.CLOSED);
        loanDatabase.put(id_loan, loan);
        return loan;
    }

    @Override
    public Map<Long, Loan> findAllLoans() {
        return loanDatabase;
    }
}
