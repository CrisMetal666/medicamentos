package com.medicamentos.service;

import java.util.List;

import com.medicamentos.model.Medicamentos;

public interface IMedicamentosService extends IService<Medicamentos> {

	List<Medicamentos> listarMedicamentos(Integer id) throws Exception;
	List<Medicamentos> listarMedicamentosNoRegistrados(String fecha) throws Exception;
	List<Medicamentos> listarMedicamentosRegistrados(String fecha) throws Exception;
	List<Medicamentos> listarMedicamentosConSaldo(String mes);
	List<Medicamentos> listarMedicamentosConSaldo(int id);
	List<Medicamentos> listarMedicamentosConSumSaldo();
}
