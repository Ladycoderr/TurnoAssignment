package com.example.Loan.Origination.System.service.strategy.loanDecision;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import org.springframework.stereotype.Component;

@Component("AUTO")
public class AutoLoanDecisionStrategy implements LoanDecisionStrategy {
    @Override
    public LoanStatus decide(double amount) {
        return LoanStatus.APPROVED_BY_SYSTEM;
    }
}
