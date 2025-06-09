package com.example.Loan.Origination.System.service.Impl;

import com.example.Loan.Origination.System.dtos.AgentRequest;
import com.example.Loan.Origination.System.entities.Agent;
import com.example.Loan.Origination.System.entities.AgentLoanId;
import com.example.Loan.Origination.System.entities.AgentLoanMapping;
import com.example.Loan.Origination.System.entities.Loan;
import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import com.example.Loan.Origination.System.repository.AgentLoanMappingRepository;
import com.example.Loan.Origination.System.repository.AgentRepository;
import com.example.Loan.Origination.System.repository.LoanRepository;
import com.example.Loan.Origination.System.service.AgentService;
import com.example.Loan.Origination.System.service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepo;
    @Autowired
    private LoanRepository loanRepo;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AgentLoanMappingRepository mappingRepo;
    public void assignLoanToAgent(Loan loan) {
        Agent agent = agentRepo.findRandomAvailableAgent();
        if (agent == null) return;
        AgentLoanMapping mapping = new AgentLoanMapping();
        mapping.setAgentId(String.valueOf(agent.getAgentId()));
        mapping.setLoanId(loan.getLoanId());
        mapping.setLoanStatus(LoanStatus.UNDER_REVIEW);
        mappingRepo.save(mapping);
        notificationService.sendAgentAssignment(agent, loan);
        notificationService.sendManagerNotification(agent.getManagerId(), loan);
    }
    @Override
    public void handleAgentDecision(String agentId, String loanId, String decision) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found"));
        AgentLoanMapping mapping = mappingRepo.findById(new AgentLoanId(agentId, loanId))
                .orElseThrow(() -> new NoSuchElementException("Agent-Loan mapping not found"));
        LoanStatus status;
        switch (decision.toUpperCase()) {
            case "APPROVE":
                status = LoanStatus.APPROVED_BY_AGENT;
                notificationService.sendSmsToCustomer(loan);
                break;
            case "REJECT":
                status = LoanStatus.REJECTED_BY_AGENT;
                break;
            default:
                throw new IllegalArgumentException("Invalid decision: " + decision);
        }
        loan.setApplicationStatus(status);
        mapping.setLoanStatus(status);
        loanRepo.save(loan);
        mappingRepo.save(mapping);
    }


    @Override
    public String createAgent(AgentRequest agentRequest) {
        agentRepo.findByPhone(agentRequest.getPhone()).ifPresent(a -> {
            throw new IllegalArgumentException("Agent with this phone number already exists.");
        });

        Agent agent = Agent.builder()
                .phone(agentRequest.getPhone())
                .managerId(agentRequest.getManagerId())
                .build();

        return agentRepo.saveAndFlush(agent).getAgentId();
    }
}
