/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author laptop
 */

import io.github.app.model.Paciente;
import io.github.app.model.PacienteDTOCreate;
import io.github.app.model.PacienteDTORead;
import io.github.app.model.PacienteDTOUpdate;
import io.github.app.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;

	@GetMapping()
	public Page<PacienteDTORead> getPaciente(@PageableDefault(size = 8) Pageable page) {
		return pacienteRepository.findAll(page).map(PacienteDTORead::new);
	}

	@PostMapping
	@Transactional
	public PacienteDTORead postPaciente(@RequestBody @Valid PacienteDTOCreate pacienteDTO) {
		Paciente paciente = new Paciente(pacienteDTO);
		PacienteDTORead pacienteRead = new PacienteDTORead(pacienteRepository.save(paciente));
		return pacienteRead;
	}

	@PostMapping("/list")
	@Transactional
	public List<PacienteDTORead> postPacienteList(@RequestBody @Valid List<PacienteDTOCreate> listPacienteCreate) {
		List<PacienteDTORead> listPacienteread = listPacienteCreate.stream()
				.map(m -> new PacienteDTORead(pacienteRepository.save(new Paciente(m)))).toList();
		return listPacienteread;
	}
	
	@PutMapping
	@Transactional
	public PacienteDTOUpdate putPaciente(@RequestBody @Valid PacienteDTOUpdate pacienteUpdate) {
		Paciente paciente = pacienteRepository.getReferenceById(pacienteUpdate.id());
		paciente.updateInfo(pacienteUpdate);
		return pacienteUpdate;
	}
}
