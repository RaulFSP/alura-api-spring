package io.github.app.model.medico;

import io.github.app.model.endereco.Endereco;
import io.github.app.model.enums.Especialidade;

public record MedicoDTORead(Long id,String nome,String email,String telefone,String crm,Especialidade especialidade, Endereco endereco) {

	public MedicoDTORead(Medico medico) {
		this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getTelefone(),medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
	}
}
