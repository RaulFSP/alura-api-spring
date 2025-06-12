package io.github.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.app.model.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query("""
			select p.ativo from Paciente p where p.id = :id
			""")
	Boolean findAtivoById(Long id);
	
	
}
