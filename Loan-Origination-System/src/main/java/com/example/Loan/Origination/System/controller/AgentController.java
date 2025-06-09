package com.example.Loan.Origination.System.controller;

import com.example.Loan.Origination.System.dtos.AgentRequest;
import com.example.Loan.Origination.System.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;
    @PutMapping("/{agentId}/loans/{loanId}/decision")
    public ResponseEntity<?> reviewLoan(
        @PathVariable String agentId,
        @PathVariable String loanId,
        @RequestBody Map<String, String> body
    ) {
        agentService.handleAgentDecision(agentId, loanId, body.get("decision"));
        return ResponseEntity.ok("Decision recorded");
    }
    @PostMapping
    public ResponseEntity<?> createAgent(@RequestBody AgentRequest agentRequest) {
        String agentID = agentService.createAgent(agentRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Agent created successfully");
        response.put("agentId", agentID);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
