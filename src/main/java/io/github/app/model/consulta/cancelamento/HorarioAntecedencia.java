package io.github.app.model.consulta.cancelamento;



import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTODelete;
import io.github.app.repository.ConsultaRepository;

@Component(value = "ValidadorHorarioAntecedenciaAgendamento")
public class HorarioAntecedencia implements CancelarAgendamentoConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Override
	public void validar(ConsultaDTODelete dados) {
		var consulta = consultaRepository.getReferenceById(dados.id());
		var agora = LocalDateTime.now();
		var diferencaHorario = Duration.between(agora, consulta.getData()).toHours();
		
		if(diferencaHorario < 24) {
			throw new ValidacaoException("Consulta não pode ser cancelada caso esteja a 24 horas de ocorrer.");
		}
	}

}
