package com.medicamentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicamentos.model.Medicamentos;

@Repository
public interface IMedicamentosRepository extends JpaRepository<Medicamentos, Integer> {

	/**
	 * Se consultaran los medicamentos que tenga un establecimiento
	 * 
	 * @return lista de medicamentos con solamente los datos minimos (id, nombreGenerico)
	 */
	@Query("select new com.medicamentos.model.Medicamentos(m.id, m.nombreGenerico) from Medicamentos m "
			+ "inner join m.establecimientoMedicamentos em where em.establecimiento.id = :idEstablecimiento")
	List<Medicamentos> listarMedicamentos(@Param("idEstablecimiento") Integer id);
	
	/**
	 * Se consultan los medicamentos pertenecientes a un establecimiento que no han sido registrados en el mes especificado
	 * @param mes. Mes actual (1-12)
	 * @param id del establecimiento
	 * @return
	 */
	@Query(value = "select m.* from medicamentos as m inner join establecimiento_medicamento as em " + 
			"on  m.id = em.idmedicamento WHERE em.id not IN (SELECT historial.idestablecimiento_medicamento " + 
			"FROM medicamentos.historial WHERE MONTH(fecha) = ?) and em.idestablecimiento = ?", nativeQuery = true)
	List<Medicamentos> listarMedicamentosNoRegistrados(int mes, int id);
	
	/**
	 * Se consultan los medicamentos pertenecientes a un establecimiento que han sido registrados en el mes espeficicado
	 * @param mes. Mes actual (1-12)
	 * @param id del establecimiento
	 * @return
	 */
	@Query(value = "select m.* from medicamentos as m inner join establecimiento_medicamento as em " + 
			"on  m.id = em.idmedicamento WHERE em.id IN (SELECT historial.idestablecimiento_medicamento " + 
			"FROM medicamentos.historial WHERE MONTH(fecha) = ?) and em.idestablecimiento = ?", nativeQuery = true)
	List<Medicamentos> listarMedicamentosRegistrados(int mes, int id);
}
