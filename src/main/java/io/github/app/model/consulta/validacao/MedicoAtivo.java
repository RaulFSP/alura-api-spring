package io.github.app.model.consulta.validacao;

import org.hibernate.QueryParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.repository.MedicoRepository;

@Component
public class MedicoAtivo implements ValidadorDeAgendamentoDeConsulta {

	@Autowired
	private MedicoRepository medicoRepository;

	public void validar(ConsultaDTOCreate consulta) {
		try {
			if (consulta.idMedico() == null) {
				return;
			}
			var medico = medicoRepository.findAtivoById(consulta.idMedico());
			if (!medico) {
				throw new ValidacaoException("Médico inativado");
			}
		} catch (QueryParameterException e) {
			throw new RuntimeException(this.getClass().getName(), e);
		}
	}
}
