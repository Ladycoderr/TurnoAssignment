package com.example.Loan.Origination.System.service.strategy.loanDecision;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;

public interface LoanDecisionStrategy {
    LoanStatus decide(double amount);
}
