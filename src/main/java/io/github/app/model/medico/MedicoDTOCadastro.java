package io.github.app.model.medico;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.github.app.model.endereco.EnderecoDTOCadastro;
import io.github.app.model.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record MedicoDTOCadastro(
		@JsonAlias({"nome","Nome"})
		@NotBlank
		String nome,
		@JsonAlias({"email","Email"})
		@NotBlank
		@Email
		String email,
		@NotBlank
		@Pattern(regexp = "\\d{10,11}")
		String telefone,
		@JsonAlias({"crm","Crm","CRM"})
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		@NotNull
		Especialidade especialidade,
		@NotNull
		@Valid
		EnderecoDTOCadastro endereco
		) {

}
