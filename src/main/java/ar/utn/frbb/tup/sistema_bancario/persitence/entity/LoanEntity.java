package ar.utn.frbb.tup.sistema_bancario.persitence.entity;

public class LoanEntity {

    private Long id;
    private Long customerId;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
    private String status; // Estados: PENDING, APPROVED, REJECTED
    private String requestDate; // Fecha de solicitud en formato String
    private String approvalDate; // Fecha de aprobación en formato String

    // Constructor completo
    public LoanEntity(Long id, Long customerId, Double amount, Double interestRate,
                      Integer termMonths, String status, String requestDate, String approvalDate) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.status = status;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
    }

    // Constructor vacío
    public LoanEntity() {}

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
}
