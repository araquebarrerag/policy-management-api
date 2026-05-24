package com.example.policy_management_api.entity;

import com.example.policy_management_api.enums.PolicyStatus;
import com.example.policy_management_api.enums.PolicyType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long policyNumber;

    @Enumerated(EnumType.STRING)
    private PolicyType type;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal monthlyRentValue;

    private BigDecimal premiumValue;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    private Boolean renewalEnabled;

    private LocalDateTime createdAt;

    //Relacion con riesgos
    @OneToMany(mappedBy = "policy",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Risk> risks = new ArrayList<>();

    //Constructores

    public Policy() {
    }

    public Policy(Long id,
                  Long policyNumber,
                  PolicyType type,
                  LocalDate startDate,
                  LocalDate endDate,
                  BigDecimal monthlyRentValue,
                  BigDecimal premiumValue,
                  PolicyStatus status,
                  Boolean renewalEnabled,
                  LocalDateTime createdAt) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRentValue = monthlyRentValue;
        this.premiumValue = premiumValue;
        this.status = status;
        this.renewalEnabled = renewalEnabled;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    //Helpers

    public void addRisk(Risk risk){
        this.risks.add(risk);
        risk.setPolicy(this);
    }

    public void removeRisk(Risk risk){
        this.risks.remove(risk);
        risk.setPolicy(null);
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public PolicyType getType() {
        return type;
    }

    public void setType(PolicyType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMonthlyRentValue() {
        return monthlyRentValue;
    }

    public void setMonthlyRentValue(BigDecimal monthlyRentValue) {
        this.monthlyRentValue = monthlyRentValue;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public Boolean getRenewalEnabled() {
        return renewalEnabled;
    }

    public void setRenewalEnabled(Boolean renewalEnabled) {
        this.renewalEnabled = renewalEnabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public void setRisks(List<Risk> risks) {
        this.risks = risks;
    }
}
