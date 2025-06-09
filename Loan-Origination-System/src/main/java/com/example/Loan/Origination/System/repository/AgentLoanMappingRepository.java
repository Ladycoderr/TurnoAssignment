package com.example.Loan.Origination.System.repository;

import com.example.Loan.Origination.System.entities.AgentLoanId;
import com.example.Loan.Origination.System.entities.AgentLoanMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentLoanMappingRepository extends JpaRepository<AgentLoanMapping, AgentLoanId> {
    List<AgentLoanMapping> findByAgentId(String agentId);
}