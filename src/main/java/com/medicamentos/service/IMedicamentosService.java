package com.medicamentos.service;

import java.util.List;

import com.medicamentos.model.Medicamentos;

public interface IMedicamentosService extends IService<Medicamentos> {

	List<Medicamentos> listarMedicamentos(Integer id) throws Exception;
	List<Medicamentos> listarMedicamentosNoRegistrados(int mes) throws Exception;
	List<Medicamentos> listarMedicamentosRegistrados(int mes) throws Exception;
}
