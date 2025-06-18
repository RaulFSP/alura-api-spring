package io.github.app.repository;




import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import io.github.app.model.consulta.Consulta;
import io.github.app.model.endereco.EnderecoDTOCadastro;
import io.github.app.model.enums.Especialidade;
import io.github.app.model.medico.Medico;
import io.github.app.model.medico.MedicoDTOCadastro;
import io.github.app.model.paciente.Paciente;
import io.github.app.model.paciente.PacienteDTOCreate;
import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager em; 
	
	@Test
	@DisplayName("Deveria devolver nulo caso nenhum medico esteja disponível na data")
	void testEscolherMedicoLivrePorData() {
		var proximaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
		var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
		var m = medicoRepository.escolherMedicoLivrePorData(Especialidade.CARDIOLOGIA, proximaAs10);
		assertThat(m).isNull();
	}
	
	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
	    em.persist(new Consulta(null, medico, paciente, data, null));
	}

	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
	    var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
	    em.persist(medico);
	    return medico;
	}

	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
	    var paciente = new Paciente(dadosPaciente(nome, email, cpf));
	    em.persist(paciente);
	    return paciente;
	}

	private MedicoDTOCadastro dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
	    return new MedicoDTOCadastro(
	            nome,
	            email,
	            "61999999999",
	            crm,
	            especialidade,
	            dadosEndereco()
	    );
	}

	private PacienteDTOCreate dadosPaciente(String nome, String email, String cpf) {
	    return new PacienteDTOCreate(
	            nome,
	            email,
	            "61999999999",
	            cpf,
	            dadosEndereco()
	    );
	}

	private EnderecoDTOCadastro dadosEndereco() {
	    return new EnderecoDTOCadastro(
	            "rua xpto",
	            "bairro",
	            "00000000",
	            "Brasilia",
	            "DF",
	            null,
	            null
	    );
	}

}
