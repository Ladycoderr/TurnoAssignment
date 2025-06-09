package com.example.Loan.Origination.System.service;

import com.example.Loan.Origination.System.dtos.LoanRequest;
import org.springframework.stereotype.Service;


public interface LoanService {
    String createLoan(LoanRequest loanRequest);
}
