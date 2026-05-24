package com.example.policy_management_api.dto;

public class CoreEventRequestDTO {

    private String evento;
    private Long polizaId;

    public CoreEventRequestDTO() {
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Long getPolizaId() {
        return polizaId;
    }

    public void setPolizaId(Long polizaId) {
        this.polizaId = polizaId;
    }
}
