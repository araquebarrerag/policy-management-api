package com.example.policy_management_api.controller;

import com.example.policy_management_api.entity.Risk;
import com.example.policy_management_api.service.PolicyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riesgos")
public class RiskController {

    private final PolicyService policyService;

    public RiskController(PolicyService policyService) {
        this.policyService = policyService;
    }

    //POST /riesgos/{id}/cancelar
    @PostMapping("/{id}/cancelar")
    public Risk cancelRisk(
            @PathVariable Long id
    ){
        return policyService.cancelRisk(id);
    }
}
