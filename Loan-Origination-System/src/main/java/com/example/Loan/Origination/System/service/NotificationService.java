package com.example.Loan.Origination.System.service;

import com.example.Loan.Origination.System.entities.Agent;
import com.example.Loan.Origination.System.entities.Loan;

public interface NotificationService {
    void sendAgentAssignment(Agent agent, Loan loan);
    void sendManagerNotification(String manager, Loan loan);
    void sendSmsToCustomer(Loan loan);
}