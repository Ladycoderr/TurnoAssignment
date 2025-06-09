package com.example.Loan.Origination.System.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "agent",
        indexes = {
                @Index(name = "idx_agent_phone", columnList = "phone")
        })
public class Agent {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "agent_id", updatable = false, nullable = false)
    private String agentId;

    private String managerId;

    private String phone;
}