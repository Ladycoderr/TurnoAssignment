package com.example.Loan.Origination.System.service.Impl;

import com.example.Loan.Origination.System.entities.Loan;
import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import com.example.Loan.Origination.System.repository.LoanRepository;
import com.example.Loan.Origination.System.service.AgentService;
import com.example.Loan.Origination.System.service.strategy.LoanDecisionStrategyFactory;
import com.example.Loan.Origination.System.service.LoanProcessingInterface;
import com.example.Loan.Origination.System.service.strategy.loanDecision.LoanDecisionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LoanProcessingService implements LoanProcessingInterface {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanDecisionStrategyFactory loanDecisionStrategyFactory;

    @Autowired
    private AgentService agentService;
    private final Random random = new Random();
    @Override
    public void processLoans() {
        List<Loan> pendingLoans = loanRepository.findByApplicationStatus(LoanStatus.APPLIED);
        for (Loan loan : pendingLoans) {
            processLoan(loan);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void scheduleLoanProcessing() {
        processLoans();
    }

    private void processLoan(Loan loan) {
        try {
            Thread.sleep((25) * 1000L);
            double amount = loan.getLoanAmount();
            LoanDecisionStrategy strategy = loanDecisionStrategyFactory.getStrategy(loan.getLoanType());
            LoanStatus decision = strategy.decide(amount);
            loan.setApplicationStatus(decision);
            loanRepository.save(loan);
            if (loan.getApplicationStatus() == LoanStatus.UNDER_REVIEW) {
                agentService.assignLoanToAgent(loan);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
