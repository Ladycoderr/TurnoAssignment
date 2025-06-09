package com.example.Loan.Origination.System.service;

import com.example.Loan.Origination.System.entities.enums.LoanStatus;

public interface LoanStatusCount {
    LoanStatus getStatus();
    Long getCount();
}