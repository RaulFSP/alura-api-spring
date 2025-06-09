package io.github.app.model;

import jakarta.validation.constraints.Pattern;

public record EnderecoDTOUpdate(
		
		 String logradouro,
		 String bairro,
		 @Pattern(regexp = "\\d{8}") String cep,
		 String cidade,
		 String uf,
		 String numero,
		String complemento
		) {

}
