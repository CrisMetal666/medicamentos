package com.medicamentos.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicamentos.model.Historial;
import com.medicamentos.service.IHistorialService;

@RestController()
@RequestMapping("/historial")
public class HistorialApi {

	@Autowired
	private IHistorialService historialService;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> guardarHistorial(@RequestBody Historial pHistorial) {

		Map<String, String> map = new HashMap<>();

		try {

			historialService.guardar(pHistorial);

			map.put("status", "OK");

		} catch (Exception e) {

			e.printStackTrace();

			map.put("status", "ERROR");

			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> actualizarHistorial(@RequestBody Historial pHistorial) {

		Map<String, Object> map = new HashMap<>();

		if (pHistorial.getId() <= 0) {
			
			map.put("status", "ERROR");

			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			historialService.guardar(pHistorial);

			map.put("status", "OK");
			map.put("saldo", pHistorial.getSaldo());

		} catch (Exception e) {

			e.printStackTrace();

			map.put("status", "ERROR");

			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Historial> listaHistorialPorMedicamentoMes(@RequestParam("id") int idMedi,
			@RequestParam("fecha") String fecha) {

		Historial historial = null;

		try {

			historial = historialService.getHistorialPorIdMedicamentoIdEstablecimientoMes(idMedi, fecha);

		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<Historial>(historial, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Historial>(historial, HttpStatus.OK);
	}
}
