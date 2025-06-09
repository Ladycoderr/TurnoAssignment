package com.example.Loan.Origination.System.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopCustomerDto {
    private String customerName;
    private Long approvedLoanCount;
}