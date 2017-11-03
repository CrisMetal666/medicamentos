package com.medicamentos.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class InformacionUsuario {

	public String getRol() {

		String rol = "";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			
			if (userDetail != null) {

				Object roles[] = userDetail.getAuthorities().toArray();

				rol = roles[0].toString();

			}
		}

		return rol;
	}

	public String getUsuario() {

		String user = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			
			if (userDetail != null) {

				user = userDetail.getUsername();

			}
		}

		return user;
	}

}
