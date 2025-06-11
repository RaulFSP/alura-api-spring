package io.github.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.model.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
