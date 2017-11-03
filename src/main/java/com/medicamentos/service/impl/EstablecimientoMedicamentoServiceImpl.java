package com.medicamentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicamentos.model.EstablecimientoMedicamento;
import com.medicamentos.repository.IEstablecimientoMedicamentoRepository;
import com.medicamentos.service.IEstablecimientoMedicamentoService;

@Service
public class EstablecimientoMedicamentoServiceImpl implements IEstablecimientoMedicamentoService {

	@Autowired
	private IEstablecimientoMedicamentoRepository establecimientoMedicamentoRepo;
	
	@Override
	public void guardar(EstablecimientoMedicamento entidad) throws Exception {
		
		establecimientoMedicamentoRepo.save(entidad);
	}

	@Override
	public EstablecimientoMedicamento buscarPorId(int id) throws Exception {
		
		return establecimientoMedicamentoRepo.findOne(id);
	}

	@Override
	public void eliminar(int id) throws Exception {
		
		establecimientoMedicamentoRepo.delete(id);
	}

	@Override
	public List<EstablecimientoMedicamento> listarTodos() throws Exception {
		
		return establecimientoMedicamentoRepo.findAll();
	}

	@Override
	public Integer getIdPorEstablecimientoMedicamento(Integer idMedicamento, Integer idEstablecimiento) {
		
		return establecimientoMedicamentoRepo.getIdPorEstablecimientoMedicamento(idMedicamento, idEstablecimiento);
	}

}