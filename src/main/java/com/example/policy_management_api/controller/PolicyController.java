package com.example.policy_management_api.controller;

import com.example.policy_management_api.dto.RiskRequestDTO;
import com.example.policy_management_api.entity.Policy;
import com.example.policy_management_api.entity.Risk;
import com.example.policy_management_api.enums.PolicyStatus;
import com.example.policy_management_api.enums.PolicyType;
import com.example.policy_management_api.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    //GET /polizas
    @GetMapping
    public List<Policy> getPolicies(
            @RequestParam PolicyType type,
            @RequestParam PolicyStatus status
    ){
        return policyService.getPolicies(
                type,
                status
        );
    }

    //GET /polizas/{id}/riesgos
    @GetMapping("/{id}/riesgos")
    public List<Risk> getRiks(
            @PathVariable Long id
    ){
        return policyService.getRisks(id);
    }

    //POST /polizas/{id}/renovar
    @PostMapping("/{id}/renovar")
    public Policy renewPolicy(
            @PathVariable Long id,
            @RequestParam BigDecimal ipc
    ){
        return policyService.renewPolicy(id, ipc);
    }

    //POST /polizas/{id}/cancelar
    @PostMapping("/{id}/cancelar")
    public Policy cancelPolicy(
            @PathVariable Long id
    ){
        return policyService.cancelPolicy(id);
    }

    //POST /polizas/{id}/riesgos
    @PostMapping("/{id}/riesgos")
    public Risk addRisk(
            @PathVariable Long id,
            @RequestBody RiskRequestDTO dto
    ){
        return policyService.addRisk(id, dto);
    }

}
