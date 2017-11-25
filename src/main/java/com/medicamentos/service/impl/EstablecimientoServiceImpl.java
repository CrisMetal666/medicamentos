package com.medicamentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicamentos.model.Establecimiento;
import com.medicamentos.repository.IEstablecimientoRepository;
import com.medicamentos.service.IEstablecimientoService;

@Service
public class EstablecimientoServiceImpl implements IEstablecimientoService {

	@Autowired
	private IEstablecimientoRepository establecimientoRepo;

	@Override
	public void guardar(Establecimiento entidad) throws Exception {
		
		establecimientoRepo.save(entidad);
	}

	@Override
	public Establecimiento buscarPorId(int id) throws Exception {
		
		return establecimientoRepo.findOne(id);
	}

	@Override
	public void eliminar(int id) throws Exception {
		
		establecimientoRepo.delete(id);
	}

	@Override
	public List<Establecimiento> listarTodos() throws Exception {
		
		List<Establecimiento> lst = establecimientoRepo.findAll();
		
		if(lst != null && !lst.isEmpty()) {
			
			lst.forEach(x -> {
				x.setEstablecimientoMedicamentos(null);
				x.setUsuario(null);
			});
		}
		
		return establecimientoRepo.findAll();
	}

	@Override
	public Integer getIdPorUsuarioId(Integer id) {
		
		return establecimientoRepo.getIdPorUsuarioId(id);
	}

	@Override
	public Integer getIdPorUsuarioIdentificacion(String identificacion) {
		
		return establecimientoRepo.getIdPorUsuarioIdentificacion(identificacion);
	}
	
}