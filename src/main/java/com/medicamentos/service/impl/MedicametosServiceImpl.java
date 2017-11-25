package com.medicamentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicamentos.model.Medicamentos;
import com.medicamentos.repository.IMedicamentosRepository;
import com.medicamentos.service.IEstablecimientoService;
import com.medicamentos.service.IHistorialService;
import com.medicamentos.service.IMedicamentosService;
import com.medicamentos.util.InformacionUsuario;

@Service
public class MedicametosServiceImpl implements IMedicamentosService {

	@Autowired
	private IMedicamentosRepository medicamentosRepo;
	
	@Autowired
	private IEstablecimientoService establecimientoService;
	
	@Autowired
	private IHistorialService historialService;
	
	@Autowired
	private InformacionUsuario infUser;

	@Override
	public void guardar(Medicamentos entidad) throws Exception {

		medicamentosRepo.save(entidad);
	}

	@Override
	public Medicamentos buscarPorId(int id) throws Exception {

		return medicamentosRepo.findOne(id);
	}

	@Override
	public void eliminar(int id) throws Exception {

		medicamentosRepo.delete(id);
	}

	@Override
	public List<Medicamentos> listarTodos() throws Exception {

		return medicamentosRepo.findAll();
	}

	@Override
	public List<Medicamentos> listarMedicamentos(Integer id) throws Exception {

		return medicamentosRepo.listarMedicamentos(id);
	}

	@Override
	public List<Medicamentos> listarMedicamentosNoRegistrados(String fecha) throws Exception {

		int id = establecimientoService.getIdPorUsuarioIdentificacion(infUser.getUsuario());

		List<Medicamentos> lista = medicamentosRepo.listarMedicamentosNoRegistrados(fecha, id);

		return lista;
	}

	@Override
	public List<Medicamentos> listarMedicamentosRegistrados(String fecha) throws Exception {

		int id = establecimientoService.getIdPorUsuarioIdentificacion(infUser.getUsuario());

		List<Medicamentos> lista = medicamentosRepo.listarMedicamentosRegistrados(fecha, id);

		return lista;
	}

	@Override
	public List<Medicamentos> listarMedicamentosConSaldo(String mes) {
		
		int id = establecimientoService.getIdPorUsuarioIdentificacion(infUser.getUsuario());
		
		return medicamentosRepo.listarMedicamentosConSaldo(mes, id);
	}

	@Override
	public List<Medicamentos> listarMedicamentosConSaldo(int id) {
		
		return medicamentosRepo.listarMedicamentosConSaldo(id);
	}

	@Override
	public List<Medicamentos> listarMedicamentosConSumSaldo() {
		
		List<Medicamentos> lst = medicamentosRepo.listarMedicamentos();
		
		for(Medicamentos medicamento : lst) {
			
			medicamento.setSaldo(historialService.getSumSaldo(medicamento.getId()));
		}
		
		return lst;
	}

}
