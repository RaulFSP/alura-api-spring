package io.github.app.model.consulta;

import io.github.app.model.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTODelete(
		@NotNull
		long id,
		@NotNull
		MotivoCancelamento motivoCancelamento
		) {

}
