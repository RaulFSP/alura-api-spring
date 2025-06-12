/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package io.github.app.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.app.model.consulta.Consulta;
/**
 *
 * @author laptop
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	Boolean existsByMedicoIdAndData(Long id, LocalDateTime data);
	Boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime dataPrimeiro,LocalDateTime dataUltimo);
	
	@Query("""
			select c from Consulta c
			join fetch c.medico 
			join fetch c.paciente
			where c.id = :id
			""")
	Consulta findByIdComEntidades(Long id);
}
