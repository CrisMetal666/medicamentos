package com.medicamentos.Medicamentos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.medicamentos.repository.IHistorialRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicamentosApplicationTests {
	
	@Autowired
	private IHistorialRepository repo;

	@Test
	public void contextLoads() {
		
		try {
			System.out.println("------> " +repo.getHistorialPorIdMedicamentoIdEstablecimientoMes(2, 1, 11).getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
