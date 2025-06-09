package com.example.Loan.Origination.System.entities;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agent_loan_mapping", indexes = {
        @Index(name = "idx_agentloanmapping_agent_id", columnList = "agentId"),
        @Index(name = "idx_agentloanmapping_loan_id", columnList = "loanId")})
@IdClass(AgentLoanId.class)
public class AgentLoanMapping {
    @Id
    private String agentId;
    @Id
    private String loanId;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;
}
