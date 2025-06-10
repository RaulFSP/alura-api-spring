package io.github.app.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteDTOCreate(
		
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotBlank
		@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
		String cpf,
		@NotBlank
		String telefone,
		@NotNull
		@Valid
		EnderecoDTOCadastro endereco
		
		) {

}
