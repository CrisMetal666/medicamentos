package com.medicamentos.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medicamentos.model.Establecimiento;
import com.medicamentos.service.IEstablecimientoService;

@RestController
@RequestMapping("establecimiento")
public class EstablecimientoApi {

	@Autowired
	private IEstablecimientoService establecimientoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Establecimiento> listarTodos(){
		
		List<Establecimiento> lst = null;
		try {
			lst = establecimientoService.listarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lst;
	}
}
