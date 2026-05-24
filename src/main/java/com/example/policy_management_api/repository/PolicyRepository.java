package com.example.policy_management_api.repository;

import com.example.policy_management_api.entity.Policy;
import com.example.policy_management_api.enums.PolicyStatus;
import com.example.policy_management_api.enums.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByTypeAndStatus(PolicyType type, PolicyStatus status);

}
