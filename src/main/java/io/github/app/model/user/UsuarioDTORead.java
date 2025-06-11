package io.github.app.model.user;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTORead(
		@NotBlank
		String login,
		@NotBlank
		String senha) {

}
