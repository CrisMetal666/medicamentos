package com.medicamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicamentos.model.Historial;

@Repository
public interface IHistorialRepository extends JpaRepository<Historial, Integer> {

	/**
	 * Se obtendra el saldo anterior de un medicamento
	 * @param idMedicamento id del medicamento 
	 * @param idEstablecimiento id del establecimiento
	 * @param mes anterior numerico (1 - 12)
	 * @return saldo anterior
	 */
	@Query("select h.saldo from Historial h inner join h.establecimientoMedicamento em " + 
			"where em.medicamentos.id = :idMedicamento and MONTH(fecha) = :mes and em.establecimiento.id = :idEstablecimiento")
	Integer getSaldoAnterior(@Param("idMedicamento")int idMedicamento, @Param("idEstablecimiento")int idEstablecimiento, 
			@Param("mes")int mes) throws Exception;
	
	/**
	 * se obtentra de la tabla historial el ingreso, lab_farma, salida, rad_formulas
	 * 
	 * @param idMedi id del medicamento
	 * @param idEsta id del establecimiento
	 * @param mes, mes en valor numerico (1 - 12)
	 * @return
	 */
	@Query("select new com.medicamentos.model.Historial(h.id, h.ingreso, h.labFarma, h.salida, h.radFormulas) "
			+ "from Historial h inner join h.establecimientoMedicamento em where em.medicamentos.id = :idMedi "
			+ "and em.establecimiento.id = :idEsta and month(h.fecha) = :mes")
	Historial getHistorialPorIdMedicamentoIdEstablecimientoMes(@Param("idMedi")int idMedi, 
			@Param("idEsta")int idEsta, @Param("mes")int mes);
}
