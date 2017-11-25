package com.medicamentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medicamentos.util.InformacionUsuario;

@Controller
public class NavegacionController {

	@Autowired
	private InformacionUsuario infUSer;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/notFound", method = RequestMethod.GET)
	public String notFound() {
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/user/actualizar", method = RequestMethod.GET)
	public String actualizar() {
		
		return "users/actualizarMedicamentos";
	}
	
	@RequestMapping(value = "/admin/medicamentos", method = RequestMethod.GET)
	public String medicamentosAdmin() {
		
		return "admins/medicamentoAdmin";
	}

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio() {

		String direccion = "";

		String rol = infUSer.getRol();

		if (rol.equals("ROLE_USER")) {

			direccion = "users/registrarMedicamentos";
			
		} else if (rol.equals("ROLE_ADMIN")) {
			
			direccion = "admins/establecimientoAdmin";
			
		} else if(rol.isEmpty()) {
			direccion = "login";
		}

		return direccion;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();

		if (error != null) {
			model.addObject("error", "Crendenciales incorrectas");
		}

		model.setViewName("login");

		return model;

	}
}
	
