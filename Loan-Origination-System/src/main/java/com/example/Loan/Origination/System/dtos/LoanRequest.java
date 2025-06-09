package com.example.Loan.Origination.System.dtos;
import com.example.Loan.Origination.System.entities.enums.LoanType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {
    @NonNull
    private String customerName;
    @NonNull
    private String customerPhone;
    @NonNull
    private Double loanAmount;
    @NonNull
    private LoanType loanType;
}
