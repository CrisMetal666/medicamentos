package com.medicamentos.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicamentos.model.EstablecimientoMedicamento;
import com.medicamentos.model.Historial;
import com.medicamentos.repository.IHistorialRepository;
import com.medicamentos.service.IEstablecimientoMedicamentoService;
import com.medicamentos.service.IEstablecimientoService;
import com.medicamentos.service.IHistorialService;
import com.medicamentos.util.DateFormat;
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
	@Autowired
	private DateFormat dateFormat;

	@Override
	public void guardar(Historial entidad) throws Exception {

		// Obtenemos el usuario que esta logeado
		String username = infUser.getUsuario();

		int idEstablecimiento = establecimientoService.getIdPorUsuarioIdentificacion(username);
		int idMedicamento = entidad.getEstablecimientoMedicamento().getMedicamentos().getId();
		int idEstablecimientoMedicamento = establecimientoMedicamentoService
				.getIdPorEstablecimientoMedicamento(idMedicamento, idEstablecimiento);

		// formatiamos la fecha a String
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		
		System.out.println(format);
		String date = format.format(entidad.getFecha());		

		Integer saldoAnterior = this.getSaldoAnterior(idMedicamento, idEstablecimiento, date);

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
	public Historial getHistorialPorIdMedicamentoIdEstablecimientoMes(int idMedi, String fecha) throws Exception {

		// Obtenemos el usuario que esta logeado
		String username = infUser.getUsuario();

		int idEstablecimiento = establecimientoService.getIdPorUsuarioIdentificacion(username);

		return historialRepo.getHistorialPorIdMedicamentoIdEstablecimientoMes(idMedi, idEstablecimiento, fecha);
	}

	@Override
	public Integer getSaldoAnterior(int idMedicamento, int idEstablecimiento, String fecha) throws Exception {
		
		String fechaAnterior = dateFormat.getMesAnteriorString(fecha);
		
		return historialRepo.getSaldoAnterior(idMedicamento, idEstablecimiento, fechaAnterior);
	}

	@Override
	public Integer getSumSaldo(int idMedicamento) {
		
		return historialRepo.getSumSaldo(idMedicamento);
	}
	
	

}
