package com.example.Loan.Origination.System.controller;

import com.example.Loan.Origination.System.dtos.TopCustomerDto;
import com.example.Loan.Origination.System.service.Impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/top")
    public ResponseEntity<List<TopCustomerDto>> getTopCustomers() {
        return ResponseEntity.ok(customerService.getTopCustomers(3));
    }
}
