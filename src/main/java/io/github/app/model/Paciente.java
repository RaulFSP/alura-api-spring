package io.github.app.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	@Embedded
	private Endereco endereco;

	public Paciente(PacienteDTOCreate cadastro) {
		this.nome = cadastro.nome();
		this.email = cadastro.email();
		this.cpf = cadastro.cpf();
		this.telefone = cadastro.telefone();
		this.endereco = new Endereco(cadastro.endereco());
	}

	public void updateInfo(PacienteDTOUpdate pacienteUpdate) {
		if(pacienteUpdate.nome() != null && !pacienteUpdate.nome().isBlank()) {
			this.nome=pacienteUpdate.nome();
		}
		if(pacienteUpdate.telefone() != null) {
			this.telefone = pacienteUpdate.telefone();
		}
		if(pacienteUpdate.endereco() != null) {
			this.endereco.updateInfo(pacienteUpdate.endereco());
		}
	}
}
