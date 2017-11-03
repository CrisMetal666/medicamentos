package com.medicamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicamentos.model.EstablecimientoMedicamento;

public interface IEstablecimientoMedicamentoRepository extends JpaRepository<EstablecimientoMedicamento, Integer> {

	/**
	 * obtenermos el id de la entidad
	 * @param idMedicamento id del medicamento
	 * @param idEstablecimiento id del establecimiento
	 * @return id de la entidad
	 */
	@Query("select em.id from EstablecimientoMedicamento em where em.establecimiento.id = :idEsta "
			+ "and em.medicamentos.id = :idMe")
	Integer getIdPorEstablecimientoMedicamento(@Param("idMe")Integer idMedicamento, 
			@Param("idEsta")Integer idEstablecimiento);
}
