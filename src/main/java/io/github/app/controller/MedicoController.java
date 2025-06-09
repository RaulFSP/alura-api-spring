package io.github.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.app.model.Medico;
import io.github.app.model.MedicoDTOCadastro;
import io.github.app.model.MedicoDTORead;
import io.github.app.model.MedicoDTOUpdate;
import io.github.app.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@GetMapping
	public Page<MedicoDTORead> getMedicos(@PageableDefault(size = 8, sort = { "nome" }) Pageable page) {
		return medicoRepository.findAllByAtivoTrue(page).map(MedicoDTORead::new);
	}

	@PostMapping
	@Transactional
	public Medico postMedicos(@RequestBody @Valid MedicoDTOCadastro medicoDTO) {
		Medico medico = new Medico(medicoDTO);
		return medicoRepository.save(medico);

	}

	@PutMapping
	@Transactional
	public MedicoDTOUpdate putMedico(@RequestBody @Valid MedicoDTOUpdate medicoDTO) {
		Medico medico = medicoRepository.getReferenceById(medicoDTO.id());
		medico.updateInfo(medicoDTO);
		return medicoDTO;
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public void deleteMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.inativar();
	}
}
