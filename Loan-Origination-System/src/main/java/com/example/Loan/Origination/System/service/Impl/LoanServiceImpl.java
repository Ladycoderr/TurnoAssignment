package com.example.Loan.Origination.System.service.Impl;

import com.example.Loan.Origination.System.dtos.LoanRequest;
import com.example.Loan.Origination.System.entities.Loan;
import com.example.Loan.Origination.System.entities.enums.LoanStatus;
import com.example.Loan.Origination.System.repository.LoanRepository;
import com.example.Loan.Origination.System.service.LoanService;
import com.example.Loan.Origination.System.service.LoanStatusCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

        public String createLoan(LoanRequest loanRequest){
            try {
                Loan loan = Loan.builder()
                        .loanAmount(loanRequest.getLoanAmount())
                        .loanType(loanRequest.getLoanType())
                        .applicationStatus(LoanStatus.APPLIED)
                        .customerName(loanRequest.getCustomerName())
                        .customerPhone(loanRequest.getCustomerPhone())
                        .created_at(LocalDateTime.now())
                        .build();
                Loan savedLoan = loanRepository.save(loan);
                return savedLoan.getLoanId();
            }catch (Exception e){
                log.error("Some error has occurred while creating loan for customer {}" , loanRequest.getCustomerName(),e);
                throw new RuntimeException("Some error has occurred while creating loan",e);
            }
        }
    public Map<LoanStatus, Long> getLoanStatusCounts() {
        List<LoanStatusCount> statusCounts = loanRepository.countLoansByStatus();
        return statusCounts.stream()
                .collect(Collectors.toMap(LoanStatusCount::getStatus, LoanStatusCount::getCount));
    }

    public Page<Loan> getLoansByStatus(LoanStatus status, int page, int size) {
        if (page < 0 || size <= 0) {
            return Page.empty();
        }
        Pageable pageable = PageRequest.of(page, size);
        return loanRepository.findByApplicationStatus(status,pageable);
    }

}
