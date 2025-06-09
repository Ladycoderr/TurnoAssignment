package com.example.Loan.Origination.System.service.Impl;

import com.example.Loan.Origination.System.entities.Agent;
import com.example.Loan.Origination.System.entities.Loan;
import com.example.Loan.Origination.System.service.NotificationService;
import org.springframework.stereotype.Service;
@Service
public class MockNotificationService implements NotificationService {

    @Override
    public void sendAgentAssignment(Agent agent, Loan loan) {
        System.out.printf("[NOTIFY] Assigned Loan %s to Agent %s%n", loan.getLoanId(), agent.getAgentId());
    }

    @Override
    public void sendManagerNotification(String manager, Loan loan) {
        System.out.printf("[NOTIFY] Manager %s: Loan %s is under review by your agent%n", manager, loan.getLoanId());
    }

    @Override
    public void sendSmsToCustomer(Loan loan) {
        System.out.printf("[SMS] Loan %s has been approved. Sent to %s%n", loan.getLoanId(), loan.getCustomerPhone());
    }
}
