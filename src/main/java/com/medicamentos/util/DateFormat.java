package com.medicamentos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 * 
 * @author crismetal
 * 
 * Esta clase se encargara de formatear fechas de string a date, date a string y calculos de fechas
 *
 */
@Component
public class DateFormat {

	/**
	 * Se obtiene la fecha especificada un mes atras
	 * @param fecha (yyyy/mm/dd)
	 * @return
	 */
	public String getMesAnteriorString(String fecha) {
		
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		
		String fechaAnterior = "";
		
		try {
			cal.setTime(sdf.parse(fecha));
			
			cal.add(Calendar.MONTH, -1);
			
			fechaAnterior = sdf.format(cal.getTime());
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return fechaAnterior;
	}
}
