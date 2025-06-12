package io.github.app.model.consulta.validacao;

import org.hibernate.QueryParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.repository.ConsultaRepository;

@Component
public class PacienteConsultaUnica implements ValidadorDeAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(ConsultaDTOCreate consultaCreate) {
		try {
			var primeiroHorario = consultaCreate.data().withHour(7);
			var ultimoHorario = consultaCreate.data().withHour(18);
			var consulta = consultaRepository.existsByPacienteIdAndDataBetween(consultaCreate.idPaciente(),
					primeiroHorario, ultimoHorario);
			if (consulta) {
				throw new ValidacaoException("O paciente já possui uma consulta neste dia.");
			}
		} catch (QueryParameterException e) {
			throw new RuntimeException(this.getClass().getName(), e);
		}
	}
}
