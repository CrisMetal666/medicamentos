package com.medicamentos.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicamentos.model.EstablecimientoMedicamento;
import com.medicamentos.model.Historial;
import com.medicamentos.repository.IHistorialRepository;
import com.medicamentos.service.IEstablecimientoMedicamentoService;
import com.medicamentos.service.IEstablecimientoService;
import com.medicamentos.service.IHistorialService;
import com.medicamentos.util.InformacionUsuario;

@Service
public class HistorailServiceImpl implements IHistorialService {

	@Autowired
	private IHistorialRepository historialRepo;
	@Autowired
	private IEstablecimientoService establecimientoService;
	@Autowired
	private InformacionUsuario infUser;
	@Autowired
	private IEstablecimientoMedicamentoService establecimientoMedicamentoService;

	@Override
	public void guardar(Historial entidad) throws Exception {

		// Obtenemos el usuario que esta logeado
		String username = infUser.getUsuario();

		int idEstablecimiento = establecimientoService.getIdPorUsuarioIdentificacion(username);
		int idMedicamento = entidad.getEstablecimientoMedicamento().getMedicamentos().getId();
		int idEstablecimientoMedicamento = establecimientoMedicamentoService
				.getIdPorEstablecimientoMedicamento(idMedicamento, idEstablecimiento);

		// obtenemos el mes actual que necesitaremos para la consulta
		Calendar cal = Calendar.getInstance();
		cal.setTime(entidad.getFecha());
		int month = cal.get(Calendar.MONTH);

		Integer saldoAnterior = historialRepo.getSaldoAnterior(idMedicamento, idEstablecimiento, month);

		if (saldoAnterior == null)
			saldoAnterior = 0;

		int saldo = saldoAnterior + entidad.getIngreso() - entidad.getSalida();

		entidad.setSaldo(saldo);
		entidad.setResponsable(username);
		entidad.setEstablecimientoMedicamento(new EstablecimientoMedicamento(idEstablecimientoMedicamento));

		historialRepo.save(entidad);
	}

	@Override
	public Historial buscarPorId(int id) throws Exception {

		return historialRepo.findOne(id);
	}

	@Override
	public void eliminar(int id) throws Exception {

		historialRepo.delete(id);
	}

	@Override
	public List<Historial> listarTodos() throws Exception {

		return historialRepo.findAll();
	}

	@Override
	public Historial getHistorialPorIdMedicamentoIdEstablecimientoMes(int idMedi, int mes) throws Exception {

		// Obtenemos el usuario que esta logeado
		String username = infUser.getUsuario();

		int idEstablecimiento = establecimientoService.getIdPorUsuarioIdentificacion(username);

		return historialRepo.getHistorialPorIdMedicamentoIdEstablecimientoMes(idMedi, idEstablecimiento, mes);
	}

}
