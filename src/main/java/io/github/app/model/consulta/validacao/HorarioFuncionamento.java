package io.github.app.model.consulta.validacao;

import java.time.DayOfWeek;

import org.hibernate.QueryParameterException;
import org.springframework.stereotype.Component;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.ConsultaDTOCreate;
@Component
public class HorarioFuncionamento implements ValidadorDeAgendamentoDeConsulta{

	public void validar(ConsultaDTOCreate consulta) {
		try {
		var data = consulta.data();
		var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesAbertura = data.getHour() < 7;
		var aposFechamento = data.getHour() > 18;
		if(domingo || antesAbertura || aposFechamento) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
		}
		} catch(QueryParameterException e) {
			throw new RuntimeException(this.getClass().getName(),e);
		}
		
	}
}
