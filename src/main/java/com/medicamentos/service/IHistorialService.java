package com.medicamentos.service;

import com.medicamentos.model.Historial;

public interface IHistorialService extends IService<Historial> {
	
	Historial getHistorialPorIdMedicamentoIdEstablecimientoMes(int idMedi, String fecha) throws Exception;
	Integer getSaldoAnterior(int idMedicamento, int idEstablecimiento, String fecha) throws Exception;
	Integer getSumSaldo(int idMedicamento);
	
}
