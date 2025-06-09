package io.github.app.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTOUpdate(@NotNull Long id,
		@JsonAlias({"nome,Nome,NOME"})
		String nome, @Pattern(regexp = "\\d{10,11}") String telefone, @Valid EnderecoDTOUpdate endereco) {

}
