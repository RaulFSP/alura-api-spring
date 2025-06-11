/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.app.model.consulta.Consulta;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.repository.ConsultaRepository;
import io.github.app.repository.MedicoRepository;
import io.github.app.repository.PacienteRepository;

/**
 *
 * @author laptop
 */
@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienterepository;

    public void agendar(ConsultaDTOCreate consultaCreate){
        var medico = medicoRepository.findById(consultaCreate.idMedico()).get();
        var paciente = pacienterepository.findById(consultaCreate.idPaciente()).get();
        var consulta = new Consulta(null,medico,paciente,consultaCreate.data());
        consultaRepository.save(consulta);
    }
}
