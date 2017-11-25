package com.medicamentos.Medicamentos;

import com.medicamentos.util.DateFormat;

public class TestWithoutSpring {

	public static void main(String[] args) {
		
		DateFormat d = new DateFormat();
		
		System.out.println(d.getMesAnteriorString("2017/01/05"));

	}

}
