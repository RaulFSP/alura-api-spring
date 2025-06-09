package io.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.app.model.Medico;
import io.github.app.model.MedicoDTOCadastro;
import io.github.app.model.MedicoDTORead;
import io.github.app.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@GetMapping
	public List<MedicoDTORead> getMedicos() {
		return medicoRepository.findAll().stream().map(MedicoDTORead::new).toList();
	}
	
	@PostMapping
	@Transactional
	public Medico postMedicos(@RequestBody @Valid MedicoDTOCadastro medicoDTO) {
		Medico medico = new Medico(medicoDTO);
		return medicoRepository.save(medico);
		
	}
}
