/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.app.exception.ValidacaoException;
import io.github.app.model.consulta.Consulta;
import io.github.app.model.consulta.ConsultaDTOCreate;
import io.github.app.model.consulta.ConsultaDTODelete;
import io.github.app.model.consulta.ConsultaDTORead;
import io.github.app.model.consulta.cancelamento.CancelarAgendamentoConsulta;
import io.github.app.model.medico.Medico;
import io.github.app.repository.ConsultaRepository;
import io.github.app.repository.MedicoRepository;
import io.github.app.repository.PacienteRepository;
import io.github.app.model.consulta.validacao.ValidadorDeAgendamentoDeConsulta;
import jakarta.validation.Valid;

/**
 *
 * @author laptop
 */
@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienterepository;
	
	@Autowired
	private List<ValidadorDeAgendamentoDeConsulta> validadores;
	
	@Autowired
	private List<CancelarAgendamentoConsulta> validadoresCancelamento;

	public ConsultaDTORead agendar(ConsultaDTOCreate consultaCreate) {
		
		if (consultaCreate.idMedico() != null && !medicoRepository.existsById(consultaCreate.idMedico())) {
			throw new ValidacaoException("Id do médico não existe");
		}
		if (!pacienterepository.existsById(consultaCreate.idPaciente())) {
			throw new ValidacaoException("Id do paciente não existe");
		}
		
		validadores.forEach(v->v.validar(consultaCreate));
		
		var medico = escolheMedico(consultaCreate);
		var paciente = pacienterepository.getReferenceById(consultaCreate.idPaciente());
		var consulta = new Consulta(null, medico, paciente, consultaCreate.data(), null);
		var c = consultaRepository.save(consulta);
		
		return new ConsultaDTORead(consultaRepository.findByIdComEntidades(c.getId()));
	}
	
	private Medico escolheMedico(ConsultaDTOCreate consultaCreate) {
		if(consultaCreate.idMedico() != null) {
			return medicoRepository.getReferenceById(consultaCreate.idMedico());
		}
		if(consultaCreate.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatório caso o médico não tenha sido escolhido!");
		}
		return medicoRepository.escolherMedicoLivrePorData(consultaCreate.idMedico(),consultaCreate.data());
	}

	public void cancelar(@Valid ConsultaDTODelete consultaDeletar) {
		if(!consultaRepository.existsById(consultaDeletar.id())) {
			throw new ValidacaoException("Consulta não encontrada!");
		}
		validadoresCancelamento.forEach(f->f.validar(consultaDeletar));
		var consulta = consultaRepository.getReferenceById(consultaDeletar.id());
		consulta.cancelar(consultaDeletar.motivoCancelamento());
	}
}
