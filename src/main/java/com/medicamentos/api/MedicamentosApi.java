package com.medicamentos.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicamentos.model.Medicamentos;
import com.medicamentos.service.IMedicamentosService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosApi {

	@Autowired
	private IMedicamentosService medicamentosService;

	/**
	 * Se obtendra los medicamentos pertenecientes a un establecimiento que no han
	 * sido registrados
	 * 
	 * @param mes:
	 *            mes reprensentado en numeros (1 - 12)
	 * 
	 * @return lista de medicamentos con datos minimos (id, nombre_generico)
	 */
	@RequestMapping(value = "/listaNoRegistrados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamentos>> listaMedicamentoNoRegistrados(@RequestParam("fecha") String fecha) {

		List<Medicamentos> lista = new ArrayList<>();

		try {

			lista = medicamentosService.listarMedicamentosNoRegistrados(fecha);

		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}

	/**
	 * Se obtendra los medicamentos pertenecientes a un establecimiento que han sido
	 * registrados
	 * 
	 * @param mes:
	 *            mes reprensentado en numeros (1 - 12)
	 * 
	 * @return lista de medicamentos con datos minimos (id, nombre_generico)
	 */
	@RequestMapping(value = "/listaRegistrados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamentos>> listaMedicamentosRegistrados(@RequestParam("fecha") String fecha) {

		List<Medicamentos> lista = new ArrayList<>();

		try {

			lista = medicamentosService.listarMedicamentosRegistrados(fecha);

		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}

	/**
	 * Se obtendra los medicamentos pertenecientes a un establecimiento que han sido
	 * registrados con su respectivo saldo
	 * 
	 * @param mes:
	 *            mes reprensentado en numeros (1 - 12)
	 * 
	 * @return lista de medicamentos con datos minimos (id, nombre_generico)
	 */
	@RequestMapping(value = "/listaRegistradosConSaldo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamentos>> listaMedicamentosRegistradosConSaldo(
			@RequestParam("fecha") String fecha) {

		List<Medicamentos> lista = new ArrayList<>();

		try {

			lista = medicamentosService.listarMedicamentosConSaldo(fecha);

		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}

	/**
	 * lista los medicametos y el saldo pertenecientes a un establecimiento
	 * @param id. id del establecimiento
	 * @return
	 */
	@RequestMapping(value = "/listaPorIdEstablecimiento/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> medicamentosConSaldoPorEstablecimineto(@PathVariable("id") Integer id) {

		List<Medicamentos> lista = new ArrayList<Medicamentos>();
		
		try {

			lista = medicamentosService.listarMedicamentosConSaldo(id);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}
	
	/**
	 * lista todos los medicametos con la suma total saldo en el mes actual
	 * @return
	 */
	@RequestMapping(value = "/listarSumSaldo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> medicamentosConSumSaldo() {

		List<Medicamentos> lista = new ArrayList<Medicamentos>();
		
		try {

			lista = medicamentosService.listarMedicamentosConSumSaldo();
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}
}
