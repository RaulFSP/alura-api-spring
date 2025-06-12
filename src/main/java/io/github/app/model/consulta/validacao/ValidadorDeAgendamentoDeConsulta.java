package io.github.app.model.consulta.validacao;

import io.github.app.model.consulta.ConsultaDTOCreate;

public interface ValidadorDeAgendamentoDeConsulta {

	void validar(ConsultaDTOCreate consultaDTOCreate);
}
