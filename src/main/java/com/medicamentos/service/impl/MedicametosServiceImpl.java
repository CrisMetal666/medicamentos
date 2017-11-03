package com.medicamentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicamentos.model.Medicamentos;
import com.medicamentos.repository.IMedicamentosRepository;
import com.medicamentos.service.IEstablecimientoService;
import com.medicamentos.service.IMedicamentosService;
import com.medicamentos.util.InformacionUsuario;

@Service
public class MedicametosServiceImpl implements IMedicamentosService {

	@Autowired
	private IMedicamentosRepository medicamentosRepo;
	@Autowired
	private IEstablecimientoService establecimientoService;
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
	public List<Medicamentos> listarMedicamentosNoRegistrados(int mes) throws Exception {

		int id = establecimientoService.getIdPorUsuarioIdentificacion(infUser.getUsuario());

		List<Medicamentos> lista = medicamentosRepo.listarMedicamentosNoRegistrados(mes, id);

		for (Medicamentos m : lista) {
			m.setEstablecimientoMedicamentos(null);
		}

		return lista;
	}

	@Override
	public List<Medicamentos> listarMedicamentosRegistrados(int mes) throws Exception {

		int id = establecimientoService.getIdPorUsuarioIdentificacion(infUser.getUsuario());

		List<Medicamentos> lista = medicamentosRepo.listarMedicamentosRegistrados(mes, id);

		for (Medicamentos m : lista) {
			m.setEstablecimientoMedicamentos(null);
		}

		return lista;
	}

}
