package com.example.Loan.Origination.System.controller;

import com.example.Loan.Origination.System.dtos.LoanRequest;
import com.example.Loan.Origination.System.entities.Loan;
import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import com.example.Loan.Origination.System.repository.LoanRepository;
import com.example.Loan.Origination.System.service.Impl.LoanServiceImpl;
import com.example.Loan.Origination.System.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/v1")
public class LoanController {
    @Autowired
    private LoanServiceImpl loanService;

    @PostMapping("/loans")
    public ResponseEntity<String> createLoan(@RequestBody @Valid LoanRequest loanRequest) {
        String loanId = loanService.createLoan(loanRequest);
        return ResponseEntity.ok("Loan with ID " + loanId + " is created");
    }
    @GetMapping("/status-count")
    public ResponseEntity<Map<LoanStatus, Long>> getStatusCounts() {
        return ResponseEntity.ok(loanService.getLoanStatusCounts());
    }
    @GetMapping("/loans")
    public ResponseEntity<Page<Loan>> getLoansByStatus(
            @RequestParam LoanStatus status,
            @RequestParam int page,
            @RequestParam int size) {
        Page<Loan> loans = loanService.getLoansByStatus(status, page, size);
        return ResponseEntity.ok(loans);
    }
}
