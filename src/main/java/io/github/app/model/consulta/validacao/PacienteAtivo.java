package io.github.app.model.consulta.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.repository.PacienteRepository;

@Component
public class PacienteAtivo implements ValidadorDeAgendamentoDeConsulta {

	@Autowired
	private PacienteRepository pacienteRepository;

	public void validar(ConsultaDTOCreate consulta) {

		if (consulta.idPaciente() == null) {
			return;
		}
		var paciente = pacienteRepository.findAtivoById(consulta.idPaciente());
		if (!paciente) {
			throw new ValidacaoException("Paciente inativado");
		}

	}
}
