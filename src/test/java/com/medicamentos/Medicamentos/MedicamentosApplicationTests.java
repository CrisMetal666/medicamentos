package com.medicamentos.Medicamentos;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.medicamentos.model.Medicamentos;
import com.medicamentos.repository.IHistorialRepository;
import com.medicamentos.repository.IMedicamentosRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicamentosApplicationTests {

	@Autowired
	private IMedicamentosRepository repo;
	
	@Autowired
	private IHistorialRepository hRepo;

	@Test
	public void contextLoads() {

		try {

			List<Medicamentos> lst = repo.listarMedicamentos();
			
			for(Medicamentos m : lst) {
				m.setSaldo(hRepo.getSumSaldo(m.getId()));
				
				System.out.println(m.getNombreGenerico() + " " + m.getSaldo());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
