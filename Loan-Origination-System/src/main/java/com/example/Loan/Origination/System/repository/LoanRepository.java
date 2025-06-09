package com.example.Loan.Origination.System.repository;

import com.example.Loan.Origination.System.entities.Loan;
import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import com.example.Loan.Origination.System.service.LoanStatusCount;
import com.example.Loan.Origination.System.service.strategy.TopCustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {

    List<Loan> findByApplicationStatus(LoanStatus loanStatus);
    @Query("SELECT l.applicationStatus AS status, COUNT(l) AS count FROM Loan l GROUP BY l.applicationStatus")
    List<LoanStatusCount> countLoansByStatus();

    @Query(value = """
    SELECT l.customer_name AS customerName, COUNT(*) AS approvedCount
    FROM loans l
    WHERE l.application_status IN ('APPROVED_BY_SYSTEM', 'APPROVED_BY_AGENT')
    GROUP BY l.customer_name
    ORDER BY approvedCount DESC
    LIMIT :limit
""", nativeQuery = true)
    List<TopCustomerProjection> findTopApprovedCustomers(@Param("limit") int limit);
    Page<Loan> findByApplicationStatus(LoanStatus status, Pageable pageable);
}
