package io.github.app.model.paciente;

public record PacienteDTORead(
		Long id,
		String nome, 
		String email, 
		String cpf, 
		String telefone
		) {
	public PacienteDTORead(Paciente paciente) {
		this(paciente.getId(),paciente.getNome(),paciente.getEmail(),paciente.getCpf(),paciente.getTelefone());
	}
}
