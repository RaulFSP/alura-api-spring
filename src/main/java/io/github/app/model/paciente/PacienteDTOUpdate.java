package io.github.app.model.paciente;

import io.github.app.model.endereco.EnderecoDTOUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteDTOUpdate(
		@NotNull
		Long id,
		String nome,
		@Pattern(regexp = "\\d{10,11}") String telefone,
		@Valid EnderecoDTOUpdate endereco
		) {

}
