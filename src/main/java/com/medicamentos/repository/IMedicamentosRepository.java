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
	 * @param mes (yyyy/mm/dd)
	 * @param id del establecimiento
	 * @return
	 */
	@Query(value = "select new com.medicamentos.model.Medicamentos(m.id, m.nombreGenerico) from Medicamentos "
			+ "m inner join m.establecimientoMedicamentos em where em.id not in (select h.establecimientoMedicamento.id " 
			+ "from Historial h where month(h.fecha) = month(:fecha) and year(h.fecha) = year(:fecha)) and "
			+ "em.establecimiento.id = :id")
	List<Medicamentos> listarMedicamentosNoRegistrados(@Param("fecha")String mes, @Param("id")int id);
	
	/**
	 * Se consultan los medicamentos pertenecientes a un establecimiento que han sido registrados en el mes espeficicado
	 * @param mes (yyyy/mm/dd)
	 * @param id del establecimiento
	 * @return
	 */
	@Query(value = "select new com.medicamentos.model.Medicamentos(m.id, m.nombreGenerico) from Medicamentos "
			+ "m inner join m.establecimientoMedicamentos em where em.id in (select h.establecimientoMedicamento.id " 
			+ "from Historial h where month(h.fecha) = month(:fecha) and year(h.fecha) = year(:fecha)) and "
			+ "em.establecimiento.id = :id")
	List<Medicamentos> listarMedicamentosRegistrados(@Param("fecha")String mes, @Param("id")int id);
	
	/**
	 * Se consultaran los medicamentos que se encuentran registrados en el mes especificado con su respectivo saldo
	 * @param mes (yyyy/mm/dd)
	 * @param id del establecimiento
	 * @return lista de medicamentos con saldo
	 */
	@Query("select new com.medicamentos.model.Medicamentos(m.id, m.nombreGenerico, h.saldo) from Medicamentos m inner join "
			+ "m.establecimientoMedicamentos em inner join em.historials h "  
			+ "where em.establecimiento.id = :id and month(h.fecha) = month(:fecha) and year(h.fecha) = year(:fecha)")
	List<Medicamentos> listarMedicamentosConSaldo(@Param("fecha")String mes, @Param("id")int id);
	
	/**
	 * Se consultaran los medicamentos que se encuentran registrados en el mes actual con su respectivo saldo
	 * @param mes (yyyy/mm/dd)
	 * @param id del establecimiento
	 * @return lista de medicamentos con saldo
	 */
	@Query("select new com.medicamentos.model.Medicamentos(m.id, m.nombreGenerico, h.saldo) from Medicamentos m inner join "
			+ "m.establecimientoMedicamentos em inner join em.historials h "  
			+ "where em.establecimiento.id = :id and month(h.fecha) = month(now()) and year(h.fecha) = year(now())")
	List<Medicamentos> listarMedicamentosConSaldo(@Param("id")int id);
	
	/**
	 * se consultan los todos los medicamentos que han registrado su historial en le mes actual
	 * @return
	 */
	@Query("select new com.medicamentos.model.Medicamentos(m.id, m.nombreGenerico) from Medicamentos m inner join "
			+ "m.establecimientoMedicamentos em inner join em.historials h "  
			+ "where month(h.fecha) = month(now()) and year(h.fecha) = year(now())")
	List<Medicamentos> listarMedicamentos();
	
}













