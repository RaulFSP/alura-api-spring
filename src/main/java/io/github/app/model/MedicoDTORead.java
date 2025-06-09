package io.github.app.model;

import io.github.app.model.enums.Especialidade;

public record MedicoDTORead(String nome,String email,String crm,Especialidade especialidade) {

	public MedicoDTORead(Medico medico) {
		this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
