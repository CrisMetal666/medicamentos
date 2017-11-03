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
import org.springframework.web.bind.annotation.RestController;

import com.medicamentos.model.Medicamentos;
import com.medicamentos.service.IMedicamentosService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosApi {
	
	@Autowired
	private IMedicamentosService medicamentosService;

	/**
	 * Se obtendra los medicamentos pertenecientes a un establecimiento que no han sido registrados
	 * 
	 * @param mes: mes reprensentado en numeros (1 - 12)
	 * 
	 * @return lista de medicamentos con datos minimos (id, nombre_generico)
	 */
	@RequestMapping(value = "/listaNoRegistrados/{mes}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamentos>> listaMedicamentoNoRegistrados(@PathVariable("mes") Integer mes){
		
		List<Medicamentos> lista = new ArrayList<>();
		
		try {
			
			lista = medicamentosService.listarMedicamentosNoRegistrados(mes);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}
	
	/**
	 * Se obtendra los medicamentos pertenecientes a un establecimiento que han sido registrados
	 * 
	 * @param mes: mes reprensentado en numeros (1 - 12)
	 * 
	 * @return lista de medicamentos con datos minimos (id, nombre_generico)
	 */
	@RequestMapping(value = "/listaRegistrados/{mes}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamentos>> listaMedicamentosRegistrados(@PathVariable("mes") Integer mes){
		
		List<Medicamentos> lista = new ArrayList<>();
		
		try {
			
			lista = medicamentosService.listarMedicamentosRegistrados(mes);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Medicamentos>>(lista, HttpStatus.OK);
	}
}
