package com.example.Loan.Origination.System.entities;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import com.example.Loan.Origination.System.entities.enums.LoanType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Entity
@Table(name = "loans",
        indexes = {
                @Index(name = "idx_loans_application_status", columnList = "application_status"),
                @Index(name = "idx_loans_customer_name", columnList = "customer_name")
        })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "loan_id")
    private String loanId;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;
    @Column(name = "loan_amount", nullable = false)
    private double loanAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;
    @Enumerated(EnumType.STRING)
    @Column(name = "application_status",nullable = false)
    private LoanStatus applicationStatus;
    @Column(name = "created_at")
    private LocalDateTime created_at;

}
