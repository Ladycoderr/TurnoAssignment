package com.example.Loan.Origination.System.service.strategy;

import com.example.Loan.Origination.System.entities.enums.LoanType;
import com.example.Loan.Origination.System.service.strategy.loanDecision.LoanDecisionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoanDecisionStrategyFactory {

    private final Map<String, LoanDecisionStrategy> strategies;

    @Autowired
    public LoanDecisionStrategyFactory(Map<String, LoanDecisionStrategy> strategies) {
        this.strategies = strategies;
    }

    public LoanDecisionStrategy getStrategy(LoanType type) {
        return strategies.get(type.name());
    }
}