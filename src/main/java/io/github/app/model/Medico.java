package io.github.app.model;

import io.github.app.model.enums.Especialidade;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	private boolean ativo;
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	@Embedded
	private Endereco endereco;

	public Medico(MedicoDTOCadastro medico) {
		this.ativo=true;
		this.nome = medico.nome();
		this.email = medico.email();
		this.crm = medico.crm();
		this.telefone=medico.telefone();
		this.especialidade = medico.especialidade();
		this.endereco = new Endereco(medico.endereco());
	}

	public void updateInfo(@Valid MedicoDTOUpdate medicoDTO) {
		if(medicoDTO.nome() != null) {
			this.nome=medicoDTO.nome();			
		}
		if (medicoDTO.telefone() != null) {
			this.telefone=medicoDTO.telefone();			
		}
		if (medicoDTO.endereco() != null) {
			this.endereco.updateInfo(medicoDTO.endereco());
		}
		
	}

    public void inativar() {
        if (this.ativo) this.ativo=false;
    }
}
