package com.example.policy_management_api.repository;

import com.example.policy_management_api.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskRepository extends JpaRepository<Risk, Long> {

    List<Risk> findByPolicyId(Long policyId);

}
