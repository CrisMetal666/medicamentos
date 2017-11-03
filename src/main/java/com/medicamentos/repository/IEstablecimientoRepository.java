package com.medicamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicamentos.model.Establecimiento;

@Repository
public interface IEstablecimientoRepository extends JpaRepository<Establecimiento, Integer> {

	/**
	 * Obtenemos el id del establecimiento
	 * 
	 * @param id del usuario
	 * 
	 * @return id del establecimiento
	 */
	@Query("select e.id from Establecimiento e where e.usuario.id = :id")
	Integer getIdPorUsuarioId(@Param("id") Integer id);
	
	/**
	 * Obtenemos el id del establecimiento
	 * 
	 * @param identificacion del usuario
	 * 
	 * @return id del establecimiento
	 */
	@Query("select e.id from Establecimiento e where e.usuario.identificacion = :ident")
	Integer getIdPorUsuarioIdentificacion(@Param("ident") String identificacion);
}
