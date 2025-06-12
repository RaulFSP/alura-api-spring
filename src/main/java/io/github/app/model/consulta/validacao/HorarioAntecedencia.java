package io.github.app.model.consulta.validacao;

import java.time.Duration;
import java.time.LocalDateTime;

import org.hibernate.QueryParameterException;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTOCreate;

@Component
public class HorarioAntecedencia implements ValidadorDeAgendamentoDeConsulta {
	public void validar(ConsultaDTOCreate consulta) {
		try {
			var dataConsulta = consulta.data();
			var agora = LocalDateTime.now();
			var diffEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
			if (diffEmMinutos < 30) {
				throw new ValidacaoException("A consulta não pode ser agendada antes de 30 minutos");
			}
		} catch (QueryParameterException e) {
			throw new RuntimeException(this.getClass().getName(), e);
		}
	}
}
