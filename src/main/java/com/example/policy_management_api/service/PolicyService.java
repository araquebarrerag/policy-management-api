package com.example.policy_management_api.service;

import com.example.policy_management_api.dto.RiskRequestDTO;
import com.example.policy_management_api.entity.Policy;
import com.example.policy_management_api.entity.Risk;
import com.example.policy_management_api.enums.PolicyStatus;
import com.example.policy_management_api.enums.PolicyType;
import com.example.policy_management_api.enums.RiskStatus;
import com.example.policy_management_api.exception.BusinessException;
import com.example.policy_management_api.repository.PolicyRepository;
import com.example.policy_management_api.repository.RiskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final RiskRepository riskRepository;

    public PolicyService(PolicyRepository policyRepository, RiskRepository riskRepository) {
        this.policyRepository = policyRepository;
        this.riskRepository = riskRepository;
    }

    // GET /polizas
    public List<Policy> getPolicies(
            PolicyType type,
            PolicyStatus status
    ){
        return policyRepository.findByTypeAndStatus(
                type, status
        );
    }

    // GET /polizas/{id}/riesgos
    public List<Risk> getRisks(Long policyId){
        return riskRepository.findByPolicyId(policyId);
    }

    // POST /polizas/{id}/renovar
    public Policy renewPolicy(
            Long policyId,
            BigDecimal ipc
    ){

        Policy policy = getPolicy(policyId);

        if(policy.getStatus() == PolicyStatus.CANCELLED){
            throw new BusinessException(
                    "No se puede renovar una póliza cancelada"
            );
        }

        BigDecimal factor = BigDecimal.ONE.add(ipc);

        BigDecimal newRent = policy.getMonthlyRentValue().multiply(factor);
        BigDecimal newPremium = policy.getPremiumValue().multiply(factor);

        policy.setMonthlyRentValue(newRent);
        policy.setPremiumValue(newPremium);
        policy.setStatus(PolicyStatus.RENEWED);

        return policyRepository.save(policy);
    }

    // POST /polizas/{id}/cancelar
    public Policy cancelPolicy(Long policyId){

        Policy policy = getPolicy(policyId);

        policy.setStatus(PolicyStatus.CANCELLED);

        policy.getRisks()
                .forEach(risk ->
                    risk.setStatus(RiskStatus.CANCELLED)
                );

        return policyRepository.save(policy);
    }

    // POST /polizas/{id}/riesgos
    public Risk addRisk(
            Long policyId,
            RiskRequestDTO dto
    ){

        Policy policy = getPolicy(policyId);

        if(policy.getType() != PolicyType.COLLECTIVE){
            throw new BusinessException(
                    "Solo pólizas colectivas permiten agregar riesgos"
            );
        }

        if(policy.getStatus() == PolicyStatus.CANCELLED){
            throw new BusinessException(
                    "No se pueden crear riesgos sobre una póliza cancelada"
            );
        }

        Risk risk = new Risk();

        risk.setTenantName(dto.getTenantName());
        risk.setLandlordName(dto.getLandlordName());
        risk.setPropertyAddress(dto.getPropertyAddress());
        risk.setInsuredValue(dto.getInsuredValue());
        risk.setStatus(RiskStatus.ACTIVE);

        policy.addRisk(risk);

        policyRepository.save(policy);

        return risk;

    }

    //POST /riesgos/{id}/cancelar
    public Risk cancelRisk(Long riskId){

        Risk risk = riskRepository.findById(riskId)
                .orElseThrow(() ->
                    new BusinessException(
                            "Riesgo no encontrado"
                    )
                );

        risk.setStatus(RiskStatus.CANCELLED);

        return riskRepository.save(risk);

    }


    private Policy getPolicy(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(
                                "Póliza no encontrada"
                        )
                );
    }

}
