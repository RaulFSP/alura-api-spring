/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package io.github.app.model.consulta;

import java.time.LocalDateTime;

/**
 *
 * @author laptop
 */
public record ConsultaDTORead(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
        ) {
public ConsultaDTORead(Consulta consulta){
        this(consulta.getId(),consulta.getIdMedico(),consulta.getIdPaciente(),consulta.getData());
}
}
