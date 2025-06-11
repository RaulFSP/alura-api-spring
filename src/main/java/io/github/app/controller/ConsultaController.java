/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.app.model.consulta.Consulta;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.model.consulta.ConsultaDTORead;
import io.github.app.repository.ConsultaRepository;
import io.github.app.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


/**
 *
 * @author laptop
 */
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaDTORead> postConsulta(@RequestBody @Valid ConsultaDTOCreate consultaCreate) {

        
        
        
    }
    
}
