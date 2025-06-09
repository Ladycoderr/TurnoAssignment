package com.example.Loan.Origination.System.service.strategy.loanDecision;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import org.springframework.stereotype.Component;

@Component("BUSINESS")
public class BusinessLoanDecisionStrategy implements LoanDecisionStrategy {
    @Override
    public LoanStatus decide(double amount) {
        return amount > 500000 ? LoanStatus.UNDER_REVIEW : LoanStatus.APPROVED_BY_SYSTEM;
    }
}
