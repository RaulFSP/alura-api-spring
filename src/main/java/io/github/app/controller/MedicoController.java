package io.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.app.model.medico.Medico;
import io.github.app.model.medico.MedicoDTOCadastro;
import io.github.app.model.medico.MedicoDTORead;
import io.github.app.model.medico.MedicoDTOUpdate;
import io.github.app.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@GetMapping
	public ResponseEntity<Page<MedicoDTORead>> getMedicos(@PageableDefault(size = 8, sort = { "nome" }) Pageable page) {
		Page<MedicoDTORead> p = medicoRepository.findAllByAtivoTrue(page).map(MedicoDTORead::new);
		return ResponseEntity.ok(p);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MedicoDTORead> getMedicosById(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		return ResponseEntity.ok(new MedicoDTORead(medico));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<MedicoDTORead> postMedicos(@RequestBody @Valid MedicoDTOCadastro medicoDTO,
			UriComponentsBuilder uriBuilder) {
		Medico medico = new Medico(medicoDTO);
		MedicoDTORead medicoDTORead = new MedicoDTORead(medicoRepository.save(medico));
		var uri = uriBuilder.path("/medicos/{id]").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(medicoDTORead);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<MedicoDTORead> putMedico(@RequestBody @Valid MedicoDTOUpdate medicoDTO) {
		Medico medico = medicoRepository.getReferenceById(medicoDTO.id());
		medico.updateInfo(medicoDTO);
		return ResponseEntity.ok(new MedicoDTORead(medico));
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<String> deleteMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.inativar();
		return ResponseEntity.noContent().build();
	}
}
