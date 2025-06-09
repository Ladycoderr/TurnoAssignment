package com.example.Loan.Origination.System.repository;

import com.example.Loan.Origination.System.entities.Agent;
import com.example.Loan.Origination.System.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository  extends JpaRepository<Agent,String> {
    @Query(value = "SELECT * FROM agent ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Agent findRandomAvailableAgent();
    Optional<Agent> findByPhone(String phone);
}
