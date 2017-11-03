package com.medicamentos.service;

import com.medicamentos.model.Historial;

public interface IHistorialService extends IService<Historial> {
	
	Historial getHistorialPorIdMedicamentoIdEstablecimientoMes(int idMedi, int mes) throws Exception;
}
