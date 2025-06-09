package com.example.Loan.Origination.System.service.strategy.loanDecision;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import org.springframework.stereotype.Component;

@Component("HOME")
public class HomeLoanDecisionStrategy implements LoanDecisionStrategy {
    @Override
    public LoanStatus decide(double amount) {
        return  amount > 800000 ? LoanStatus.REJECTED_BY_SYSTEM : LoanStatus.UNDER_REVIEW;
    }
}