package com.example.policy_management_api.controller;

import com.example.policy_management_api.dto.CoreEventRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {

    private static final Logger log =
            LoggerFactory.getLogger(
                    CoreMockController.class
            );

    @PostMapping("/evento")
    public String evento (
            @RequestBody CoreEventRequestDTO dto
    ){
        log.info(
                "Evento enviado al CORE -> evento: {}, polizaId: {}",
                dto.getEvento(),
                dto.getPolizaId()
        );

        return "Evento recibido correctamente";
    }

}
