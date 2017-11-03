package com.medicamentos.Medicamentos;

import java.util.Calendar;
import java.util.Date;

public class TestWithoutSpring {

	public static void main(String[] args) {

		Date date = Calendar.getInstance().getTime();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		
		System.out.println(month);

	}

}
