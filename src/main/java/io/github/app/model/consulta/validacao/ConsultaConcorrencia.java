package io.github.app.model.consulta.validacao;

import org.hibernate.QueryParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.repository.ConsultaRepository;

@Component
public class ConsultaConcorrencia implements ValidadorDeAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(ConsultaDTOCreate consultaCreate) {
		try {
		var m = consultaRepository.existsByMedicoIdAndData(consultaCreate.idMedico(), consultaCreate.data());
		if (m) {
			throw new ValidacaoException("Uma consulta com este médico e horário já existe!");
		}
		} catch(QueryParameterException e) {
			throw new RuntimeException(this.getClass().getName(),e);
		}
	}
}
