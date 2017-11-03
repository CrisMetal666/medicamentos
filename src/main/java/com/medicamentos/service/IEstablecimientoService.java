package com.medicamentos.service;

import com.medicamentos.model.Establecimiento;

public interface IEstablecimientoService extends IService<Establecimiento> {

	Integer getIdPorUsuarioId(Integer id);
	Integer getIdPorUsuarioIdentificacion(String identificacion);
}
