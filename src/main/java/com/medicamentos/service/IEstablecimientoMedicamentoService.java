package com.medicamentos.service;

import com.medicamentos.model.EstablecimientoMedicamento;

public interface IEstablecimientoMedicamentoService extends IService<EstablecimientoMedicamento> {

	Integer getIdPorEstablecimientoMedicamento(Integer idMedicamento, Integer idEstablecimiento);
}
