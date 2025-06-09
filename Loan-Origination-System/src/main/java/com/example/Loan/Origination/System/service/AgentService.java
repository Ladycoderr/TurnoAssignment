package com.example.Loan.Origination.System.service;

import com.example.Loan.Origination.System.dtos.AgentRequest;
import com.example.Loan.Origination.System.entities.Loan;

public interface AgentService {
    void assignLoanToAgent(Loan loan);
    void handleAgentDecision(String agentId, String loanId, String decision);
    String createAgent(AgentRequest agentRequest);
}
