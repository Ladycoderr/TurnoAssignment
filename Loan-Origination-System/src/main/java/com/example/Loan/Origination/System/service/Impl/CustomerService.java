package com.example.Loan.Origination.System.service.Impl;

import com.example.Loan.Origination.System.dtos.TopCustomerDto;
import com.example.Loan.Origination.System.repository.LoanRepository;
import com.example.Loan.Origination.System.service.strategy.TopCustomerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private LoanRepository loanRepository;

    public List<TopCustomerDto> getTopCustomers(int limit) {
        List<TopCustomerProjection> projections = loanRepository.findTopApprovedCustomers(limit);
        return projections.stream()
                .map(p -> new TopCustomerDto(p.getCustomerName(), p.getApprovedCount()))
                .collect(Collectors.toList());
    }

}
