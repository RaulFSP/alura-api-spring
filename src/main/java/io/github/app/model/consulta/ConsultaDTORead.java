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
public record ConsultaDTORead(Long id, String medico, String paciente, LocalDateTime data) {
	public ConsultaDTORead(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getNome(), consulta.getPaciente().getNome(), consulta.getData());
	}
}
