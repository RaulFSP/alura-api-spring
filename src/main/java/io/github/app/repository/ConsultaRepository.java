/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package io.github.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.model.consulta.Consulta;
/**
 *
 * @author laptop
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

}
