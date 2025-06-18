/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.model.consulta.ConsultaDTODelete;
import io.github.app.model.consulta.ConsultaDTORead;
import io.github.app.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


/**
 *
 * @author laptop
 */
@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    

    @Autowired
    private ConsultaService consultaService;

    
    
    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaDTORead> postConsulta(@RequestBody @Valid ConsultaDTOCreate consultaCreate) {
    		var dto = consultaService.agendar(consultaCreate);
        return ResponseEntity.ok(dto);
        
        
    }
    
    @DeleteMapping
    @Transactional
    public ResponseEntity<String> deleteConsulta(@RequestBody @Valid ConsultaDTODelete consultaDeletar){
    		consultaService.cancelar(consultaDeletar);
    		return ResponseEntity.noContent().build();
    }
    
}
