package io.github.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
